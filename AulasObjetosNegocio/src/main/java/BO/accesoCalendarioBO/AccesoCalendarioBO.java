
package BO.accesoCalendarioBO;

import DTOS.evento.EventoConsultableDTO;
import conexion.IConexionDAO;
import java.util.List;

/**
 *
 * @author t1pas
 */
public class AccesoCalendarioBO implements IAccesoCalendarioBO{
    
    private final IConexionDAO conexion;

    public AccesoCalendarioBO(IConexionDAO conexion) {
        this.conexion = conexion;
    }

    @Override
    public boolean editarCalendario(List<EventoConsultableDTO> calendario) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
