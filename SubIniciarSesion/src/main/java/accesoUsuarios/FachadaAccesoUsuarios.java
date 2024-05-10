/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package accesoUsuarios;

import DTOS.usuarios.UsuarioDTO;
import excepciones.NegocioException;

/**
 *
 * @author pauli
 */
public class FachadaAccesoUsuarios implements IAccesoUsuarios{
    private final ControlUsuarios usaurios;
    public FachadaAccesoUsuarios (){
        this.usaurios=new ControlUsuarios();
    }
    @Override
    public UsuarioDTO iniciarSesion(String usuario, String contraseña) throws NegocioException {
        return  usaurios.iniciarSesion(usuario, contraseña);
    }
    
    
}
