/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package prueba;

import DTOS.usuarios.UsuarioDTO;
import accesoUsuarios.FachadaAccesoUsuarios;
import accesoUsuarios.IAccesoUsuarios;
import excepciones.NegocioException;

/**
 *
 * @author pauli
 */
public class pruebaMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        IAccesoUsuarios user=new FachadaAccesoUsuarios();
         try{
             UsuarioDTO usuario=user.iniciarSesion("test1", "pass123");
             System.out.println(usuario);
         }catch(NegocioException e){
            System.out.println(e.getMessage());
         }
    }
    
}
