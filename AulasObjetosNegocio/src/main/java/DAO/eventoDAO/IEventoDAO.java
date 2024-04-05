
package DAO.eventoDAO;

import java.util.List;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas
 */
public interface IEventoDAO {
    Evento guardarEvento(Evento evento);

    Evento actualizarEvento(Evento evento);

    boolean eliminarEvento(long eventoId);

    Evento obtenerEventoPorId(long eventoId);

    List<Evento> obtenerTodosLosEventosPorMaestro(Maestro maestro);
}
