
package accesoUbicaciones;

import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoUbicaciones implements IAccesoUbicaciones {
    
    private final ControlUbicaciones ubicaciones;

    public FachadaAccesoUbicaciones(IConexionDAO conexion) {
         this.ubicaciones = new ControlUbicaciones(conexion);
    }

    @Override
    public List<String> recuperarEdificios() {
        return ubicaciones.recuperarEdificios();
    }

    @Override
    public List<String> recuperarEdificiosPorCampus(String campus) {
        return ubicaciones.recuperarEdificiosPorCampus(campus);
    }
    
     @Override
    public List<String> accesoEdificiosGoogleMaps(){
        try {
            return ubicaciones.accesoEdificiosGoogleMaps();
        } catch (NegocioException ex) {
            Logger.getLogger(FachadaAccesoUbicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<String> accesoEdificiosPorCampusGoogleMaps(String campus){
        try {
            return ubicaciones.accesoEdificiosPorCampusGoogleMaps(campus);
        } catch (NegocioException ex) {
            Logger.getLogger(FachadaAccesoUbicaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
