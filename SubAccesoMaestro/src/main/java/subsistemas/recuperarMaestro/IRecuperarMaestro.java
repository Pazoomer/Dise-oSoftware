
package subsistemas.recuperarMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;

/**
 *
 * @author t1pas
 */
public interface IRecuperarMaestro {
    
    MaestroEditableDTO recuperarMaestro()throws NegocioException;
    MaestroEditableDTO recuperarMaestro2(MaestroEditableDTO maestro)throws NegocioException;
}
