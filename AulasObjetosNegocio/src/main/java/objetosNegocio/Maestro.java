/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
public class Maestro {
    private Long id;
    private String nombre;
    private String cubiculo;
    private String descripcion;
    private List<Evento> calendario; 

    public Maestro() {
        this.calendario=new ArrayList<>();
    }

    public Maestro(Long id, String nombre, String cubiculo, String descripcion, List<Evento> calendario) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.calendario = calendario;
    }

    public Maestro(Long id, String nombre, String cubiculo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
    }

    public Long getId(Long id) {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre(String nombre) {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCubiculo(String cubiculo) {
        return cubiculo;
    }

    public void setCubiculo(String cubiculo) {
        this.cubiculo = cubiculo;
    }

    public String getDescripcion(String descripcion) {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Evento> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<Evento> calendario) {
        this.calendario = calendario;
    }

    public void agregarEventoCalendario(Evento evento){
        this.calendario.add(evento);
    }
  
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maestro{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo=").append(cubiculo);
        sb.append(", descripcion=").append(descripcion);
        sb.append('}');
        return sb.toString();
    }
    
}
