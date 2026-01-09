
package com.ifmg.projeto_haras.model.dao;

import com.ifmg.projeto_haras.factory.Database;
import com.ifmg.projeto_haras.model.Proprietario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class ProprietarioDAO implements IDao{
    private EntityManager entityManager;
    private TypedQuery<Proprietario> qry;
    private String sql;
    
    public ProprietarioDAO(){
        entityManager = Database.getInstance().getEntityManager();
    }
    
    public Integer getProprietarioByEmailAndSenha(String email, String senha){
        sql = " SELECT p "
                + " FROM Proprietario p "
                + " WHERE email = :email"
                + " AND senha = :senha ";
        qry = this.entityManager.createQuery(sql, Proprietario.class);
        qry.setParameter("email", email);
        qry.setParameter("senha", senha);
        
        List<Proprietario> lst = qry.getResultList();

        if (lst.isEmpty()) {
            return 0;
        } else {
            return lst.get(0).getId();
        }
    }

    
    @Override
    public void save(Object obj) {
        Proprietario proprietario = (Proprietario) obj;
        this.entityManager.getTransaction().begin();
        if (proprietario != null) {
            this.entityManager.merge(proprietario);
        } else {
            this.entityManager.persist(proprietario);
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.clear();
    }

    
    @Override
    public boolean delete(Object obj) {
        Proprietario proprietario = (Proprietario) obj;
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(proprietario);
        this.entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Object find(int id) {
        sql = " SELECT p "
                + " FROM Proprietario p "
                + " WHERE id = :id ";

        qry = this.entityManager.createQuery(sql, Proprietario.class);
        qry.setParameter("id", id);
        
        List<Proprietario> lst = qry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
        
    public List<Proprietario> findAll() {
        sql = " SELECT p "
                + " FROM Proprietario p ";

        TypedQuery<Proprietario> listQry = this.entityManager.createQuery(sql, Proprietario.class);
        
        List<Proprietario> lst = listQry.getResultList();
        return lst;
    }
}
