
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class FachadaEditarCalendario implements IAccesoCalendario{

    private final Calendarios calendarios;

    public FachadaEditarCalendario() {
        this.calendarios = new Calendarios();
    }
    
    @Override
    public List<EventoConsultableDTO> editarCalendario(List<EventoConsultableDTO> calendario) throws PersistenciaException {
        //TODO
        //Solo soy una fachada
        return calendarios.editarCalendario(calendario);
    }
    
}
