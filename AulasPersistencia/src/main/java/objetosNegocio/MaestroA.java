
package objetosNegocio;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
public class MaestroA {
    private Long id;
    private String nombre;
    private String cubiculo;
    private String descripcion;
    private List<EventoA> calendario; 

    public MaestroA() {
        this.calendario=new ArrayList<>();
    }

    public MaestroA(Long id, String nombre, String cubiculo, String descripcion, List<EventoA> calendario) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.calendario = calendario;
    }

    public MaestroA(Long id, String nombre, String cubiculo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
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

    public List<EventoA> getCalendario() {
        return calendario;
    }

    public void setCalendario(List<EventoA> calendario) {
        this.calendario = calendario;
    }

    public void agregarEventoCalendario(EventoA evento){
        this.calendario.add(evento);
    }
  
    public MaestroA obtenerMaestro(MaestroA maestro){
        return maestro;
    }
    
    public MaestroA editarMaestro(MaestroA maestroEditado){
        return maestroEditado;
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
