
package accesoUbicaciones;

import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoUbicaciones {
    public List<String> recuperarEdificios();
    
    public List<String> recuperarEdificiosPorCampus(String campus);
    
    List<String> accesoEdificiosGoogleMaps();
    
    List<String> accesoEdificiosPorCampusGoogleMaps(String campus);
}
