
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoCalendario {
    List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario) throws PersistenciaException;
}
