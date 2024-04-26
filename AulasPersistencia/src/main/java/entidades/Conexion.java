package entidades;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author luiis
 */
public class Conexion implements IConexion{

    String cadenaConexion = "mongodb://127.0.0.1:27017";
    String NombrebaseDatos = "Aulas";

    private static MongoClient cliente;
    private static MongoDatabase baseDatos;

    protected Conexion() {
        //cliente = MongoClients.create(cadenaConexion);

        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(cadenaConexion))
                .codecRegistry(pojoCodecRegistry)
                .build();
        
        cliente = MongoClients.create(settings);
        System.out.println(cliente);

        baseDatos = cliente.getDatabase(NombrebaseDatos);
        System.out.println(baseDatos);

    }
    
    /**
    @Override
    public synchronized MongoCollection<Document> getColeccion(String nombreColeccion) { 
        if (coleccion == null) {
            coleccion = baseDatos.getCollection(nombreColeccion);
        }
        return coleccion;
    }*/

    /**
     *
     */
    @Override
    public void cerrarConexion() {
        if (cliente != null) {
            cliente.close();
        }

    }
    
    /**
     * Obtiene todos los maestros de la coleccion Maestros
     * @return 
     */
    @Override
    public MongoCollection<EntidadMaestro> ConversionDocumentMaestro() {
        return baseDatos.getCollection("Maestros", EntidadMaestro.class);
    }

    /**
     * Obtiene todos los campus de la coleccion Campus
     * @return 
     */
    @Override
    public MongoCollection<EntidadCampus> ConversionDocumentCampus() {
        return baseDatos.getCollection("Campus", EntidadCampus.class);
    }

}
