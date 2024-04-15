
package accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
//import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import objetosNegocio.IServicioConexion;
import objetosNegocio.ServicioConexion;

/**
 *
 * @author t1pas
 */
public class FachadaAccesoMaestro implements IAccesoMaestro{
    private ControlMaestros maestros;
    private IServicioConexion servicioConexion;
//    public FachadaAccesoMaestro(IConexionDAO conexion) {
//        this.maestros = new ControlMaestros(conexion);
//    }

    public FachadaAccesoMaestro() {
        this.maestros =new ControlMaestros();
        this.servicioConexion=new ServicioConexion();
    }
    
    @Override
    public boolean editarCalendario(List<EventoConsultableDTO> calendario)throws NegocioException {
       try{
           return maestros.editarCalendario(calendario);
       }catch(NegocioException e){
           throw e;
       }
    }
    
    @Override
    public boolean editarMaestro(MaestroEditableDTO maestro)throws NegocioException{
        try {
            return maestros.editarMaestro(maestro);
        } catch (NegocioException e) {
            throw e;
        }
    }
    
//    @Override
//    public MaestroEditableDTO recuperarMaestro()throws NegocioException{
//        try {
//            return maestros.recuperarMaestro();
//        } catch (NegocioException ex) {
//            Logger.getLogger(FachadaAccesoMaestro.class.getName()).log(Level.SEVERE, null, ex);
//            throw ex;
//        }
//    }

    @Override
    public MaestroEditableDTO recuperarMaestro(MaestroEditableDTO maestro)throws NegocioException{
        try {
            return maestros.recuperarMaestro(maestro);
        } catch (NegocioException ex) {
            Logger.getLogger(FachadaAccesoMaestro.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }

    @Override
    public void cerrarConexiones() throws NegocioException {
        try{
            servicioConexion.cerrarConexion();
        }catch(NegocioException e){
            throw e;
        }
    }
}
