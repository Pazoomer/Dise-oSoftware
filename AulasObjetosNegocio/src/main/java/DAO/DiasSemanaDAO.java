/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import objetosNegocio.DiasSemana;

/**
 *
 * @author pauli
 */
public class DiasSemanaDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public DiasSemanaDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PUAula");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void guardarDiasSemana(DiasSemana diasSemana) {
        entityManager.getTransaction().begin();
        entityManager.persist(diasSemana);
        entityManager.getTransaction().commit();
    }

    public void actualizarDiasSemana(DiasSemana diasSemana) {
        entityManager.getTransaction().begin();
        entityManager.merge(diasSemana);
        entityManager.getTransaction().commit();
    }

    public void eliminarDiasSemana(long diasSemanaId) {
        entityManager.getTransaction().begin();
        DiasSemana diasSemana = entityManager.find(DiasSemana.class, diasSemanaId);
        entityManager.remove(diasSemana);
        entityManager.getTransaction().commit();
    }

    public DiasSemana obtenerDiasSemanaPorId(long diasSemanaId) {
        return entityManager.find(DiasSemana.class, diasSemanaId);
    }

    public List<DiasSemana> obtenerTodosLosDiasSemana() {
        Query query = entityManager.createQuery("SELECT * FROM DiasSemana ");
        return query.getResultList();
    }
    
}
