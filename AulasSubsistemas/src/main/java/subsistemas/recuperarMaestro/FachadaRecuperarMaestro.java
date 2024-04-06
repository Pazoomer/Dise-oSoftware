
package subsistemas.recuperarMaestro;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;

/**
 *
 * @author t1pas
 */
public class FachadaRecuperarMaestro implements IRecuperarMaestro {

    private final ControlRecuperarMaestro maestros;

    public FachadaRecuperarMaestro() {
        this.maestros = new ControlRecuperarMaestro();
    }
    
    //TODO
    //Solo soy una fachada
    @Override
    public MaestroEditableDTO recuperarMaestro() throws NegocioException {
        return maestros.recuperarMaestro();
    }

    @Override
    public MaestroEditableDTO recuperarMaestro2(MaestroEditableDTO maestro) throws NegocioException {
        return maestros.recuperarMaestro2(maestro);
    }

}
