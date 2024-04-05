
package BO;

import DAO.maestroDAO.MaestroDAO;
import DAO.exceptions.PersistenciaException;
import DAO.exceptions.ValidacionException;
import conexion.IConexionDAO;
import java.util.Collections;
import java.util.List;
import objetosNegocio.Maestro;

/**
 *
 * @author pauli
 */
public class MaestroBO {
    
    private final IConexionDAO conexion;

    public MaestroBO(IConexionDAO conexion) {
        this.conexion=conexion;
    }

    public boolean guardarMaestro(MaestroEditableDTO maestroEditableDTO) throws ValidacionException, PersistenciaException {

        if (maestro != null) { // Verifica que el maestro no sea null
            try {
                MaestroDAO mDao = new MaestroDAO(conexion);
                mDao.guardarMaestro(maestro); // Guardar maestro
                return true; // Devolver true para indicar que se guardo correctamente
            } catch (Exception e) {
                // Manejar la excepción
                throw new PersistenciaException(e); // Imprimir la traza de la excepción para depuración
            }
        } else {
            // Si el maestro es null, imprimir un mensaje de error
            throw new ValidacionException("El maestro es null");
        }

    }

    public boolean actualizarMaestro(Maestro maestro) {
        try {
            if (maestro != null) { // Verifica que el maestro no sea null
                MaestroDAO mDao = new MaestroDAO(conexion);
                mDao.actualizarMaestro(maestro); // Actualizarr maestro
                return true; // Devolver true para indicar que se actualizo correctamente
            } else {
                // Si el maestro es null, imprimir un mensaje de error
                System.out.println("El maestro no existe");
                return false; // Devolver false para indicar que ha habido un error al actualizar maestro
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error
        }
    }

    public boolean eliminarMaestro(long maestroId) {
        try {
            // Verificar que el ID del maestro no sea null ni cero
            if (maestroId != 0) {
                MaestroDAO eDao = new MaestroDAO(conexion);
                eDao.eliminarMaestro(maestroId); // Eliminar maetsro
                return true; // Devolver true para indicar que el maestro se eliminó correctamente
            } else {
                // Si el ID del maestro es null o cero, imprimir un mensaje de error
                System.out.println("El ID es incorrecto");
                return false; // Devolver false para indicar que ha habido un error al eliminar maestro
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error al eliminar el evento
        }
    }

    public Maestro obtenerMaestroPorId(Long maestroId) {
        try {
            if (maestroId != null && maestroId != 0) { // Verificar que el ID del maestro no sea null ni cero
                MaestroDAO mDao = new MaestroDAO(conexion);
                Maestro evento = mDao.obtenerMaestroPorId(maestroId);
                return evento; // Devolver el maestro obtenido
            } else {
                // Si el ID del maestro es null o cero, imprimir un mensaje de error
                System.out.println("El ID no existe");
                return null; // Devolver null para indicar que ha habido un error
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return null; // Devolver null para indicar que ha habido un error
        }
    }

    public List<Maestro> obtenerTodosLosMaestros() {
        try {
            MaestroDAO mDao = new MaestroDAO(conexion);
            List<Maestro> maestros = mDao.obtenerTodosLosMaestros();

            if (maestros != null) { // Verificar si la lista de maestros obtenida no es null
                return maestros; // Devolver la lista de maestros obtenida
            } else {
                // Si la lista de maestros es null, imprimir un mensaje de error
                System.out.println("La lista de maestros no existe");
                return Collections.emptyList(); // Devolver una lista vacía para indicar que no se han encontrado eventos
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return Collections.emptyList(); // Devolver una lista vacía para indicar que ha habido un error
        }
    }
}
