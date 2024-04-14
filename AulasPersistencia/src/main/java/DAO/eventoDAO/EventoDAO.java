
package DAO.eventoDAO;

import conexion.IConexionDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;

/**
 * ESTA CLASE YA NO ES NECESARIA
 * @author pauli
 */
public class EventoDAO //implements IEventoDAO
{
    
    private final IConexionDAO conexion;

    public EventoDAO(IConexionDAO conexion) {
        this.conexion=conexion;
    }

   // @Override
    public EntidadEvento guardarEvento(EntidadEvento evento) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.getTransaction().commit();
        entityManager.close();
        return evento;
    }

    //@Override
    public EntidadEvento actualizarEvento(EntidadEvento evento) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        EntidadEvento eventoActualizado =entityManager.merge(evento);
        entityManager.getTransaction().commit();
        entityManager.close();
        return eventoActualizado;
    }

    //@Override
    public boolean eliminarEvento(long eventoId) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        EntidadEvento evento = entityManager.find(EntidadEvento.class, eventoId);
        entityManager.remove(evento);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    //@Override
    public EntidadEvento obtenerEventoPorId(long eventoId) {
        EntityManager entityManager = conexion.crearConexion();
        EntidadEvento eventoConsultado=entityManager.find(EntidadEvento.class, eventoId);
        entityManager.close();
        return eventoConsultado;
    }

  //  @Override
    public List<EntidadEvento> obtenerTodosLosEventosPorMaestro(EntidadMaestro maestro) {
        EntityManager entityManager = conexion.crearConexion();
        Query query = entityManager.createQuery("SELECT e FROM Evento e WHERE e.maestro = :maestro");
        query.setParameter("maestro", maestro);
        List<EntidadEvento> eventos=query.getResultList();
        entityManager.close();
        return eventos;
    }

}
