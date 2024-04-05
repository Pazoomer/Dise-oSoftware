/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.MaestroDAO;
import java.util.Collections;
import java.util.List;
import objetosNegocio.Maestro;

/**
 *
 * @author pauli
 */
public class MaestroBO {
    
    public boolean guardarMaestro(Maestro maestro) {
        try {
            if (maestro != null) { // Verifica que el maestro no sea null
                MaestroDAO mDao = new MaestroDAO();
                mDao.guardarMaestro(maestro); // Guardar maestro
                mDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return true; // Devolver true para indicar que se guardo correctamente
            } else {
                // Si el maestro es null, imprimir un mensaje de error
                System.out.println("El maestro no existe");
                return false; // Devolver false para indicar que ha habido un error al guardar maestro
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error
        }
    }

    public boolean actualizarMaestro(Maestro maestro) {
        try {
            if (maestro != null) { // Verifica que el maestro no sea null
                MaestroDAO mDao = new MaestroDAO();
                mDao.actualizarMaestro(maestro); // Actualizarr maestro
                mDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
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
                MaestroDAO eDao = new MaestroDAO();
                eDao.eliminarMaestro(maestroId); // Eliminar maetsro
                eDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
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
                MaestroDAO mDao = new MaestroDAO();
                Maestro evento = mDao.obtenerMaestroPorId(maestroId);
                mDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
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
            MaestroDAO mDao = new MaestroDAO();
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
