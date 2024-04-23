package entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

public class EntidadUbicacion implements Serializable {

    @BsonId
    private ObjectId id;

    private String descripcion;
    
    private String identificador;
    
    private EntidadCampus campus;

    private List<EntidadEvento> eventos;
    
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
    
    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }
    
    
    /**
     * Obtienes el valor de ObjectId como long
     * @return 
     */
    public Long getIdLong(){
        // Obtener el valor hexadecimal del ObjectId
        String valorHex = this.id.toHexString();
        
        // Convertir el valor hexadecimal a un número
        return Long.valueOf(valorHex);
    }

    /**
     * Recibe un long que convierta a ObjectId para colocarselo como atributo
     * @param id 
     */
    public void setIdLong(Long id) {

        // Convertir el valor long a su representación hexadecimal
        String hexValue = Long.toHexString(id);

        // Rellenar con ceros a la izquierda para asegurar que tenga 24 dígitos
        while (hexValue.length() < 24) {
            hexValue = "0" + hexValue;
        }

        this.id = new ObjectId(hexValue);
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
