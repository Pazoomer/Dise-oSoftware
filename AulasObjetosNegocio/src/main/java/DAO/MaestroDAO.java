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
import objetosNegocio.Maestro;

/**
 *
 * @author pauli
 */
public class MaestroDAO {
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public MaestroDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PUAula");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void guardarMaestro(Maestro maestro) {
        entityManager.getTransaction().begin();
        entityManager.persist(maestro);
        entityManager.getTransaction().commit();
    }

    public void actualizarMaestro(Maestro maestro) {
        entityManager.getTransaction().begin();
        entityManager.merge(maestro);
        entityManager.getTransaction().commit();
    }

    public void eliminarMaestro(long maestroId) {
        entityManager.getTransaction().begin();
        Maestro maestro = entityManager.find(Maestro.class, maestroId);
        entityManager.remove(maestro);
        entityManager.getTransaction().commit();
    }

    public Maestro obtenerMaestroPorId(long maestroId) {
        return entityManager.find(Maestro.class, maestroId);
    }

    public List<Maestro> obtenerTodosLosMaestros() {
        Query query = entityManager.createQuery("SELECT * FROM Maestro ");
        return query.getResultList();
    }
    
}
