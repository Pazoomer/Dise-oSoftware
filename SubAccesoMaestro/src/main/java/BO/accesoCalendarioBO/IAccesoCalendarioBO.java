
package BO.accesoCalendarioBO;

import DTOS.evento.EventoConsultableDTO;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoCalendarioBO {
    boolean editarCalendario(List<EventoConsultableDTO> calendario);
}
