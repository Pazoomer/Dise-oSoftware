
package subsistemas.conexionCia;

import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import objetosNegocio.Maestro;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoCia implements IAccesoCia {
    
    private final IConexionDAO conexion;
    private final AccesoCia accesoCia;

    public FachadaAccesoCia(IConexionDAO conexion) {
        this.conexion = conexion;
        this.accesoCia = new AccesoCia(conexion);
    }

    //TODO
    //Solo soy una fachada
    @Override
    public MaestroEditableDTO accesoCia() throws NegocioException {
        return accesoCia.AccesoCia();
    }

}
