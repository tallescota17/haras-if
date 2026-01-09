
package com.ifmg.projeto_haras.model.dao;

import com.ifmg.projeto_haras.factory.Database;
import com.ifmg.projeto_haras.model.Equino;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.util.List;



public class EquinoDAO implements IDao {
    private EntityManager entityManager;
    private Query qry;
    private TypedQuery<Equino> typedQry;
    private String sql;
    
    public EquinoDAO(){
        entityManager = Database.getInstance().getEntityManager();
    }

    
    @Override
    public void save(Object obj) {
        Equino equino = (Equino) obj;
        this.entityManager.getTransaction().begin();
        if (equino != null) {
            System.out.println("AQUI AQUI AQUI");
            this.entityManager.merge(equino);
        } else {
            this.entityManager.persist(equino);
        }
        this.entityManager.getTransaction().commit();
        this.entityManager.clear();
    }

    
    @Override
    public boolean delete(Object obj) {
        Equino equino = (Equino) obj;
        this.entityManager.getTransaction().begin();
        this.entityManager.remove(equino);
        this.entityManager.getTransaction().commit();
        return true;
    }

    @Override
    public Object find(int id) {
        sql = " SELECT e "
                + " FROM Equino e "
                + " WHERE id = :id ";

        typedQry = this.entityManager.createQuery(sql, Equino.class);
        typedQry.setParameter("id", id);
        
        List<Equino> lst = typedQry.getResultList();
        if (lst.isEmpty()) {
            return null;
        } else {
            return lst.get(0);
        }
    }
    
    public List<Equino> findAll() {
        sql = " SELECT e "
                + " FROM Equino e ";

        typedQry = this.entityManager.createQuery(sql, Equino.class);
        
        List<Equino> lst = typedQry.getResultList();
        return lst;
    }
    
    public List<Object[]> getLeftJoinAlimentos(){
        sql = " SELECT e.id, e.nome, a.id, a.nome FROM equino e "
                + " LEFT JOIN equino_alimento ea ON e.id = ea.equino_id "
                + "JOIN alimento a ON ea.alimento_id = a.id ";

        qry = this.entityManager.createNativeQuery(sql);
        
        @SuppressWarnings("unchecked")
        List<Object[]> lst = (List<Object[]>) qry.getResultList();
        return lst;
    }
    
    public List<Object[]> getLeftJoinServicoAdicional(){
        sql = " SELECT e.id, e.nome, s.servico_adicional_id, s.servico, es.qtd FROM equino e " +
                " LEFT JOIN equino_servico es ON e.id = es.equino_id " +
                " JOIN servicoadicional s ON es.servico_adicional_id = s.servico_adicional_id ";

        qry = this.entityManager.createNativeQuery(sql);
        
        @SuppressWarnings("unchecked")
        List<Object[]> lst = (List<Object[]>) qry.getResultList();
        return lst;
    }
    
    public void deleteRelacionamentoAlimentos(Integer equino_id, Integer alimento_id){
        System.err.println(equino_id);
        System.err.println(alimento_id);
        this.entityManager.getTransaction().begin();
        sql = " DELETE FROM equino_alimento "
                + " WHERE equino_id = (?1)"
                + " AND alimento_id = (?2)";
        
        
        this.entityManager.createNativeQuery(sql).setParameter(1, equino_id).setParameter(2, alimento_id).executeUpdate();
        this.entityManager.getTransaction().commit();
    }
    
    public void deleteRelacionamentoServicos(Integer equino_id, Integer servico_id){
        this.entityManager.getTransaction().begin();
        sql = " DELETE FROM equino_servico "
                + " WHERE equino_id = (?1)"
                + " AND servico_adicional_id = (?2)";
        
        
        this.entityManager.createNativeQuery(sql).setParameter(1, equino_id).setParameter(2, servico_id).executeUpdate();
        this.entityManager.getTransaction().commit();
    }
}
