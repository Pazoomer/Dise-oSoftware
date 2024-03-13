
package subsistemas.accesoMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;

/**
 *
 * @author t1pas
 */
public class FachadaEditarMaestro implements IAccesoMaestro {

    @Override
    public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws PersistenciaException {
        //TODO
        //Solo soy una fachada
        return maestro;
    }

}
