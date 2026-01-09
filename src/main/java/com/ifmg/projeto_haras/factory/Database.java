
package com.ifmg.projeto_haras.factory;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class Database {
    private static Database INSTANCE = null;
    
    private EntityManager entityManager;
    
    private Database(){
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("haras-jpa");
        this.entityManager = factory.createEntityManager();
        // Seed database from data/seed-generic.sql if empty
        try {
            Long count = entityManager.createQuery("SELECT COUNT(p) FROM com.ifmg.projeto_haras.model.Pessoa p", Long.class)
                    .getSingleResult();
            if (count == 0) {
                List<String> statements = null;
                Path file = Path.of("data/seed-generic.sql");
                if (Files.exists(file)) {
                    String sql = Files.readString(file);
                    statements = Arrays.stream(sql.split(";"))
                            .map(String::trim)
                            .filter(s -> !s.isEmpty() && !s.startsWith("--"))
                            .collect(Collectors.toList());
                } else {
                    InputStream is = getClass().getClassLoader().getResourceAsStream("data/seed-generic.sql");
                    if (is != null) {
                        try (BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
                            String sql = br.lines().collect(Collectors.joining(System.lineSeparator()));
                            statements = Arrays.stream(sql.split(";"))
                                    .map(String::trim)
                                    .filter(s -> !s.isEmpty() && !s.startsWith("--"))
                                    .collect(Collectors.toList());
                        }
                    }
                }
                if (statements != null && !statements.isEmpty()) {
                    // If the seed file is available on the filesystem, prefer using H2 RunScript
                    // to execute the whole script reliably (handles multiple statements, comments, etc.).
                    Path fileFs = Path.of("data/seed-generic.sql");
                    if (Files.exists(fileFs)) {
                        try {
                            org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
                            session.doWork(conn -> {
                                java.io.FileReader fr = null;
                                try {
                                    fr = new java.io.FileReader(fileFs.toFile());
                                    org.h2.tools.RunScript.execute(conn, fr);
                                } catch (java.io.IOException ioe) {
                                    throw new RuntimeException(ioe);
                                } finally {
                                    if (fr != null) {
                                        try { fr.close(); } catch (Exception e) { /* ignore */ }
                                    }
                                }
                            });
                        } catch (Exception e) {
                            // if RunScript failed, fall back to previous per-statement execution path
                            System.err.println("RunScript failed, falling back to statement-by-statement execution: " + e.getMessage());
                        }
                    }

                    // Proceed with existing execution path (which will be skipped if RunScript succeeded)
                    final List<String> seedStatements = statements;
                    // Try to execute seed using the underlying JDBC connection to toggle
                    // H2 referential integrity around the batch. This ensures FK order
                    // issues do not prevent the seed from applying.
                    try {
                        // Prefer using Hibernate Session.doWork to access a JDBC Connection
                        // this is more reliable than unwrapping Connection directly.
                        try {
                            org.hibernate.Session session = entityManager.unwrap(org.hibernate.Session.class);
                            session.doWork(conn -> {
                                boolean oldAuto = conn.getAutoCommit();
                                try (java.sql.Statement stmt = conn.createStatement()) {
                                    conn.setAutoCommit(false);
                                    try {
                                        stmt.execute("SET REFERENTIAL_INTEGRITY FALSE");
                                    } catch (Exception e) {
                                        // ignore if DB doesn't support it
                                    }
                                    for (String st : seedStatements) {
                                        String sql = st.trim();
                                        if (sql.isEmpty()) continue;
                                        try {
                                            stmt.executeUpdate(sql);
                                        } catch (Exception e) {
                                            System.err.println("Seed statement failed: " + sql);
                                            e.printStackTrace();
                                        }
                                    }
                                    try {
                                        stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");
                                    } catch (Exception e) {
                                        // ignore
                                    }
                                    conn.commit();
                                } finally {
                                    try { conn.setAutoCommit(oldAuto); } catch (Exception e) { /* ignore */ }
                                }
                            });
                        } catch (Exception sessionEx) {
                            // Fallback: attempt direct unwrap of Connection
                            java.sql.Connection conn = null;
                            try {
                                conn = entityManager.unwrap(java.sql.Connection.class);
                            } catch (Exception e) {
                                conn = null;
                            }
                            if (conn != null) {
                                boolean oldAuto = conn.getAutoCommit();
                                try (java.sql.Statement stmt = conn.createStatement()) {
                                    conn.setAutoCommit(false);
                                    try {
                                        stmt.execute("SET REFERENTIAL_INTEGRITY FALSE");
                                    } catch (Exception e) {
                                        // ignore if DB doesn't support it
                                    }
                                    for (String st : seedStatements) {
                                        String sql = st.trim();
                                        if (sql.isEmpty()) continue;
                                        try {
                                            stmt.executeUpdate(sql);
                                        } catch (Exception e) {
                                            System.err.println("Seed statement failed: " + sql);
                                            e.printStackTrace();
                                        }
                                    }
                                    try {
                                        stmt.execute("SET REFERENTIAL_INTEGRITY TRUE");
                                    } catch (Exception e) {
                                        // ignore
                                    }
                                    conn.commit();
                                } finally {
                                    try { conn.setAutoCommit(oldAuto); } catch (Exception e) { /* ignore */ }
                                }
                            } else {
                            // Fallback: previous per-statement EntityManager execution
                                    for (String st : seedStatements) {
                                try {
                                    entityManager.getTransaction().begin();
                                    entityManager.createNativeQuery(st).executeUpdate();
                                    entityManager.getTransaction().commit();
                                } catch (Exception e) {
                                    try {
                                        if (entityManager.getTransaction().isActive()) {
                                            entityManager.getTransaction().rollback();
                                        }
                                    } catch (Exception rb) {
                                        // ignore
                                    }
                                    System.err.println("Seed statement failed: " + st);
                                    e.printStackTrace();
                                }
                            }
                            }
                        }
                    } catch (Exception ex) {
                        System.err.println("Error applying seed via JDBC fallback:");
                        ex.printStackTrace();
                    }
                }
            }
        } catch (Exception ex) {
            // if any error during seed detection, log and continue
            ex.printStackTrace();
        }
    }
    
    public EntityManager getEntityManager(){
        return this.entityManager;
    }
    
    public static Database getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new Database();
        }
        return INSTANCE;
    }
}
