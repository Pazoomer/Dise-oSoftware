
package subsistemas.recuperarMaestro;

import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;

/**
 *
 * @author t1pas
 */
public class RecuperarMaestro implements IRecuperarMaestro {
    
    private final IConexionDAO conexion;
     private final Maestros maestros;

    public RecuperarMaestro(IConexionDAO conexion) {
        this.conexion = conexion;
        this.maestros = new Maestros(conexion);
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
