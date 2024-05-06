/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

/**
 *
 * @author pauli
 */
public class EntidadUsuario implements Serializable{
    @BsonId
    private ObjectId id;
    private String usuario;
    private String contraseña;
    private boolean administrador;

    
    
    public EntidadUsuario(){
        
    }
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
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
    /**
     * Obtienes el valor de ObjectId como string
     *
     * @return
     */
    public String getIdConversion() {
        // Obtener el valor hexadecimal del ObjectId
        if (this.id == null) {
            return null;
        }
        
        return this.id.toString();
    }

    /**
     * Recibe un String que convierta a ObjectId para colocarselo como atributo
     * @param id 
     */
    public void setIdConversion(String id) {
       if (id != null) {
            this.id = new ObjectId(id);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadUsuario{");
        sb.append("id=").append(id);
        sb.append(", usuario=").append(usuario);
        sb.append(", contraseña=").append(contraseña);
        sb.append(", administrador=").append(administrador);
        sb.append('}');
        return sb.toString();
    }
    
}
