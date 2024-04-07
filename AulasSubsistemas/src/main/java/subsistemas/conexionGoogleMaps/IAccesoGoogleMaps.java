
package subsistemas.conexionGoogleMaps;

import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoGoogleMaps {
    
    List<String> accesoEdificiosGoogleMaps()throws NegocioException;
    
    List<String> accesoEdificiosPorCampusGoogleMaps(String campus)throws NegocioException;
}
