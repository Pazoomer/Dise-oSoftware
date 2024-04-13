
package accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoMaestro {
    
    /**
     * Recupera el maestro harcodeado
     * @return
     */
    MaestroEditableDTO recuperarMaestro();
    
    /**
     * Recupera el maestro del parametro en la base de datos
     * @param maestro
     * @return
     */
    MaestroEditableDTO recuperarMaestroPorDTO(MaestroEditableDTO maestro);
    
    //Hace lo mismo que recuperar maestro pero mal
    //MaestroEditableDTO accesoCia();
    
    /**
     * Edita el maestro en la base de datos
     * @param maestro
     * @return
     */
    boolean editarMaestro(MaestroEditableDTO maestro);
    
    /**
     * Edita el calendario en la base de datos
     * @param calendario
     * @return 
     */
    boolean editarCalendario(List<EventoConsultableDTO> calendario);
}
