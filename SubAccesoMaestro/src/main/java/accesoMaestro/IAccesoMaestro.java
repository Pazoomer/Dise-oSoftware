
package accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoMaestro {
    
    /**
     * Recupera el maestro harcodeado
     * @return
     * @throws excepciones.NegocioException
     */
//    MaestroEditableDTO recuperarMaestro()throws NegocioException;
    
    /**
     * Recupera el maestro del parametro en la base de datos
     * @param maestro
     * @return
     * @throws excepciones.NegocioException
     */
    MaestroEditableDTO recuperarMaestro(MaestroEditableDTO maestro)throws NegocioException;
    
    //Hace lo mismo que recuperar maestro pero mal
    //MaestroEditableDTO accesoCia();
    
    /**
     * Edita el maestro en la base de datos
     * @param maestro
     * @return
     * @throws excepciones.NegocioException
     */
    boolean editarMaestro(MaestroEditableDTO maestro)throws NegocioException;
    
    boolean agregarEventoCalendario(MaestroEditableDTO maestro, EventoConsultableDTO evento)throws NegocioException;
}
