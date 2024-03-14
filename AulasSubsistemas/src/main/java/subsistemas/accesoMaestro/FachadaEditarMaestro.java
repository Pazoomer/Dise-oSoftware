
package subsistemas.accesoMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;

/**
 *
 * @author t1pas
 */
public class FachadaEditarMaestro implements IAccesoMaestro {

    private final Maestros maestros;

    public FachadaEditarMaestro() {
        this.maestros = new Maestros();
    }
   
    @Override
    public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws PersistenciaException {
        //TODO
        //Solo soy una fachada
        return maestros.editarMaestro(maestro);
    }

}
