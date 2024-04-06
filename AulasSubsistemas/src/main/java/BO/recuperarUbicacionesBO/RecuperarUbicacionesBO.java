
package BO.recuperarUbicacionesBO;

import conexion.IConexionDAO;
import java.util.List;
import subsistemas.recuperarUbicaciones.RecuperarUbicaciones;
import subsistemas.recuperarUbicaciones.IRecuperarUbicaciones;

/**
 *
 * @author t1pas
 */
public class RecuperarUbicacionesBO implements IRecuperarUbicacionesBO {
    
    private final IConexionDAO conexion;

    public RecuperarUbicacionesBO(IConexionDAO conexion) {
        this.conexion = conexion;
    }

    @Override
    public List<String> recuperarCampus() {
        IRecuperarUbicaciones dao = new RecuperarUbicaciones(conexion);
        return dao.recuperarEdificios();
    }

    @Override
    public List<String> recuperarEdificiosPorCampus(String campus) {
        IRecuperarUbicaciones dao = new RecuperarUbicaciones(conexion);
        return dao.recuperarEdificiosPorCampus(campus);
    }

}
