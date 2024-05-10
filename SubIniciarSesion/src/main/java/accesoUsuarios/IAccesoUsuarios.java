/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package accesoUsuarios;

import DTOS.usuarios.UsuarioDTO;
import excepciones.NegocioException;

/**
 *
 * @author pauli
 */
public interface IAccesoUsuarios {
    public UsuarioDTO iniciarSesion(String usuario,String contraseña)throws NegocioException;
}
