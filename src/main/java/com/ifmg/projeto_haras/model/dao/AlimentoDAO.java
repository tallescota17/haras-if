
package com.ifmg.projeto_haras.model.dao;

import com.ifmg.projeto_haras.factory.Database;
import com.ifmg.projeto_haras.model.Alimento;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class AlimentoDAO implements IDao {
    private EntityManager entityManager;
    private TypedQuery<Alimento> qry;
    private String sql;
    
    public AlimentoDAO(){
        entityManager = Database.getInstance().getEntityManager();
    }

    
    @Override
    public void save(Object obj) {
        Alimento alimento = (Alimento) obj;
        this.entityManager.getTransaction().begin();
        if (alimento != null) {
            this.entityManager.merge(alimento);
        } else {
            this.entityManager.persist(alimento);
        }
        this.entityManager.getTransaction().commit();
    }

    
    @Override
    public boolean delete(Object obj) {
        Alimento alimento = (Alimento) obj;
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(alimento);
        this.entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Object find(int id) {
        sql = " SELECT a "
                + " FROM Alimento a "
                + " WHERE id = :id ";

        qry = this.entityManager.createQuery(sql, Alimento.class);
        qry.setParameter("id", id);
        
        List<Alimento> lst = qry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    public Alimento findByNome(String nome) {
        sql = " SELECT a "
                + " FROM Alimento a "
                + " WHERE nome = :nome ";

        TypedQuery<Alimento> singleQry = this.entityManager.createQuery(sql, Alimento.class);
        singleQry.setParameter("nome", nome);
        
        List<Alimento> lst = singleQry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
 
    public List<Alimento> findAll() {
        sql = " SELECT a "
                + " FROM Alimento a ";

        TypedQuery<Alimento> listQry = this.entityManager.createQuery(sql, Alimento.class);
        
        List<Alimento> lst = listQry.getResultList();
        return lst;
    }
    
    
    
}
