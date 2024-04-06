
package DAO.ubicacionesDAO;

import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public interface IUbicacionesDAO {
    List<String> accesoEdificiosGoogleMaps()throws NegocioException;
    
    List<String> accesoEdificiosPorCampusGoogleMaps(String campus)throws NegocioException;
}
