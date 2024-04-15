/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
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
@Table(name = "ubicaciones")
public class EntidadUbicacion implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "descripcion",nullable=false)
    private String descripcion;
    
    @Column(name= "identificador", nullable=false, unique = true)
    private String identificador;
    
    @ManyToOne
    @JoinColumn(name="id_campus",nullable=false)
    private EntidadCampus campus;

    @OneToMany(mappedBy="ubicacion")
    private List<EntidadEvento> eventos;
    
    public EntidadUbicacion() {
        this.eventos=new ArrayList<>();
    }

    public EntidadUbicacion(String identificador) {
        this.identificador = identificador;
        this.eventos=new ArrayList<>();
    }

    public EntidadUbicacion(String identificador, EntidadCampus campus, String descripcion) {
        this.identificador = identificador;
        this.campus = campus;
        this.descripcion=descripcion;
        this.eventos=new ArrayList<>();
    }

    public List<EntidadEvento> getEventos() {
        return eventos;
    }

    public void setEventos(List<EntidadEvento> eventos) {
        this.eventos = eventos;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public EntidadCampus getCampus() {
        return campus;
    }

    public void setCampus(EntidadCampus campus) {
        this.campus = campus;
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
        sb.append("EntidadUbicacion{");
        sb.append("id=").append(id);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", campus=").append(campus.getNombre());
        if(!eventos.isEmpty())
            sb.append(eventosToString());
        sb.append('}');
        return sb.toString();
    }

    public String eventosToString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Eventos[");
        for(EntidadEvento e:eventos){
            sb.append("{nombre=").append(e.getNombre());
            sb.append(", tipo=").append(e.getTipo().toString());
            if(e.getTipo().equals(EntidadTipoEventoEnum.UNICO_UN_DIA)){
                sb.append(",dia=").append(e.getFechaInicio());
            }else{
                sb.append(", dias semana=").append(e.getDiasSemana());
            }
            sb.append(", hora inicio=").append(e.getHoraInicio());
            sb.append(", duracion en hrs=").append(e.getHorasDuracionEvento()).append('}');
        }
        sb.append(']');
        return sb.toString();
    }
}
