
package subsistemas.conexionCia;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas
 */
public interface IAccesoCia {
    MaestroEditableDTO accesoCia()throws NegocioException;
}
