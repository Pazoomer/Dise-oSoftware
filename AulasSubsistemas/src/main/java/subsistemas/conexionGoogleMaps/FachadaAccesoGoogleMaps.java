
package subsistemas.conexionGoogleMaps;

import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoGoogleMaps implements IAccesoGoogleMaps{
    
     private final AccesoGoogleMaps accesoGoogleMaps;

    public FachadaAccesoGoogleMaps() {
        this.accesoGoogleMaps = new AccesoGoogleMaps();
    }
    
    //TODO
    //Solo soy una fachada
     @Override
    public List<String> accesoGoogleMaps() throws NegocioException {
        return accesoGoogleMaps.accesoGoogleMaps();
    }

}
