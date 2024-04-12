
package subsistemas.recuperarMaestro;

import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;

/**
 *
 * @author t1pas
 */
public class RecuperarMaestro implements IRecuperarMaestro {
    
   // private final IConexionDAO conexion;
     private final ControlRecuperarMaestro maestros;

    public RecuperarMaestro() {
        //this.conexion = conexion;
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
