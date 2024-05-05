package entidades;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

/**
 *
 * @author luiis
 */
public class Conexion implements IConexion{

    private static MongoClient mongoClient = null;
    private static final String URI = "mongodb://localhost:27017";
    private static final String DB_NAME = "Aulas";

//    private static final String cadenaConexion = "mongodb://localhost:27017";
//    private static final String NombrebaseDatos = "Aulas";
//
//    private static MongoClient cliente=null;

    private Conexion() {
    }

    public static MongoDatabase getDatabasee(){
        if (mongoClient == null) {
            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));

            MongoClientSettings clientSettings = MongoClientSettings.builder()
                    .applyConnectionString(new com.mongodb.ConnectionString(URI))
                    .codecRegistry(pojoCodecRegistry).build();

            mongoClient = MongoClients.create(clientSettings);
            return mongoClient.getDatabase(DB_NAME).withCodecRegistry(pojoCodecRegistry);
        }
        return mongoClient.getDatabase(DB_NAME);
//        if (cliente == null) {
//            CodecRegistry pojoCodecRegistry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(),
//                    CodecRegistries.fromProviders(PojoCodecProvider.builder().automatic(true).build()));
//
//            MongoClientSettings settings = MongoClientSettings.builder()
//                    .applyConnectionString(new com.mongodb.ConnectionString(cadenaConexion))
//                    .codecRegistry(pojoCodecRegistry).build();
//            
//            cliente = MongoClients.create(settings);
//            return cliente.getDatabase(NombrebaseDatos).withCodecRegistry(pojoCodecRegistry);
//        }
//        return cliente.getDatabase(NombrebaseDatos);
    }
    /**
     *
     */
    //@Override
    public static void cerrarConexion() {
        if (mongoClient != null) {
            mongoClient.close();
        }

    }

    /**
     * Obtiene todos los maestros de la coleccion Maestros
     * @return 
     */
    @Override
    public MongoCollection<EntidadMaestro> ConversionDocumentMaestro() {
        return getDatabasee().getCollection("Maestros", EntidadMaestro.class);
    }

    /**
     * Obtiene todos los campus de la coleccion Campus
     * @return 
     */
    @Override
    public MongoCollection<EntidadCampus> ConversionDocumentCampus() {
        return getDatabasee().getCollection("Campus", EntidadCampus.class);
    }

    @Override
    public MongoCollection<EntidadEvento> ConversionDocumentEvento() {
        return getDatabasee().getCollection("Eventos", EntidadEvento.class);
    }

}
