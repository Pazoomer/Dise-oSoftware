
package DAO.maestroDAO;

import conexion.IConexionDAO;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import objetosNegocio.Maestro;

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
    public Maestro guardarMaestro(Maestro maestro) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        entityManager.persist(maestro);
        entityManager.getTransaction().commit();
        entityManager.close();
        return maestro;
    }

    @Override
    public Maestro actualizarMaestro(Maestro maestro) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        Maestro maestroActualizado = entityManager.merge(maestro);
        entityManager.getTransaction().commit();
        entityManager.close();
        return maestroActualizado;
    }

    @Override
    public boolean eliminarMaestro(long maestroId) {
        EntityManager entityManager = conexion.crearConexion();
        entityManager.getTransaction().begin();
        Maestro maestro = entityManager.find(Maestro.class, maestroId);
        entityManager.remove(maestro);
        entityManager.getTransaction().commit();
        entityManager.close();
        return true;
    }

    @Override
    public Maestro obtenerMaestroPorId(long maestroId) {
        EntityManager entityManager = conexion.crearConexion();
        Maestro maestroConsultado = entityManager.find(Maestro.class, maestroId);
        entityManager.close();
        return maestroConsultado;
    }

    @Override
    public List<Maestro> obtenerTodosLosMaestros() {
        EntityManager entityManager = conexion.crearConexion();
        Query query = entityManager.createQuery("SELECT m FROM Maestro m");
        List<Maestro> maestros = query.getResultList();
        entityManager.close();
        return maestros;
    }

}
