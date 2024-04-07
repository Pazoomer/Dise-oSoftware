
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import com.toedter.calendar.JCalendar;
import excepciones.NegocioException;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author t1pas
 */
public interface IAccesoCalendario {
    /*
    List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario,
            EventoConsultableDTO evento, String tipoOperacion) throws NegocioException;
    EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento) throws NegocioException;
    
    EventoConsultableDTO inicializarCalendario(List<EventoConsultableDTO> calendario,JCalendar jCalendar,JTable tabla);
    EventoConsultableDTO refreshCalendario(List<EventoConsultableDTO> calendario,JCalendar jCalendar,JTable tabla);
    */
    boolean editarCalendario(List<EventoConsultableDTO> calendario);

//    List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario,
//            EventoConsultableDTO evento, String tipoOperacion) throws NegocioException;
//
//    EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento) throws NegocioException;
}
