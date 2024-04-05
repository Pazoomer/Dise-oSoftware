/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DAO.EventoDAO;
import java.util.Collections;
import java.util.List;
import javax.persistence.Query;
import objetosNegocio.Evento;

/**
 *
 * @author pauli
 */
public class EventoBO {

    public EventoBO() {
    }

    /**
     *
     * @param evento
     * @return
     */
    public boolean guardarEvento(Evento evento) {
        try {
            if (evento != null) { // Verifica que el evento no sea null
                EventoDAO eDao = new EventoDAO();
                eDao.guardarEvento(evento); // Guardar el evento
                eDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return true; // Devolver true para indicar que el evento se actualizó correctamente
            } else {
                // Si el evento es null, imprimir un mensaje de error
                System.out.println("El evento que quiere guardar no existe");
                return false; // Devolver false para indicar que ha habido un error al guardar el evento
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error
        }

    }

    /**
     *
     * @param evento
     * @return
     */
    public boolean actualizarEvento(Evento evento) {
        try {
            if (evento != null) { // Verifica que el evento no sea null
                EventoDAO eDao = new EventoDAO();
                eDao.actualizarEvento(evento); // Actualizar el evento
                eDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return true; // Devolver true para indicar que el evento se actualizó correctamente
            } else {
                // Si el evento es null, imprimir un mensaje de error
                System.out.println("El evento no existe");
                return false; // Devolver false para indicar que ha habido un error al actualizar el evento
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error
        }
    }

    public boolean eliminarEvento(long eventoId) {
        try {
            // Verificar que el ID del evento no sea null ni cero
            if (eventoId != 0) {
                EventoDAO eDao = new EventoDAO();
                eDao.eliminarEvento(eventoId); // Eliminar el evento
                eDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return true; // Devolver true para indicar que el evento se eliminó correctamente
            } else {
                // Si el ID del evento es null o cero, imprimir un mensaje de error
                System.out.println("El ID es incorrecto");
                return false; // Devolver false para indicar que ha habido un error al eliminar el evento
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return false; // Devolver false para indicar que ha habido un error al eliminar el evento
        }
    }

    public Evento obtenerEventoPorId(Long eventoId) {
        try {
            if (eventoId != null && eventoId != 0) { // Verificar que el ID del evento no sea null ni cero
                EventoDAO eDao = new EventoDAO();
                Evento evento = eDao.obtenerEventoPorId(eventoId);
                eDao.close(); // Cerrar el DAO después de usarlo para evitar pérdidas de recursos
                return evento; // Devolver el evento obtenido
            } else {
                // Si el ID del evento es null o cero, imprimir un mensaje de error
                System.out.println("El ID no exitse");
                return null; // Devolver null para indicar que ha habido un error
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return null; // Devolver null para indicar que ha habido un error
        }
    }

    public List<Evento> obtenerTodosLosEventos() {
        try {
            EventoDAO eDao = new EventoDAO();
            List<Evento> eventos = eDao.obtenerTodosLosEventos();

            if (eventos != null) { // Verificar si la lista de eventos obtenida no es null
                return eventos; // Devolver la lista de eventos obtenida
            } else {
                // Si la lista de eventos es null, imprimir un mensaje de error
                System.out.println("La lista de eventos no existe");
                return Collections.emptyList(); // Devolver una lista vacía para indicar que no se han encontrado eventos
            }
        } catch (Exception e) {
            // Manejar la excepción
            e.printStackTrace(); // Imprimir la traza de la excepción para depuración
            return Collections.emptyList(); // Devolver una lista vacía para indicar que ha habido un error
        }
    }

}
