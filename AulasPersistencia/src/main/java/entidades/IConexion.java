
package entidades;

import com.mongodb.client.MongoCollection;
import org.bson.Document;

/**
 *
 * @author t1pas
 */
public interface IConexion {
    
    public MongoCollection<Document> getColeccion(String nombreColeccion);

    public void cerrarConexion();
}
