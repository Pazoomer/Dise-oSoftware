
package subsistemas.conexionGoogleMaps;

import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoGoogleMaps implements IAccesoGoogleMaps {

    private final IConexionDAO conexion;
    private final AccesoGoogleMaps accesoGoogleMaps;

    public FachadaAccesoGoogleMaps(IConexionDAO conexion) {
        this.conexion = conexion;
        this.accesoGoogleMaps = new AccesoGoogleMaps(conexion);
    }
    
    @Override
    public List<String> accesoEdificiosGoogleMaps() throws NegocioException {
        return accesoGoogleMaps.accesoEdificiosGoogleMaps();
    }

    @Override
    public List<String> accesoEdificiosPorCampusGoogleMaps(String campus) throws NegocioException {
       return accesoGoogleMaps.accesoEdificiosPorCampusGoogleMaps(campus);
    }

}
