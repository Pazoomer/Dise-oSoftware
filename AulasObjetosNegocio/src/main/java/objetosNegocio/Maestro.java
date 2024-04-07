
package objetosNegocio;

import java.io.Serializable;
import java.util.ArrayList;
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
 * @author t1pas
 */
@Entity
@Table(name = "maestro")
public class Maestro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_maestro_bd")
    private Long idBd;
    
    @Column(name="id_maestro",nullable=false)
    private Long idMaestro; //Representa el identificador fuera de la base de datos
    
    @Column(name="nombre",nullable=true,length=150)
    private String nombre;
    
    @Column(name="cubiculo", nullable = true, length = 50)
    private String cubiculo;

    @Column(name = "descripcion", nullable = true, length = 200)
    private String descripcion;
    
    @Column(name = "foto", nullable = true, length = 300)
    private String foto;
    
    @OneToMany(mappedBy = "Maestro", cascade = CascadeType.ALL)
    private List<Evento> calendario;
    
    public Maestro() {
        this.calendario=new ArrayList<>();
    }

    public Maestro(Long idMaestro, String nombre, String cubiculo, String descripcion, String foto, List<Evento> calendario) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
        this.calendario = calendario;
    }

    public Maestro(Long idMaestro, String nombre, String cubiculo, String descripcion, String foto) {
        this.idMaestro = idMaestro;
        this.nombre = nombre;
        this.cubiculo = cubiculo;
        this.descripcion = descripcion;
        this.foto = foto;
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

    public List<Evento> getCalendario() {
        return calendario;
    }

    public Maestro obtenerMaestro(Maestro maestro){
        return maestro;
    }
    
    public Maestro editarMaestro(Maestro maestroEditado){
        return maestroEditado;
    }
    
    public void setCalendario(List<Evento> calendario) {
        this.calendario = calendario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Maestro{");
        sb.append("idBd=").append(idBd);
        sb.append(", idMaestro=").append(idMaestro);
        sb.append(", nombre=").append(nombre);
        sb.append(", cubiculo=").append(cubiculo);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", foto=").append(foto);
        sb.append(", calendario=").append(calendario);
        sb.append('}');
        return sb.toString();
    }
    
    

    
    
  
    
}
