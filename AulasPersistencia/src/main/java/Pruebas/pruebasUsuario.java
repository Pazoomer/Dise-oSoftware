/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Pruebas;

import Insercion.Insercion;
import entidades.CrudUsuario;
import entidades.EntidadUsuario;
import excepcioness.PersistenciaExceptionn;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pauli
 */
public class pruebasUsuario {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         try {
        CrudUsuario crud=new CrudUsuario();
        
        EntidadUsuario usuario=new EntidadUsuario();
        
        usuario.setUsuario("test1");
        usuario.setContraseña("pass123");
        
        EntidadUsuario usuarioEncontrado= crud.iniciarSesion("test1","pass123");
        if (usuarioEncontrado != null) {
            if(usuarioEncontrado.isAdministrador()){
                //adm
            }else{
                //maestro
            }
            System.out.println(usuarioEncontrado); // Si existe, devuelve el usuario encontrado
        } else {
            // usuario o contraseña ioncorrecto
            System.out.println(usuarioEncontrado);
        }
         } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Insercion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
