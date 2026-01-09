package com.ifmg.projeto_haras;

import com.ifmg.projeto_haras.factory.Database;
import jakarta.persistence.EntityManager;
import java.util.List;

public class TestSeed {
    public static void main(String[] args) {
        Database db = Database.getInstance();
        EntityManager em = db.getEntityManager();
        try {
            List<?> rows = em.createQuery("SELECT p.email, p.nome FROM com.ifmg.projeto_haras.model.Pessoa p").getResultList();
            System.out.println("Pessoas count: " + rows.size());
            for (Object r : rows) {
                Object[] arr = (Object[]) r;
                System.out.println(arr[0] + " | " + arr[1]);
            }

            Object equinos = em.createQuery("SELECT COUNT(e) FROM com.ifmg.projeto_haras.model.Equino e").getSingleResult();
            System.out.println("Equinos count: " + equinos);

            Object baias = em.createQuery("SELECT COUNT(b) FROM com.ifmg.projeto_haras.model.Baia b").getSingleResult();
            System.out.println("Baias count: " + baias);

            Object alimentos = em.createQuery("SELECT COUNT(a) FROM com.ifmg.projeto_haras.model.Alimento a").getSingleResult();
            System.out.println("Alimentos count: " + alimentos);

            Object servicos = em.createQuery("SELECT COUNT(s) FROM com.ifmg.projeto_haras.model.ServicoAdicional s").getSingleResult();
            System.out.println("Servicos adicionais count: " + servicos);

            Object faturas = em.createQuery("SELECT COUNT(f) FROM com.ifmg.projeto_haras.model.Fatura f").getSingleResult();
            System.out.println("Faturas count: " + faturas);

            // Also print raw table counts via native SQL to confirm
            Object rawBaia = em.createNativeQuery("SELECT COUNT(*) FROM Baia").getSingleResult();
            Object rawAlimento = em.createNativeQuery("SELECT COUNT(*) FROM Alimento").getSingleResult();
            Object rawEquino = em.createNativeQuery("SELECT COUNT(*) FROM Equino").getSingleResult();
            Object rawEquinoAlimento = em.createNativeQuery("SELECT COUNT(*) FROM Equino_Alimento").getSingleResult();
            Object rawServico = em.createNativeQuery("SELECT COUNT(*) FROM ServicoAdicional").getSingleResult();
            System.out.println("RAW counts - Baia:" + rawBaia + " Alimento:" + rawAlimento + " Equino:" + rawEquino + " Equino_Alimento:" + rawEquinoAlimento + " ServicoAdicional:" + rawServico);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
