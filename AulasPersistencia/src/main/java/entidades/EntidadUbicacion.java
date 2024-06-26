package entidades;

import java.beans.Transient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class EntidadUbicacion implements Serializable {

    @BsonId
    private ObjectId id;

    private String descripcion;
    
    private String identificador;
    
    //@BsonProperty("idCampus")
    private ObjectId idCampus;

    private List<EntidadEvento> eventos;
    
    private Double posicionX;
            
    private Double posicionY;       
    
    public EntidadUbicacion() {
        this.eventos=new ArrayList<>();
    }

    public EntidadUbicacion(ObjectId id) {
        this.id = id;
    }

    public EntidadUbicacion(String identificador) {
        this.identificador = identificador;
        this.eventos=new ArrayList<>();
    }

    public EntidadUbicacion(String identificador, ObjectId idCampus, String descripcion) {
        this.identificador = identificador;
        this.idCampus = idCampus;
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

    public ObjectId getCampus() {
        return idCampus;
    }

    public void setCampus(ObjectId idCampus) {
        this.idCampus = idCampus;
    }
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public Double getPosicionX() {
        return posicionX;
    }

    public void setPosicionX(Double posicionX) {
        this.posicionX = posicionX;
    }

    public Double getPosicionY() {
        return posicionY;
    }

    public void setPosicionY(Double posicionY) {
        this.posicionY = posicionY;
    }
    
    /**
     * Obtienes el valor de ObjectId como string
     *
     * @return
     */
    @Transient
    public String ggetCampusConversion() {
        // Obtener el valor hexadecimal del ObjectId
        if (this.idCampus == null) {
            return null;
        }
        
        return this.idCampus.toString();
    }

    /**
     * Recibe un String que convierta a ObjectId para colocarselo como atributo
     *
     * @param idCampus
     */
    @Transient
    public void ssetCampusConversion(String idCampus) {
        if (idCampus != null) {
            this.idCampus = new ObjectId(idCampus);
        }

    }
    
    /**
     * Obtienes el valor de ObjectId como string
     *
     * @return
     */
    @Transient
    public String ggetIdConversion() {
        // Obtener el valor hexadecimal del ObjectId
        if (this.id == null) {
            return null;
        }
        
        return this.id.toString();
    }

    /**
     * Recibe un String que convierta a ObjectId para colocarselo como atributo
     *
     * @param id
     */
    @Transient
    public void ssetIdConversion(String id) {
        if (id != null) {
            this.id = new ObjectId(id);
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("EntidadUbicacion{");
        sb.append("id=").append(id);
        sb.append(", descripcion=").append(descripcion);
        sb.append(", identificador=").append(identificador);
        sb.append(", campus=").append(idCampus);
        sb.append(", eventos=").append(eventos);
        sb.append(", posicionX=").append(posicionX);
        sb.append(", posicionY=").append(posicionY);
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

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final EntidadUbicacion other = (EntidadUbicacion) obj;
        return Objects.equals(this.id, other.id);
    }
    
    
}
