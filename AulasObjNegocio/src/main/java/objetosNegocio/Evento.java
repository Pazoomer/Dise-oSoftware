
package objetosNegocio;

import DTOS.evento.EventoConsultableDTO;
import entidades.CrudCampus;
import entidades.CrudEvento;
import entidades.EntidadEvento;
import entidades.EntidadUbicacion;
import excepciones.NegocioException;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
public class Evento {
    private final CrudEvento crudEvento;
    private final Conversiones conversiones;
    
    public Evento() {
        this.crudEvento=new CrudEvento();
        this.conversiones=new Conversiones();
    }
    
    public EventoConsultableDTO agregarEvento(EventoConsultableDTO evento)throws NegocioException{
        CrudCampus crudC=new CrudCampus();
        try{
            EntidadEvento e = conversiones.toEventoBO(evento);
            e = crudEvento.agregarEvento(e);
            if (e != null) return conversiones.toEventoDTO(e);
            return null;
//            if(evento.getUbicacion()!=null && evento.getUbicacion().getIdentificador()!=null){
//                EntidadUbicacion ubicacion = crudC.obtenerUbi(new EntidadUbicacion(evento.getUbicacion().getIdentificador()));
//                if (ubicacion != null) {
//                    System.out.println(ubicacion);
//                    EntidadEvento e = conversiones.toEventoBO(evento);
//                    e.setUbicacion(ubicacion);
//                    e = crudEvento.agregarEvento(e);
//                    if (e != null) {
//                        if(!crudC.agregarEventoAUbicacion(ubicacion, e)){
//                            boolean b=eliminarEvento(evento);
//                        }
//                        return conversiones.toEventoDTO(e);
//                    }
//                    return null;
//                }throw new NegocioException("La ubicacion ingresada no existe");
//            }throw new NegocioException("Debe ingresar una ubicacion para el evento");
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public EventoConsultableDTO editarEvento(EventoConsultableDTO evento,List<String> camposModificados)throws NegocioException{
        try{
            EntidadEvento e=crudEvento.editarEvento(conversiones.toEventoBO(evento),camposModificados);
            if(e!=null)return conversiones.toEventoDTO(e);
            else return null;
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public boolean eliminarEvento(EventoConsultableDTO evento)throws NegocioException{
        try{
            return crudEvento.eliminarEvento(conversiones.toEventoBO(evento));
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public List<EventoConsultableDTO> obtenerEventos()throws NegocioException{
        try{
            List<EventoConsultableDTO> eventosDTO=new ArrayList<>();
            List<EntidadEvento> eventos=crudEvento.obtenerEventos();
            if(eventos!=null){
                for (EntidadEvento ev : eventos) {
                    eventosDTO.add(conversiones.toEventoDTO(ev));
                }
                return eventosDTO;
            }
            return null;
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public List<EventoConsultableDTO> obtenerEventos(String tipoEvento)throws NegocioException{
        try{
            List<EventoConsultableDTO> eventosDTO=new ArrayList<>();
            List<EntidadEvento> eventos=crudEvento.obtenerEventos(tipoEvento);
            if(eventos!=null){
                for (EntidadEvento ev : eventos) {
                    eventosDTO.add(conversiones.toEventoDTO(ev));
                }
                return eventosDTO;
            }
            return null;
        }catch(PersistenciaExceptionn e){
            throw new NegocioException(e.getMessage());
        }
    }
    
    public EventoConsultableDTO obtenerEvento(EventoConsultableDTO evento)throws NegocioException{
        try {
            EntidadEvento eventoBuscado=new EntidadEvento();
            eventoBuscado.setNombre(evento.getNombre());
            EntidadEvento ev=crudEvento.obtenerEvento(eventoBuscado);
            if(ev!=null)return conversiones.toEventoDTO(ev);
            return null;
        } catch (Exception e) {
            throw new NegocioException(e.getMessage());
        }
    }
}
