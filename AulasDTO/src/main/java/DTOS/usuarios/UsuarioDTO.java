/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTOS.usuarios;

/**
 *
 * @author pauli
 */
public class UsuarioDTO {
    private String idUsuario;
    private String contraseña;
    private boolean administrador;
    public UsuarioDTO (){
        
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String usuario) {
        this.idUsuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }
    
}
