
package subsistemas.conexionGoogleMaps;

import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IAccesoGoogleMaps {
    
    List<String> accesoGoogleMaps()throws NegocioException;
}
