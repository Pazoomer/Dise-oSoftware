/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author luiis
 */
@Entity
@Table (name = "maestros")
public class EntidadMaestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_maestro_bd")
    private Long idBd;

    @Column(name = "id_maestro", nullable = false)
    private Long idMaestro; //Representa el identificador fuera de la base de datos

    @Column(name = "nombre", nullable = true, length = 150)
    private String nombre;
    
    @ManyToOne
    @JoinColumn(name = "edificio_cubiculo")
    private EntidadUbicacion cubiculo;

    @Column(name = "descripcion", nullable = true, length = 200)
    private String descripcion;

    @Column(name = "foto", nullable = true, length = 300)
    private String foto;

    @OneToMany(mappedBy = "maestro", cascade = CascadeType.ALL)
    private List<EntidadEvento> calendario;

    public EntidadMaestro() {
        this.calendario=new ArrayList<>();
    }

    public EntidadMaestro(Long idMaestro, String nombre, EntidadUbicacion cubiculo, String descripcion, String foto, List<EntidadEvento> calendario) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = calendario;
    }

    public EntidadMaestro(Long idMaestro, String nombre, EntidadUbicacion cubiculo, String descripcion, String foto) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario=new ArrayList<>();
    }

    public Long getIdBd() {
        return idBd;
    }

    public void setIdBd(Long idBd) {
        this.idBd = idBd;
    }

    public Long getIdMaestro() {
        return idMaestro;
    }

    public void setIdMaestro(Long idMaestro) {
        this.idMaestro = idMaestro;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public EntidadUbicacion getCubiculo() {
        return cubiculo;
    }

    public void setCubiculo(EntidadUbicacion cubiculo) {
        this.cubiculo = cubiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public List<EntidadEvento> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<EntidadEvento> calendario) {
        this.calendario = calendario;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadMaestro{");
        sb.append("idBd=").append(idBd);
        sb.append(", idMaestro=").append(idMaestro);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo[");
        sb.append("edificio=").append(cubiculo.getIdentificador());
        sb.append(", campus=").append(cubiculo.getCampus().getNombre()).append(']');
        sb.append(", descripcion=").append(descripcion);
        sb.append(", foto=").append(foto);
        if(!calendario.isEmpty())
            sb.append(", calendario=").append(calendarioToString());
        sb.append('}');
        return sb.toString();
    }
  
    public String calendarioToString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Eventos[");
        for(EntidadEvento ev:calendario){
            sb.append("{nombre=").append(ev.getNombre());
            sb.append("ubicacion=").append(ev.getUbicacion());
            sb.append("fecha inicio=").append(ev.getFechaInicio());
            sb.append("hora inicio=").append(ev.getHoraInicio()).append("}");
        }
        sb.append("]");
        return sb.toString();
    }
}
