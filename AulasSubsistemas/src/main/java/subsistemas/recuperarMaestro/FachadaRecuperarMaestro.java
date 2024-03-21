
package subsistemas.recuperarMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.PersistenciaException;

/**
 *
 * @author t1pas
 */
public class FachadaRecuperarMaestro implements IRecuperarMaestro {

    private final Maestros maestros;

    public FachadaRecuperarMaestro() {
        this.maestros = new Maestros();
    }
    
    //TODO
    //Solo soy una fachada
    @Override
    public MaestroEditableDTO recuperarMaestro() throws PersistenciaException {
        return maestros.recuperarMaestro();
    }

}
