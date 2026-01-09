
package com.ifmg.projeto_haras.model.dao;

import com.ifmg.projeto_haras.factory.Database;
import com.ifmg.projeto_haras.model.Cuidador;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class CuidadorDAO implements IDao {
    private EntityManager entityManager;
    private TypedQuery<Cuidador> qry;
    private String sql;
    
    public CuidadorDAO(){
        entityManager = Database.getInstance().getEntityManager();
    }
    
    public Integer getCuidadorByEmailAndSenha(String email, String senha){
        sql = " SELECT c "
                + " FROM Cuidador c "
                + " WHERE email = :email"
                + " AND senha = :senha ";
        qry = this.entityManager.createQuery(sql, Cuidador.class);
        qry.setParameter("email", email);
        qry.setParameter("senha", senha);
        
        List<Cuidador> lst = qry.getResultList();

        if (lst.isEmpty()) {
            return 0;
        } else {
            return lst.get(0).getId();
        }
    }

    
    @Override
    public void save(Object obj) {
        Cuidador cuidador = (Cuidador) obj;
        this.entityManager.getTransaction().begin();
        if (cuidador != null) {
            this.entityManager.merge(cuidador);
        } else {
            this.entityManager.persist(cuidador);
        }
        this.entityManager.getTransaction().commit();
    }

    
    @Override
    public boolean delete(Object obj) {
        Cuidador cuidador = (Cuidador) obj;
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(cuidador);
        this.entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Object find(int id) {
        sql = " SELECT c "
                + " FROM Cuidador c "
                + " WHERE id = :id ";

        qry = this.entityManager.createQuery(sql, Cuidador.class);
        qry.setParameter("id", id);
        
        List<Cuidador> lst = qry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    public List<Cuidador> findAll() {
        sql = " SELECT c "
                + " FROM Cuidador c ";

        TypedQuery<Cuidador> listQry = this.entityManager.createQuery(sql, Cuidador.class);
        
        List<Cuidador> lst = listQry.getResultList();
        return lst;
    }
}
