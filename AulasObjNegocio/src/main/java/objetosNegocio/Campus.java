package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import entidades.CrudCampus;
import entidades.EntidadCampus;
import entidades.EntidadUbicacion;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author t1pas
 */
public class Campus {
    private final CrudCampus crudCampus;
    private final Conversiones conversiones;
    
    public Campus() {
        this.crudCampus=new CrudCampus();
        this.conversiones=new Conversiones();
    }

    public CampusConsultableDTO obtenerCampus(CampusConsultableDTO campusParametro) throws NegocioException {

        try {
            EntidadCampus entidadCampus=crudCampus.obtenerCampus(conversiones.toCampusBO(campusParametro));
            if (entidadCampus!=null) {
                 return conversiones.toCampusDTO(entidadCampus);
            }
            throw new PersistenciaExceptionn("No se encontro el campus");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public CampusConsultableDTO agregarCampus(CampusConsultableDTO campusParametro) throws NegocioException {

        try {
            EntidadCampus entidadCampus=crudCampus.agregarCampus(conversiones.toCampusBO(campusParametro));
            if (entidadCampus!=null) {
                 return conversiones.toCampusDTO(entidadCampus);
            }
            throw new PersistenciaExceptionn("Hubo un error al agregar el campus");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Boolean eliminarCampus(CampusConsultableDTO campusParametro) throws NegocioException {

        try {
            Boolean eliminado=crudCampus.eliminarCampus(conversiones.toCampusBO(campusParametro));
            if (eliminado==true) {
                 return eliminado;
            }
            throw new PersistenciaExceptionn("Hubo un error al eliminar el campus");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public CampusConsultableDTO editarCampus(CampusConsultableDTO campusParametro) throws NegocioException {

        try {
            EntidadCampus entidadCampus=crudCampus.editarCampus(conversiones.toCampusBO(campusParametro));
            if (entidadCampus!=null) {
                 return conversiones.toCampusDTO(entidadCampus);
            }
            throw new PersistenciaExceptionn("Hubo un error al editar el campus");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public List<CampusConsultableDTO> obtenerTodosLosCampus() throws NegocioException {

        try {
            List<CampusConsultableDTO> campus = new ArrayList<>();
            List<EntidadCampus> entidadesCampus = crudCampus.obtenerTodosLosCampus();
            for (EntidadCampus entidadCampus : entidadesCampus) {
                campus.add(conversiones.toCampusDTO(entidadCampus));
            }
            return campus;
        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public List<UbicacionDTO> obtenerUbicacionesPorCampus(CampusConsultableDTO campus)throws NegocioException{
        List<UbicacionDTO> ubicacionesDTO=new ArrayList<>();

        EntidadCampus campusBO=conversiones.toCampusBO(campus);

        try{
            List<EntidadUbicacion> ubicacionesAux=crudCampus.obtenerUbicacionesPorCampus(campusBO);
            
            if(ubicacionesAux!=null && !ubicacionesAux.isEmpty()){
                for(EntidadUbicacion u:ubicacionesAux){
                    ubicacionesDTO.add(conversiones.toUbicacionDTO(u));
                }
                return ubicacionesDTO;
            }
            return null;
        } catch (PersistenciaExceptionn e) {
            throw new NegocioException(e.getMessage());
        }
    }

    public UbicacionDTO obtenerUbicacion(UbicacionDTO ubicacion) throws NegocioException {
        try {
            EntidadUbicacion entidadUbicacion=crudCampus.obtenerUbi(conversiones.toUbicacionBO(ubicacion));
            if (entidadUbicacion!=null) {
                return conversiones.toUbicacionDTO(entidadUbicacion);
            }
            return null;
        } catch (PersistenciaExceptionn ex) {
            throw new NegocioException(ex.getMessage());
            //Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        //return null;
    }
    
    public UbicacionDTO agregarUbicacion(UbicacionDTO ubicacionParametro) throws NegocioException {

        try {
            EntidadUbicacion entidadUbicacion=crudCampus.agregarUbicacion(conversiones.toUbicacionBO(ubicacionParametro));
            if (entidadUbicacion!=null) {
                 return conversiones.toUbicacionDTO(entidadUbicacion);
            }
            throw new PersistenciaExceptionn("Hubo un error al agregar la ubicacion");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Boolean eliminarUbicacion(UbicacionDTO ubicacionParametro) throws NegocioException {

        try {

            EntidadUbicacion toUbicacionBO = conversiones.toUbicacionBO(ubicacionParametro);

            Boolean eliminado=crudCampus.eliminarUbicacion(toUbicacionBO);
           //Boolean eliminado=crudCampus.eliminarUbicacion(conversiones.toUbicacionBO(ubicacionParametro));
            if (eliminado==true) {
                 return eliminado;
            }
            throw new PersistenciaExceptionn("Hubo un error al eliminar la ubicacion");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public UbicacionDTO editarUbicacion(UbicacionDTO ubicacionParametro) throws NegocioException {

        try {
            EntidadUbicacion entidadUbicacion=crudCampus.editarUbicacion(conversiones.toUbicacionBO(ubicacionParametro));
            if (entidadUbicacion!=null) {
                 return conversiones.toUbicacionDTO(entidadUbicacion);
            }
            throw new PersistenciaExceptionn("Hubo un error al editar la ubicacion");

        } catch (PersistenciaExceptionn ex) {
            Logger.getLogger(Campus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public boolean agregarEventoAUbicacion(UbicacionDTO ubicacion, EventoConsultableDTO evento)throws NegocioException{
        try{
            return crudCampus.agregarEventoAUbicacion(
                    conversiones.toUbicacionBO(ubicacion),
                    conversiones.toEventoBO(evento));
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
}
