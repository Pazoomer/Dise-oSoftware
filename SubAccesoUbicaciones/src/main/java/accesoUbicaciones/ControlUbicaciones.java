
package accesoUbicaciones;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import excepciones.NegocioException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import objetosNegocio.Ubicacion;

/**
 *
 * @author t1pas
 */
public class ControlUbicaciones {
    
    private Ubicacion ubicacionBO;
    
    public ControlUbicaciones() {
        this.ubicacionBO=new Ubicacion();
    }

    protected List<UbicacionDTO> recuperarEdificios()throws NegocioException{
        try{
            return ubicacionBO.obtenerUbicaciones();
        }catch(NegocioException e){
            throw e;
        }
    }
    
    protected UbicacionDTO recuperarEdificio(UbicacionDTO ubicacion)throws NegocioException {
        try{
            return ubicacionBO.obtenerUbicacion(ubicacion);
        }catch(NegocioException e){
            throw e;
        }
        
        //IAccesoGoogleMaps dao = new FachadaAccesoGoogleMaps(conexion);

//        try {
//            return this.accesoEdificiosGoogleMaps();
//        } catch (NegocioException ex) {
//            Logger.getLogger(ControlUbicaciones.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
    }

    protected List<UbicacionDTO> recuperarEdificiosPorCampus(CampusConsultableDTO campus)throws NegocioException {
        try{
            return ubicacionBO.obtenerUbicacionesPorCampus(campus);
        }catch(NegocioException e){
            throw e;
        }
        
//IAccesoGoogleMaps dao=new FachadaAccesoGoogleMaps(conexion);
//        
//        try {
//            return this.accesoEdificiosPorCampusGoogleMaps(campus);
//        } catch (NegocioException ex) {
//            Logger.getLogger(ControlUbicaciones.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }
    
//    private List<String> accesoEdificiosGoogleMaps() throws NegocioException {
//        EntityManager entityManager = conexion.crearConexion();
//        List<String> nombresCampus;
//
//        // Consulta SQL nativa para obtener solo los nombres de todos los campus
//        String sql = "SELECT nombre FROM Campus";
//
//        // Crear una consulta nativa
//        Query query = entityManager.createNativeQuery(sql);
//
//        // Ejecutar la consulta y obtener los nombres de los campus
//        nombresCampus = query.getResultList();
//        
//        entityManager.close();
//
//        return nombresCampus;
//    }
//
//    private List<String> accesoEdificiosPorCampusGoogleMaps(String campus) throws NegocioException {
//
//        EntityManager entityManager = conexion.crearConexion();
//        List<String> nombresEdificios;
//
//        try {
//
//            if (campus.equalsIgnoreCase("Obregon Nainari")) {
//                String sql1 = "SELECT nombre FROM ubicacion WHERE campus_id = 1";
//
//                Query query1 = entityManager.createNativeQuery(sql1);
//
//                // Ejecutar la consulta
//                nombresEdificios = query1.getResultList();
//            } else {
//                String sql1 = "SELECT nombre FROM ubicacion WHERE campus_id = 2";
//
//                Query query1 = entityManager.createNativeQuery(sql1);
//
//                // Ejecutar la consulta
//                nombresEdificios = query1.getResultList();
//            }
//
//
//        } finally {
//            // Siempre cierra el EntityManager
//            entityManager.close();
//        }
//
//        return nombresEdificios;
//
//    }
}
