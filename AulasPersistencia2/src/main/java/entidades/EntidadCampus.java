/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author luiis
 */
@Entity
@Table(name="campus")
public class EntidadCampus implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "nombre",nullable = false)
    private String nombre;

    @OneToMany(mappedBy="campus")
    private List<EntidadUbicacion> ubicaciones;

    public EntidadCampus() {
    }

    public EntidadCampus(String nombre, List<EntidadUbicacion> ubicaciones) {
        this.nombre = nombre;
        this.ubicaciones = ubicaciones;
    }

    public EntidadCampus(String nombre) {
        this.nombre = nombre;
    }
    
    public List<EntidadUbicacion> getUbicaciones() {
        return ubicaciones;
    }

    public void setUbicaciones(List<EntidadUbicacion> ubicaciones) {
        this.ubicaciones = ubicaciones;
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadCampus{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append("edificios=").append(edificiosToString());
        sb.append('}');
        return sb.toString();
    }

    private String edificiosToString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Ubicaciones[");
        for(EntidadUbicacion ubi:ubicaciones){
            sb.append("{edificio=").append(ubi.getIdentificador()).append('}');
        }
        return sb.toString();
    }
}
