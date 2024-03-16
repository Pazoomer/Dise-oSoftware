
package subsistemas.recuperarMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;

/**
 *
 * @author t1pas
 */
public interface IRecuperarMaestro {
    
    MaestroEditableDTO recuperarMaestro()throws PersistenciaException;
}
