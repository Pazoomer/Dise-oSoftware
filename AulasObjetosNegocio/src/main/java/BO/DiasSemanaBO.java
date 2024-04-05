/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.DiasSemanaDAO;
import DAO.EventoDAO;
import java.util.Collections;
import java.util.List;
import objetosNegocio.DiasSemana;

/**
 *
 * @author pauli
 */
public class DiasSemanaBO {
    public boolean guardarDiasSemana(DiasSemana diasSemana) {
        try {
            if (diasSemana != null) { // Verifica que no sea null
                DiasSemanaDAO dsDao = new DiasSemanaDAO();
                dsDao.guardarDiasSemana(diasSemana); // Guardar dias
                dsDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return true; // Devolver true para indicar que los dias se guardaron correctamente
            } else {
                // Si DiasSemana es null, imprimir un mensaje de error
                System.out.println("Error al guardar los dias");
                return false; // Devolver false para indicar que ha habido un error al guardar los dias
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error
    }
    }

    public boolean actualizarDiasSemana(DiasSemana diasSemana) {
        try {
            if (diasSemana != null) { // Verifica que DiasSemana no sea null
                DiasSemanaDAO dsDao = new DiasSemanaDAO();
                dsDao.actualizarDiasSemana(diasSemana); // Actualizar DiasSemana
                dsDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return true; // Devolver true para indicar que el evento se actualizó correctamente
            } else {
                // Si DiasSemana es null, imprimir un mensaje de error
                System.out.println("Error al actualizar los dias");
                return false; // Devolver false para indicar que ha habido un error al actualizar el evento
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error
        }
    }

    public boolean eliminarDiasSemana(long diasSemanaId) {
        try {
            // Verificar que el ID no sea null ni cero
            if (diasSemanaId != 0) {
                DiasSemanaDAO dsDao = new DiasSemanaDAO();
                dsDao.eliminarDiasSemana(diasSemanaId); // Eliminar DiasSemnana
                dsDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return true; // Devolver true para indicar que el evento se eliminó correctamente
            } else {
                // Si el ID es null o cero, imprimir un mensaje de error
                System.out.println("El ID es incorrecto");
                return false; // Devolver false para indicar que ha habido un error al eliminar el evento
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error al eliminar el evento
        }
    }

    public DiasSemana obtenerDiasSemanaPorId(Long diasSemanaId) {
        try {
            if (diasSemanaId != null && diasSemanaId != 0) { // Verificar que el ID no sea null ni cero
                DiasSemanaDAO dsDao = new DiasSemanaDAO();
                DiasSemana diasSemana = dsDao.obtenerDiasSemanaPorId(diasSemanaId);
                dsDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return diasSemana; // Devolver el evento obtenido
            } else {
                // Si el ID es null o cero, imprimir un mensaje de error
                System.out.println("El ID no exitse");
                return null; // Devolver null para indicar que ha habido un error
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return null; // Devolver null para indicar que ha habido un error
        }
    }

    public List<DiasSemana> obtenerTodosLosDiasSemana() {
        try {
            DiasSemanaDAO dsDao = new DiasSemanaDAO();
            List<DiasSemana> diasSemana = dsDao.obtenerTodosLosDiasSemana();

            if (diasSemana != null) { // Verificar si la lista obtenida no es null
                return diasSemana; // Devolver la lista obtenida
            } else {
                // Si la lista es null, imprimir un mensaje de error
                System.out.println("La lista no existe");
                return Collections.emptyList(); // Devolver una lista vacía para indicar que no se han encontrado eventos
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return Collections.emptyList(); // Devolver una lista vacía para indicar que ha habido un error
        }
    }
    
}
