
package accesoUbicaciones;

import DTOS.campus.*;
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

    public FachadaAccesoUbicaciones() {
        this.ubicaciones = new ControlUbicaciones();
    }

    @Override
    public UbicacionDTO recuperarEdificio(UbicacionDTO ubicacion)throws NegocioException {
        return ubicaciones.recuperarEdificio(ubicacion);
    }

    @Override
    public List<UbicacionDTO> recuperarEdificiosPorCampus(CampusConsultableDTO campus) throws NegocioException{
        return ubicaciones.recuperarEdificiosPorCampus(campus);
    }

    @Override
    public List<UbicacionDTO> recuperarEdificios() throws NegocioException {
        return ubicaciones.recuperarEdificios();
    }

}
