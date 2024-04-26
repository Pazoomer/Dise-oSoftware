
package accesoUbicaciones;

import DTOS.campus.*;
import excepciones.NegocioException;
import java.util.List;

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
    public UbicacionDTO recuperarCampus(UbicacionDTO ubicacion)throws NegocioException {
        return ubicaciones.recuperarEdificio(ubicacion);
    }

    @Override
    public List<UbicacionDTO> recuperarEdificiosPorCampus(CampusConsultableDTO campus) throws NegocioException{
        return ubicaciones.recuperarEdificiosPorCampus(campus);
    }

}
