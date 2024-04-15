
package presentacion.pantallas;

//import BO.recuperarMaestroBO.IRecuperarMaestroBO;
//import BO.recuperarMaestroBO.RecuperarMaestroBO;
import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
//import conexion.ConexionDAO;
//import conexion.IConexionDAO;
import accesoMaestro.IAccesoMaestro;
import excepciones.NegocioException;
/**
 *
 * @author 
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IAccesoMaestro acceso=new FachadaAccesoMaestro();
        MaestroEditableDTO maestro;
        try{
            maestro=acceso.recuperarMaestro(new MaestroEditableDTO(1L));
            System.out.println(maestro.toString());
            new PrincipalMaestro(maestro).setVisible(true);
        }catch(NegocioException e){
            System.out.println(e.getMessage());
        }
        
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
