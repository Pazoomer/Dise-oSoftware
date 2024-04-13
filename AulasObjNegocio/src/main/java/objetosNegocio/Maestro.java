/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DTOS.maestro.MaestroEditableDTO;
import entidades.EntidadMaestro;

/**
 *
 * @author luiis
 */
public class Maestro {
    private Long id;
    private String nombre;
    private String cubiculo;
    private String descripcion;
    private Calendario calendario;
    private Conversiones convertidor;
    private EntidadMaestro maestroPersistencia;

    public Maestro() {
    }

    public Maestro(Long id, String nombre, String cubiculo, String descripcion, Calendario calendario) {
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

    public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro){
        EntidadMaestro maestroEditado= convertidor.toMaestroBO(maestro);
        return convertidor.toMaestroDTO(maestroPersistencia.editarMaestro(maestroEditado));
    }
    
    public MaestroEditableDTO obtenerMaestro(MaestroEditableDTO maestro){
        EntidadMaestro maestroBuscado=convertidor.toMaestroBO(maestro);
        return convertidor.toMaestroDTO(maestroPersistencia.obtenerMaestro(maestroBuscado));
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCubiculo() {
        return cubiculo;
    }

    public void setCubiculo(String cubiculo) {
        this.cubiculo = cubiculo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Calendario getCalendario() {
        return calendario;
    }

    public void setCalendario(Calendario calendario) {
        this.calendario = calendario;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maestro{");
        sb.append("id=").append(id);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo=").append(cubiculo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", calendario=").append(calendario);
        sb.append('}');
        return sb.toString();
    }
    
}
