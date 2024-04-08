
package subsistemas.accesoMaestro;

import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;

/**
 *
 * @author t1pas
 */
public class EditarMaestro implements IAccesoMaestro {

    private final ControlMaestro maestros;
    private final IConexionDAO conexion;

    public EditarMaestro(IConexionDAO conexion) {
        this.conexion = conexion;
        this.maestros = new ControlMaestro(conexion);
        
    }

    @Override
    public boolean editarMaestro(MaestroEditableDTO maestro) throws NegocioException {

        try {
            return maestros.editarMaestro(maestro);
        } catch (NegocioException e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
