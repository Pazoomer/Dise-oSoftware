
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoCalendario {
    List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario,
            EventoConsultableDTO evento, String tipoOperacion) throws NegocioException;
    EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento) throws NegocioException;
}
