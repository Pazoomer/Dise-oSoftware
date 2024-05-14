
package Ejecutable;

import DTOS.usuarios.UsuarioDTO;
import accesoUsuarios.FachadaAccesoUsuarios;
import accesoUsuarios.IAccesoUsuarios;
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
    
    public static void main(String[] args) {
        
        IAccesoUsuarios acceso = new FachadaAccesoUsuarios();
        UsuarioDTO admin;
        try {
            admin = acceso.iniciarSesion("23","pass123");
            
            if (admin != null) {
                 new PrincipalInicio(admin).setVisible(true);
            }

        } catch (NegocioException e) {
            System.out.println(e.getMessage());
        }
        
       
    }
    
}
