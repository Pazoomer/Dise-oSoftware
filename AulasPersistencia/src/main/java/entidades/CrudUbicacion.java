/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import excepcioness.PersistenciaExceptionn;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author luiis
 */
public class CrudUbicacion {
    private EntityManager em;
    private CriteriaBuilder cb;
    private final static Logger LOG = Logger.getLogger(CrudUbicacion.class.getName());

    public CrudUbicacion() {
        em = ClaseConexion.getEntityManager();
        cb = em.getCriteriaBuilder();
    }
    public EntidadUbicacion obtenerUbicacion(EntidadUbicacion ubicacione)throws PersistenciaExceptionn{
        CriteriaQuery<EntidadUbicacion> criteria = cb.createQuery(EntidadUbicacion.class);
        Root<EntidadUbicacion> root = criteria.from(EntidadUbicacion.class);

        criteria.select(root).
            where(cb.equal(root.get("identificador"), ubicacione.getIdentificador()));

        TypedQuery<EntidadUbicacion> query = em.createQuery(criteria);
        EntidadUbicacion ubicacionEncontrada;
        try {
            ubicacionEncontrada = query.getSingleResult();
            return ubicacionEncontrada;
        } catch (NoResultException nre) {
            LOG.log(Level.SEVERE, nre.getMessage(), nre);
            throw new PersistenciaExceptionn("no se encontro la ubicacion con el identificador especificado");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("hubo un error al obtener la ubicacion");
        }
    }
    
    public List<EntidadUbicacion> obtenerUbicacionesPorCampus(EntidadCampus campus)throws PersistenciaExceptionn{
        CriteriaQuery<EntidadUbicacion> criteria = cb.createQuery(EntidadUbicacion.class);
        Root<EntidadUbicacion> root = criteria.from(EntidadUbicacion.class);

        criteria.select(root).
                where(cb.equal(root.get("campus").get("nombre"), campus.getNombre()));

        TypedQuery<EntidadUbicacion> query = em.createQuery(criteria);
        List<EntidadUbicacion> ubicacionesEncontrada;
        try {
            ubicacionesEncontrada = query.getResultList();
            return ubicacionesEncontrada;
        } catch (NoResultException nre) {
            LOG.log(Level.SEVERE, nre.getMessage(), nre);
            throw new PersistenciaExceptionn("no se encontro la ubicacion del campus con nombre especificado");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("hubo un error al obtener las ubicacion del campus "+campus.getNombre());
        }
    }
    
    public List<EntidadUbicacion> obtenerUbicaciones()throws PersistenciaExceptionn{
        CriteriaQuery<EntidadUbicacion> criteria = cb.createQuery(EntidadUbicacion.class);
        Root<EntidadUbicacion> root = criteria.from(EntidadUbicacion.class);

        criteria.select(root);

        TypedQuery<EntidadUbicacion> query = em.createQuery(criteria);
        List<EntidadUbicacion> ubicacionesEncontrada;
        try {
            ubicacionesEncontrada = query.getResultList();
            return ubicacionesEncontrada;
        } catch (NoResultException nre) {
            LOG.log(Level.SEVERE, nre.getMessage(), nre);
            throw new PersistenciaExceptionn("no se encontraron las ubicaciones");
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("hubo un error al obtener las ubicaciones");
        }
    }
    
    
}
