/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excepcioness.PersistenciaExceptionn;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luiis
 */
public class CrudEvento {
    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG = Logger.getLogger(CrudEvento.class.getName());

    public CrudEvento() {
        em = ClaseConexion.getEntityManager();
        cb = em.getCriteriaBuilder();
    }

    public boolean agregarEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        try {
            if (em.find(EntidadMaestro.class, evento.getMaestro().getIdBd()) != null) {
                em.getTransaction().begin();
                em.persist(evento);
                em.getTransaction().commit();
                return true;
            }
            LOG.log(Level.SEVERE, "el maestro asignado al evento no esta registrado");
            return false;
        } catch(IllegalArgumentException | NullPointerException ex){
            LOG.log(Level.SEVERE, ex.getMessage(), ex);
            throw new PersistenciaExceptionn("hubo un error al agregar el evento");
        }
        catch (Exception e) {
            //em.getTransaction().rollback();
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("hubo un error al agregar el evento");
        }
    }

    public EntidadEvento editarEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        EntidadEvento eventoEditado;
        if (em.find(EntidadEvento.class, evento.getId()) != null) {
            try {
                em.getTransaction().begin();
                eventoEditado = em.merge(evento);
                em.getTransaction().commit();
                return eventoEditado;
            } catch (Exception e) {
                em.getTransaction().rollback();
                LOG.log(Level.SEVERE, e.getMessage(), e);
                throw new PersistenciaExceptionn("hubo un error al editar el evento");
            }
        }
        return null;
    }

    public boolean eliminarEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        EntidadEvento eventoEliminado = em.find(EntidadEvento.class, evento.getId());
        if (eventoEliminado != null) {
            try {
                em.getTransaction().begin();
                em.remove(eventoEliminado);
                em.getTransaction().commit();
                return true;
            } catch (Exception e) {
                em.getTransaction().rollback();
                LOG.log(Level.SEVERE, e.getMessage(), e);
                throw new PersistenciaExceptionn("hubo un error al eliminar el evento");
            }
        }
        return false;
    }

    public EntidadEvento obtenerEvento(EntidadEvento evento) throws PersistenciaExceptionn {
        CriteriaQuery<EntidadEvento> criteria = cb.createQuery(EntidadEvento.class);
        Root<EntidadEvento> root = criteria.from(EntidadEvento.class);

        criteria.select(root).
                where(cb.equal(root.get("nombre"), evento.getNombre()));

        TypedQuery<EntidadEvento> query = em.createQuery(criteria);
        EntidadEvento eventoEncontrado;
        try {
            eventoEncontrado = query.getSingleResult();
            return eventoEncontrado;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("hubo un error al obtener el evento");
        }
    }
    
}
