
package Ejecutable;

import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import excepciones.NegocioException;
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

        IAccesoMaestro acceso = new FachadaAccesoMaestro();
        MaestroEditableDTO maestro;
        try {
            maestro = acceso.recuperarMaestro(new MaestroEditableDTO("1"));
            
            if (maestro != null) {
                System.out.println(maestro.toString());
                new PrincipalMaestro(maestro,null).setVisible(true);
            }

        } catch (NegocioException e) {
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
