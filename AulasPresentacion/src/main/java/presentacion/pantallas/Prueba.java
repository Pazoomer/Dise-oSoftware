
package presentacion.pantallas;

import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import conexion.ConexionDAO;
import conexion.IConexionDAO;

/**
 *
 * @author 
 */
public class Prueba {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        IConexionDAO conexion = new ConexionDAO();

        IAccesoMaestro accesoMaestro=new FachadaAccesoMaestro(conexion);
        MaestroEditableDTO maestroEditable = accesoMaestro.recuperarMaestro();
        
        //IRecuperarMaestroBO recuperarMaestroBo = new RecuperarMaestroBO(conexion);
        //MaestroEditableDTO maestroEditable = recuperarMaestroBo.recuperarMaestro();

        if (maestroEditable != null) {
            new PrincipalMaestro(maestroEditable, conexion).setVisible(true);
        }

    }

}
