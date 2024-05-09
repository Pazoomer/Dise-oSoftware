/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.UpdateResult;
import excepcioness.PersistenciaExceptionn;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.conversions.Bson;

/**
 *
 * @author pauli
 */
public class CrudUsuario {
    
    private static MongoCollection<EntidadUsuario> coleccion;
    private final static Logger LOG = Logger.getLogger(CrudUsuario.class.getName());
    IConexion conexion;

    public CrudUsuario() {
        conexion=new Conexion();
        coleccion = conexion.ConversionDocumentUsuario();
    }

    public EntidadUsuario agregarUsuario(EntidadUsuario usuario) throws PersistenciaExceptionn {
        try {
            coleccion.insertOne(usuario);
            return usuario;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al agregar usuario.");
        }
    }
    
    public EntidadUsuario editarUsuario(EntidadUsuario usuario) throws PersistenciaExceptionn {
        System.out.println(usuario);
    try {
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

            return usuarioEncontrado;
        } catch (Exception e) {
            LOG.log(Level.SEVERE, e.getMessage(), e);
            throw new PersistenciaExceptionn("Hubo un error al obtener usuario.");
        }
    }
    public EntidadUsuario iniciarSesion(String idUsuario, String contraseña) throws PersistenciaExceptionn {
    try {
        // Buscar el usuario por nombre de usuario y contraseña
        EntidadUsuario usuarioEncontrado = coleccion.find(and(eq("idUsuario", idUsuario), eq("contraseña", contraseña))).first();
        
        // Verificar si el usuario existe
        if (usuarioEncontrado != null) {
            return usuarioEncontrado; // Si existe, devuelve el usuario encontrado
        } else {
            // Si el usuario no existe, lanzar una excepción
            return null;
        }
    } catch (Exception e) {
        LOG.log(Level.SEVERE, e.getMessage(), e);
        throw new PersistenciaExceptionn("Hubo un error al iniciar sesión.");
    }
}


    
    public boolean cerrarConexion(){
        conexion.cerrarConexion();
        return true;
    }
}

    
    

