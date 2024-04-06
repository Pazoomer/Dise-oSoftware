
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import com.toedter.calendar.JCalendar;
import excepciones.NegocioException;
import java.util.List;
import javax.swing.JTable;

/**
 *
 * @author t1pas, luisa morales
 */
public class FachadaAccederCalendario implements IAccesoCalendario{

    private final ControlCalendarios calendarios;

    public FachadaAccederCalendario() {
        this.calendarios = new ControlCalendarios();
    }
    
    @Override
    public List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario, EventoConsultableDTO evento,
            String tipoOperacion) throws NegocioException {
        List<EventoConsultableDTO> calendarioEditado=null;
        switch(tipoOperacion){
            case "agregar":
                try{
                    calendarioEditado=calendarios.agregarEvento(calendario, evento);
                }catch(NegocioException e){
                    throw new NegocioException(e.getMessage());
                }
                break;
            case "editar":
                try{
                    calendarioEditado=calendarios.editarEvento(calendario, evento);
                }catch(NegocioException e){
                    throw new NegocioException(e.getMessage());
                }
                break;
            case "eliminar":
                try{
                    calendarioEditado=calendarios.eliminarEvento(calendario, evento);
                }catch(NegocioException e){
                    throw new NegocioException(e.getMessage());
                }
                break;
        }
        return calendarioEditado;
    }

    @Override
    public EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento) throws NegocioException {
        EventoConsultableDTO eventoObtenido;
        try{
            eventoObtenido=calendarios.obtenerEvento(evento);
            return eventoObtenido;
        }catch(NegocioException e){
            throw new NegocioException(e.getMessage());
        }
    }

    @Override
    public EventoConsultableDTO inicializarCalendario(List<EventoConsultableDTO> calendario, JCalendar jcalendar, JTable tabla){
        return calendarios.inicializarCalendario(jcalendar, calendario, tabla);
    }

    @Override
    public EventoConsultableDTO refreshCalendario(List<EventoConsultableDTO> calendario, JCalendar jCalendar, JTable tabla) {
        return calendarios.refrescarCalendario(jCalendar, calendario, tabla);
    }
}
