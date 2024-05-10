
package Ejecutable;

import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import excepciones.NegocioException;
import presentacion.pantallas.PrincipalInicio;

/**
 *
 * @author t1pas
 */
public class CUAdministrarUbicaciones {

    /**
     * @param args the command line arguments
     */
    /*
    public static void main(String[] args) {
        
        IAccesoMaestro acceso = new FachadaAccesoMaestro();
        MaestroEditableDTO admin;
        try {
            admin = acceso.recuperarMaestro(new MaestroEditableDTO("1"));
            
            if (admin != null) {
                 new PrincipalInicio(admin).setVisible(true);
            }

        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
        
       
    }
    */
}
