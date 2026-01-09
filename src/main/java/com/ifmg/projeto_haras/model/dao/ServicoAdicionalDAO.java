/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ifmg.projeto_haras.model.dao;

import com.ifmg.projeto_haras.factory.Database;
import com.ifmg.projeto_haras.model.ServicoAdicional;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;


public class ServicoAdicionalDAO implements IDao {
    private EntityManager entityManager;
    private TypedQuery<ServicoAdicional> qry;
    private String sql;
    
    public ServicoAdicionalDAO(){
        entityManager = Database.getInstance().getEntityManager();
    }

    
    @Override
    public void save(Object obj) {
        ServicoAdicional servicoAdicional = (ServicoAdicional) obj;
        this.entityManager.getTransaction().begin();
        if (servicoAdicional != null) {
            this.entityManager.merge(servicoAdicional);
        } else {
            this.entityManager.persist(servicoAdicional);
        }
        this.entityManager.getTransaction().commit();
    }

    
    @Override
    public boolean delete(Object obj) {
        ServicoAdicional servicoAdicional = (ServicoAdicional) obj;
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(servicoAdicional);
        this.entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Object find(int id) {
        sql = " SELECT s "
                + " FROM ServicoAdicional s "
                + " WHERE id = :id ";

        qry = this.entityManager.createQuery(sql, ServicoAdicional.class);
        qry.setParameter("id", id);
        
        List<ServicoAdicional> lst = qry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    public List<ServicoAdicional> findAll() {
        sql = " SELECT s "
                + " FROM ServicoAdicional s ";

        TypedQuery<ServicoAdicional> listQry = this.entityManager.createQuery(sql, ServicoAdicional.class);
        
        List<ServicoAdicional> lst = listQry.getResultList();
        return lst;
    }
}
