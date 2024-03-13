
package subsistemas.accesoCalendario;

import DTOS.evento.EventoConsultableDTO;
import excepciones.PersistenciaException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class FachadaEditarCalendario implements IAccesoCalendario{

    @Override
    public List<EventoConsultableDTO> editarMaestro(List<EventoConsultableDTO> calendario) throws PersistenciaException {
        //TODO
        //Solo soy una fachada
        return calendario;
    }
    
}
