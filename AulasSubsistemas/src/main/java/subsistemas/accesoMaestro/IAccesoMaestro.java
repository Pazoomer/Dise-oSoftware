
package subsistemas.accesoMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;

/**
 *
 * @author t1pas
 */
public interface IAccesoMaestro {

    MaestroEditableDTO agregarEventoCalendario(MaestroEditableDTO maestro) throws NegocioException;
    MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws NegocioException;
}
