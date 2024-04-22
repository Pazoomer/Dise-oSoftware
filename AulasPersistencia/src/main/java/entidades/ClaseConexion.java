package entidades;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

/**
 *
 * @author luiis
 */
public class ClaseConexion {

    String cadenaConexion = "mongodb://127.0.0.1:27017";
    String NombrebaseDatos = "Aulas";

    private static MongoClient cliente;
    private static MongoDatabase baseDatos;
    private static MongoCollection<Document> coleccion;

    protected ClaseConexion() {
        cliente = MongoClients.create(cadenaConexion);
        baseDatos = cliente.getDatabase(NombrebaseDatos);
    }

    public static synchronized MongoCollection<Document> getColeccion(String nombreColeccion) {
        if (coleccion == null) {
            coleccion = baseDatos.getCollection(nombreColeccion);
        }
        return coleccion;
    }

    public static void cerrarConexion() {
        cliente.close();
    }  
    
}
