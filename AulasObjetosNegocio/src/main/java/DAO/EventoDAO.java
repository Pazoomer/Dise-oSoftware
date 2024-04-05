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
import objetosNegocio.Evento;

/**
 *
 * @author pauli
 */
public class EventoDAO {
    
    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public EventoDAO() {
        entityManagerFactory = Persistence.createEntityManagerFactory("PUAula");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public void close() {
        entityManager.close();
        entityManagerFactory.close();
    }

    public void guardarEvento(Evento evento) {
        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.getTransaction().commit();
    }

    public void actualizarEvento(Evento evento) {
        entityManager.getTransaction().begin();
        entityManager.merge(evento);
        entityManager.getTransaction().commit();
    }

    public void eliminarEvento(long eventoId) {
        entityManager.getTransaction().begin();
        Evento evento = entityManager.find(Evento.class, eventoId);
        entityManager.remove(evento);
        entityManager.getTransaction().commit();
    }

    public Evento obtenerEventoPorId(long eventoId) {
        return entityManager.find(Evento.class, eventoId);
    }

    public List<Evento> obtenerTodosLosEventos() {
        Query query = entityManager.createQuery("SELECT * FROM Evento ");
        return query.getResultList();
    }
}
