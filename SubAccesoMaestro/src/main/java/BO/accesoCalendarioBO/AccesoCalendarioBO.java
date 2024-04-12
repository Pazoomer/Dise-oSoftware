
package BO.accesoCalendarioBO;

import DTOS.evento.EventoConsultableDTO;
import conexion.IConexionDAO;
import java.util.List;
import subsistemas.accesoCalendario.EditarCalendario;
import subsistemas.accesoCalendario.IAccesoCalendario;

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
      IAccesoCalendario accesoCalendario=new EditarCalendario(conexion);
      return accesoCalendario.editarCalendario(calendario);
    }
    
}
