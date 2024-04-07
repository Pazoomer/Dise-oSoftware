
package subsistemas.recuperarUbicaciones;

import conexion.IConexionDAO;
import java.util.List;

/**
 *
 * @author luiis
 */
public class RecuperarUbicaciones implements IRecuperarUbicaciones {
    
    private final IConexionDAO conexion;
    private final Ubicaciones ubicaciones;

    public RecuperarUbicaciones(IConexionDAO conexion) {
         this.ubicaciones = new Ubicaciones(conexion);
        this.conexion = conexion;
    }

    @Override
    public List<String> recuperarEdificios() {
        return ubicaciones.recuperarEdificios();
    }

    @Override
    public List<String> recuperarEdificiosPorCampus(String campus) {
        return ubicaciones.recuperarEdificiosPorCampus(campus);
    }
    
}
