package entidades;

import java.util.ArrayList;
import java.util.List;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 *
 * @author t1pas
 */
public class ConversionesDocument {

    public static EntidadMaestro DocumentMaestroAEntidad(Document doc) {
        
        //Colocar la informacion del maestro
        EntidadMaestro maestro = new EntidadMaestro();
            maestro.setId((ObjectId) doc.get("_id"));
            maestro.setIdMaestro(doc.getLong("idMaestro"));
            maestro.setNombre(doc.getString("nombre"));
            maestro.setDescripcion(doc.getString("descripcion"));
            maestro.setFoto(doc.getString("foto"));

           //Colocar la informacion del cubiculo
        if (doc.get("cubiculo", Document.class) != null) {
            Document cubiculoDoc = doc.get("campus", Document.class);
            
            EntidadUbicacion cubiculo = new EntidadUbicacion();
                cubiculo.setDescripcion(cubiculoDoc.getString("descripcion"));
                cubiculo.setIdentificador(cubiculoDoc.getString("identificador"));
                
                //Colocar la informacion del campus
            if (cubiculoDoc.get("campus", Document.class) != null) {
                Document campusDoc = doc.get("campus", Document.class);

                EntidadCampus campus = new EntidadCampus();
                campus.setNombre(campusDoc.getString("nombre"));

                   //Colocar la informacion de la lista de eventos
                if (campusDoc.get("ubicaciones", Document.class)!=null) {
                    Document eventosDoc = doc.get("ubicaciones", Document.class);
                    
                    List<EntidadEvento> eventos=new ArrayList<>();
                    //TODO: CREO QUE ASI NO ES
                }
                cubiculo.setCampus(campus);
            }
            maestro.setCubiculo(cubiculo);
        }

        maestro.setCalendario((List<EntidadEvento>) doc.get("calendario"));

        return null;
         }
    public static EntidadEvento DocumentEventoAEntidad(Document MongoDocument) {

        return null;

    }

    public static EntidadUbicacion DocumentUbicacionAEntidad(Document MongoDocument) {

        return null;

    }

    public static EntidadCampus DocumentCampusAEntidad(Document MongoDocument) {

        return null;

    }
}
