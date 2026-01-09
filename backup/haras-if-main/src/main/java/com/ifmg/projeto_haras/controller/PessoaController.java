package com.ifmg.projeto_haras.controller;

import com.ifmg.projeto_haras.model.Administrador;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class PessoaController {
    
    private static Administrador admin;
    
    static {
        try {
            List<String> lines = Files.readAllLines(Paths.get("senhaAdm.txt"));
            if (lines.size() >= 2) {
                String email = lines.get(0).trim();
                String senha = lines.get(1).trim();
                admin = new Administrador();
                admin.setEmail(email);
                admin.setSenha(senha);
                admin.setNome("Administrador");
            }
        } catch (Exception e) {
            System.err.println("Erro ao carregar credenciais de admin: " + e.getMessage());
        }
    }
    
    public static boolean autenticarAdministrador(String email, String senha) {
        if (admin != null) {
            return admin.getEmail().equals(email) && admin.getSenha().equals(senha);
        }
        return false;
    }
    
    public static String getTipoPerfil(String email) {
        if (admin != null && admin.getEmail().equals(email)) {
            return "ADMINISTRADOR";
        }
        return "DESCONHECIDO";
    }
}
