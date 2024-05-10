
package entidades;

import com.mongodb.client.MongoCollection;

/**
 *
 * @author t1pas
 */
public interface IConexion {
    
    //public MongoCollection<Document> getColeccion(String nombreColeccion);
    //public void cerrarConexion();

    public MongoCollection<EntidadMaestro> ConversionDocumentMaestro();

    public MongoCollection<EntidadCampus> ConversionDocumentCampus();
    public MongoCollection<EntidadUsuario> ConversionDocumentUsuario();
    public MongoCollection<EntidadEvento> ConversionDocumentEvento();

}
