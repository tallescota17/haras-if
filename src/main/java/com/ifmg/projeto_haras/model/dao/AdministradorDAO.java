
package com.ifmg.projeto_haras.model.dao;

import com.ifmg.projeto_haras.factory.Database;
import com.ifmg.projeto_haras.model.Administrador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class AdministradorDAO implements IDao {
    private EntityManager entityManager;
    private TypedQuery<Administrador> qry;
    private String sql;
    
    public AdministradorDAO(){
        entityManager = Database.getInstance().getEntityManager();
    }

    
    @Override
    public void save(Object obj) {
        Administrador admin = (Administrador) obj;
        this.entityManager.getTransaction().begin();
        if (admin != null) {
            this.entityManager.merge(admin);
        } else {
            this.entityManager.persist(admin);
        }
        this.entityManager.getTransaction().commit();
    }

    
    @Override
    public boolean delete(Object obj) {
        Administrador admin = (Administrador) obj;
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(admin);
        this.entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Object find(int id) {
        sql = " SELECT a "
                + " FROM Administrador a "
                + " WHERE id = :id ";

        qry = this.entityManager.createQuery(sql, Administrador.class);
        qry.setParameter("id", id);
        
        List<Administrador> lst = qry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    public List<Object> findAll() {
        sql = " SELECT a "
                + " FROM Administrador a ";

        TypedQuery<Administrador> listQry = this.entityManager.createQuery(sql, Administrador.class);
        
        List<Administrador> lst = listQry.getResultList();
        return (List) lst;
    }
}
