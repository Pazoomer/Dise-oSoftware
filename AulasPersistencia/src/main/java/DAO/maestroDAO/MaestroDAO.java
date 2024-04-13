
package DAO.maestroDAO;

import conexion.IConexionDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.EntidadMaestro;

/**
 *
 * @author pauli
 */
public class MaestroDAO implements IMaestroDAO{
    private final IConexionDAO conexion;

    public MaestroDAO(IConexionDAO conexion) {
        this.conexion=conexion;
    }

    @Override
    public EntidadMaestro guardarMaestro(EntidadMaestro maestro) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        entityManager.persist(maestro);
        entityManager.getTransaction().commit();
        entityManager.close();
        return maestro;
    }

    @Override
    public EntidadMaestro actualizarMaestro(EntidadMaestro maestro) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        EntidadMaestro maestroActualizado = entityManager.merge(maestro);
        entityManager.getTransaction().commit();
        entityManager.close();
        return maestroActualizado;
    }

    @Override
    public boolean eliminarMaestro(long maestroId) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        EntidadMaestro maestro = entityManager.find(EntidadMaestro.class, maestroId);
        entityManager.remove(maestro);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public EntidadMaestro obtenerMaestroPorId(long maestroId) {
        EntityManager entityManager = conexion.crearConexion();
        EntidadMaestro maestroConsultado = entityManager.find(EntidadMaestro.class, maestroId);
        entityManager.close();
        return maestroConsultado;
    }

    @Override
    public List<EntidadMaestro> obtenerTodosLosMaestros() {
        EntityManager entityManager = conexion.crearConexion();
        Query query = entityManager.createQuery("SELECT m FROM Maestro m");
        List<EntidadMaestro> maestros = query.getResultList();
        entityManager.close();
        return maestros;
    }

}
