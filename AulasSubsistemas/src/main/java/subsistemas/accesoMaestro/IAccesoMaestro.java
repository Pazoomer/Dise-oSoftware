
package subsistemas.accesoMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;

/**
 *
 * @author t1pas
 */
public interface IAccesoMaestro {

    MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws PersistenciaException;
}
