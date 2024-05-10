
package Ejecutable;

//import BO.recuperarMaestroBO.IRecuperarMaestroBO;
//import BO.recuperarMaestroBO.RecuperarMaestroBO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
//import conexion.ConexionDAO;
//import conexion.IConexionDAO;
import accesoMaestro.IAccesoMaestro;
import excepciones.NegocioException;
import java.util.Calendar;
import java.util.List;
import presentacion.pantallas.PrincipalMaestro;
/**
 *
 * @author 
 */
public class Ejecutable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//
//        IAccesoMaestro acceso = new FachadaAccesoMaestro();
//        MaestroEditableDTO maestro;
//        try {
//            maestro = acceso.recuperarMaestro(new MaestroEditableDTO("1"));
//            
//            if (maestro != null) {
////                List<EventoConsultableDTO> eventos=maestro.getCalendario();
////                eventos.forEach(e->{
////                    System.out.println("fecha inicio: "+
////                            e.getFechaInicio().get(Calendar.DAY_OF_MONTH)+"/"+
////                            e.getFechaInicio().get(Calendar.MONTH)+"/"+
////                            e.getFechaInicio().get(Calendar.YEAR)+" "+
////                            e.getFechaInicio().get(Calendar.HOUR_OF_DAY)+":"+
////                            e.getFechaInicio().get(Calendar.MINUTE));
////                });
//                System.out.println(maestro.toString());
//                new PrincipalMaestro(maestro).setVisible(true);
//            }
//
//        } catch (NegocioException e) {
//            System.out.println(e.getMessage());
//        }
        
//        
//        IConexionDAO conexion = new ConexionDAO();
//
//        IRecuperarMaestroBO recuperarMaestroBo = new RecuperarMaestroBO(conexion);
//        MaestroEditableDTO maestroEditable = recuperarMaestroBo.recuperarMaestro();
//
//        if (maestroEditable != null) {
//            new PrincipalMaestro(maestroEditable, conexion).setVisible(true);
//        }

    }

}
