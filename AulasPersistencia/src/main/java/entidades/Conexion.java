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

    private final MongoClient cliente;
    private final MongoDatabase baseDatos;

    protected Conexion() {
        CodecRegistry pojoCodecRegistry = fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(cadenaConexion))
                .codecRegistry(pojoCodecRegistry)
                .build();
        
        cliente = MongoClients.create(settings);

        baseDatos = cliente.getDatabase(NombrebaseDatos);

    }

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
    @Override
    public MongoCollection<EntidadUsuario> ConversionDocumentUsuario() {
        return baseDatos.getCollection("Usuarios", EntidadUsuario.class);
    }
    

}
