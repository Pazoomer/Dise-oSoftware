
package objetosNegocio;

import DTOS.maestro.MaestroEditableDTO;
import entidades.CrudMaestro;
import entidades.EntidadMaestro;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
public class Maestro {
    private Long id;
    private String nombre;
    private Ubicacion cubiculo;
    private String descripcion;
    private Calendario calendario;
    private List<Evento> calendarioEv;
    private Conversiones convertidor;
    private CrudMaestro crudMaestro;

    public Maestro() {
        this.convertidor=new Conversiones();
        this.crudMaestro=new CrudMaestro();
        this.calendarioEv=new ArrayList<>();
    }

    public Maestro(Long id, String nombre, Ubicacion cubiculo, String descripcion, List<Evento> calendarioEv) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.calendarioEv = calendarioEv;
        this.convertidor=new Conversiones();
    }

    public Maestro(Long id, String nombre, Ubicacion cubiculo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.convertidor=new Conversiones();
        this.calendarioEv=new ArrayList<>();
    }

    public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro)throws NegocioException{
        EntidadMaestro maestroEditado= convertidor.toMaestroBO(maestro);
        try{
            return convertidor.toMaestroDTO(crudMaestro.editarMaestro(maestroEditado));
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public MaestroEditableDTO obtenerMaestro(MaestroEditableDTO maestro)throws NegocioException{
        EntidadMaestro maestroBuscado=convertidor.toMaestroBO(maestro);
        try{
            return convertidor.toMaestroDTO(crudMaestro.obtenerMaestro(maestroBuscado));
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
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

    public Ubicacion getCubiculo() {
        return cubiculo;
    }

    public void setCubiculo(Ubicacion cubiculo) {
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
        sb.append(", cubiculo[").append(cubiculo.toStringReducido()).append(']');
        sb.append(", descripcion=").append(descripcion);
        sb.append(", calendario=").append(calendarioToString());
        sb.append('}');
        return sb.toString();
    }
    
    public String calendarioToString(){
        StringBuilder sb=new StringBuilder();
        sb.append("Eventos[");
        for(Evento ev:calendarioEv){
            sb.append('{').append(ev.toStringReducido()).append('}');
        }
        sb.append("]");
        return sb.toString();
    }
    
}
