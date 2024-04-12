
package accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoMaestro implements IAccesoMaestro{
    private final ControlMaestros maestros;
    
    public FachadaAccesoMaestro(IConexionDAO conexion) {
        this.maestros = new ControlMaestros(conexion);
    }
    
    @Override
    public boolean editarCalendario(List<EventoConsultableDTO> calendario) {
       return maestros.editarCalendario(calendario);
    }
    
    @Override
    public boolean editarMaestro(MaestroEditableDTO maestro){

        try {
            return maestros.editarMaestro(maestro);
        } catch (NegocioException e) {
            try {
                throw new NegocioException(e.getMessage());
            } catch (NegocioException ex) {
                Logger.getLogger(FachadaAccesoMaestro.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }
    
    //Solo soy una fachada
    @Override
    public MaestroEditableDTO accesoCia(){
        try {
            return maestros.AccesoCia();
        } catch (NegocioException ex) {
            Logger.getLogger(FachadaAccesoMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public MaestroEditableDTO recuperarMaestro(){
        try {
            return maestros.recuperarMaestro();
        } catch (NegocioException ex) {
            Logger.getLogger(FachadaAccesoMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public MaestroEditableDTO recuperarMaestro2(MaestroEditableDTO maestro){
        try {
            return maestros.recuperarMaestro2(maestro);
        } catch (NegocioException ex) {
            Logger.getLogger(FachadaAccesoMaestro.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
