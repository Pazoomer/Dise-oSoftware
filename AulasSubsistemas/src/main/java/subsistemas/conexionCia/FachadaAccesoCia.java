
package subsistemas.conexionCia;

import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoCia implements IAccesoCia {

    private final AccesoCia accesoCia;

    public FachadaAccesoCia() {
        this.accesoCia = new AccesoCia();
    }

    //TODO
    //Solo soy una fachada
    @Override
    public MaestroEditableDTO accesoCia() throws NegocioException {
        return accesoCia.AccesoCia();
    }

}
