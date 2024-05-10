/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DTOS.usuarios.UsuarioDTO;
import entidades.CrudUsuario;
import entidades.EntidadUsuario;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;

/**
 *
 * @author pauli
 */
public class Usuario {
    private final Conversiones convertidor;
    private final CrudUsuario crudUsuario;
    public Usuario(){
         this.convertidor=new Conversiones();
        this.crudUsuario=new CrudUsuario();
    }
    public UsuarioDTO iniciarSesion(String usuario, String contraseña)throws NegocioException{
         try{
             EntidadUsuario usuarioEncontrado= crudUsuario.iniciarSesion(usuario,contraseña);
            if (usuarioEncontrado != null) {       
                return convertidor.toUsuarioDTO(usuarioEncontrado);
            } else {
                return null;
            }
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
}
