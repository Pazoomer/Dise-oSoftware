
package entidades;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import excepcioness.PersistenciaExceptionn;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pauli
 */
public class CrudUsuario {
    
    private static MongoCollection<EntidadUsuario> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudUsuario.class.getName());

    public CrudUsuario() {
        CrudUsuario.coleccion =Conexion.getDatabasee().getCollection("Usuarios", EntidadUsuario.class);
    }
    
    public EntidadUsuario encriptar(EntidadUsuario usuario) throws PersistenciaExceptionn{
        try {
            String sal=Cifrado.generarSal();
            usuario.setSal(sal);
            usuario.setContraseña(Cifrado.encriptarCadena(usuario.getContraseña(), sal));
            return usuario;
        } catch (Exception ex) {
            throw new PersistenciaExceptionn("No se pudo mantener la estabilidad de la base de datos.");
        }
    }
    
    public EntidadUsuario desencriptar(EntidadUsuario usuario) throws PersistenciaExceptionn{
        try {
            usuario.setContraseña(Cifrado.descifrarCadena(usuario.getContraseña(), usuario.getSal()));
            return usuario;
        } catch (Exception ex) {
            throw new PersistenciaExceptionn("No se pudo mantener la estabilidad de la base de datos.");
        }
    }

    public EntidadUsuario agregarUsuario(EntidadUsuario usuario) throws PersistenciaExceptionn {
        try {
            usuario=encriptar(usuario);
            coleccion.insertOne(usuario);
            return usuario;
        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar usuario.");
        }
    }
    
    public EntidadUsuario editarUsuario(EntidadUsuario usuario) throws PersistenciaExceptionn {
        System.out.println(usuario);
    try {
        usuario=encriptar(usuario);
        coleccion.replaceOne(eq("idUsuario", usuario.getId()), usuario);  
        return usuario;
    } catch (Exception e) {
        LOG.log(Level.SEVERE, e.getMessage(), e);
        throw new PersistenciaExceptionn("Hubo un error al actualizar usuario.");
    }
}

    public boolean eliminarUsuario(EntidadUsuario usuarioParametro) throws PersistenciaExceptionn {
        try {
            coleccion.deleteOne(eq("idUsuario", usuarioParametro.getId()));
            return true;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al eliminar usuario.");
        }
    }

    public EntidadUsuario obtenerUsuario(EntidadUsuario usuarioParametro) throws PersistenciaExceptionn {
        try {
            EntidadUsuario usuarioEncontrado = coleccion.find(eq("idUsuario", usuarioParametro.getId())).first();
            usuarioEncontrado=this.desencriptar(usuarioEncontrado);
            return usuarioEncontrado;
        } catch (PersistenciaExceptionn e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener usuario.");
        }
    }

    public EntidadUsuario iniciarSesion(String idUsuario, String contraseña) throws PersistenciaExceptionn {
        //System.out.println("Id: "+idUsuario);
        //System.out.println("Contraseña: "+contraseña);
        try {
            // Busca el usuario por nombre de usuario
            FindIterable<EntidadUsuario> usuariosEncontrados = coleccion.find(eq("idUsuario", idUsuario));

            // Itera sobre los usuarios encontrados
            for (EntidadUsuario usuario : usuariosEncontrados) {
                // Recupera la sal asociada con el usuario
                String salt = usuario.getSal();

               // String contraseñaEncriptada=Cifrado.encriptarCadena(contraseña, salt);
                String contraseñaDesencriptada=Cifrado.descifrarCadena(usuario.getContraseña(), salt);

                
                //System.out.println("Sal: "+salt);
                //System.out.println("Contraseña: "+usuario.getContraseña());
                //System.out.println("Desencriptada: "+contraseñaDesencriptada);
               // System.out.println("Encriptada: "+contraseñaEncriptada);
                
                desencriptar(usuario);
                
                //System.out.println("Sal2: "+usuario.getSal());
                //System.out.println("Contraseña2: "+usuario.getContraseña());
                // Compara la contraseña encriptada con la contraseña encriptada almacenada en la base de datos
                if (usuario.getContraseña().equals(contraseñaDesencriptada)) {
                    return usuario; // Si las contraseñas coinciden, devuelve el usuario encontrado
                }
            }

            // Si el usuario no existe o la contraseña no coincide, devuelve null
            return null;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al iniciar sesión.");
        }
    }

    public boolean cerrarConexion() {
        Conexion.cerrarConexion();
        return true;
    }

}

    
    

