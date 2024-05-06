/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoUsuarios;

import DTOS.usuarios.UsuarioDTO;
import excepciones.NegocioException;
import objetosNegocio.Usuario;

/**
 *
 * @author pauli
 */
public class ControlUsuarios {
     private final Usuario usuarioBO;
     public ControlUsuarios(){
         this.usuarioBO=new Usuario();
     }
     protected UsuarioDTO iniciarSesion(String usuario,String contraseña)throws NegocioException{
        try{
              return usuarioBO.iniciarSesion(usuario, contraseña);
        }catch(NegocioException e){
            throw e;
        }
          
     }
}
