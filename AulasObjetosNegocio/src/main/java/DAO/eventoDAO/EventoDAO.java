
package DAO.eventoDAO;

import conexion.IConexionDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;

/**
 *
 * @author pauli
 */
public class EventoDAO implements IEventoDAO{
    
    private final IConexionDAO conexion;

    public EventoDAO(IConexionDAO conexion) {
        this.conexion=conexion;
    }

    @Override
    public Evento guardarEvento(Evento evento) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        entityManager.persist(evento);
        entityManager.getTransaction().commit();
        entityManager.close();
        return evento;
    }

    @Override
    public Evento actualizarEvento(Evento evento) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        Evento eventoActualizado =entityManager.merge(evento);
        entityManager.getTransaction().commit();
        entityManager.close();
        return eventoActualizado;
    }

    @Override
    public boolean eliminarEvento(long eventoId) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        Evento evento = entityManager.find(Evento.class, eventoId);
        entityManager.remove(evento);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public Evento obtenerEventoPorId(long eventoId) {
        EntityManager entityManager = conexion.crearConexion();
        Evento eventoConsultado=entityManager.find(Evento.class, eventoId);
        entityManager.close();
        return eventoConsultado;
    }

    @Override
    public List<Evento> obtenerTodosLosEventosPorMaestro(Maestro maestro) {
        EntityManager entityManager = conexion.crearConexion();
        Query query = entityManager.createQuery("SELECT e FROM Evento e WHERE e.maestro = :maestro");
        query.setParameter("maestro", maestro);
        List<Evento> eventos=query.getResultList();
        entityManager.close();
        return eventos;
    }
}
