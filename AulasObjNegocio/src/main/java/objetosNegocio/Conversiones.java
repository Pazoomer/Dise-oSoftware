
package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import static DTOS.evento.TipoEventoEnumDTO.SEMANAL;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_UN_DIA;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_VARIOS_DIAS;
import DTOS.maestro.MaestroEditableDTO;
import entidades.CrudCampus;
import entidades.CrudMaestro;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import entidades.EntidadUbicacion;
import excepcioness.PersistenciaExceptionn;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 *
 * @author luiis
 */
class Conversiones {

    protected EntidadUbicacion toUbicacionBO(UbicacionDTO ubicacionDTO) {
        EntidadUbicacion ubicacion = new EntidadUbicacion();

        if (ubicacionDTO.getCampus() != null) {

            ubicacion.setCampus(ubicacionDTO.getCampus().getNombre());

        }

        List<EntidadEvento> entidadEventos = new ArrayList<>();

        if (ubicacionDTO.getEventos() != null) {

            for (EventoConsultableDTO evento : ubicacionDTO.getEventos()) {

                EntidadEvento eventoAux = toEventoBO(evento);

                entidadEventos.add(eventoAux);
            }
        }
        ubicacion.setEventos(entidadEventos);
        ubicacion.setDescripcion(ubicacionDTO.getDescripcion());
        ubicacion.setIdentificador(ubicacionDTO.getIdentificador());
        ubicacion.setIdConversion(ubicacionDTO.getId());
        
        return ubicacion;
    }

    protected UbicacionDTO toUbicacionDTO(EntidadUbicacion ubicacionBO){
        UbicacionDTO ubicacion;
        if(ubicacionBO.getCampus()!=null){
            ubicacion=new UbicacionDTO(
                    ubicacionBO.getIdentificador(),
                    ubicacionBO.getDescripcion(),
                    new CampusConsultableDTO(ubicacionBO.getCampus())
            );
        }else{
            ubicacion=new UbicacionDTO(ubicacionBO.getIdentificador());
        }
        ubicacion.setId(ubicacionBO.getIdConversion());
        return ubicacion;
    }
    
    protected EntidadMaestro toMaestroBO(MaestroEditableDTO maestro) {
        List<EventoConsultableDTO> eventos = maestro.getCalendario();
        List<EntidadEvento> eventosBO = new ArrayList<>();
        EntidadUbicacion ubicacionBO=null;
        EntidadMaestro maestroBO;
        
        if(maestro.getCubiculo()!=null)
            ubicacionBO=toUbicacionBO(maestro.getCubiculo());
        
        if (eventos != null && !eventos.isEmpty()) {
            
            for (EventoConsultableDTO ec : eventos) {
                eventosBO.add(toEventoBO(ec));
            }
            maestroBO= new EntidadMaestro(
                    maestro.getId(),
                    maestro.getNombre(),
                    ubicacionBO,
                    maestro.getDescripcion(),
                    maestro.getFoto(),
                    eventosBO
            );
        }else{
            maestroBO= new EntidadMaestro(
                    maestro.getId(),
                    maestro.getNombre(),
                    ubicacionBO,
                    maestro.getDescripcion(),
                    maestro.getFoto()
            );
        }
        if(maestro.getIdBD()!=null)maestroBO.setIdConversion(maestro.getIdBD());
        return maestroBO;
    }

    protected MaestroEditableDTO toMaestroDTO(EntidadMaestro maestro) {
        List<EntidadEvento> eventos = maestro.getCalendario();
        List<EventoConsultableDTO> eventosDTO = new ArrayList<>();
        MaestroEditableDTO maestroDTO = new MaestroEditableDTO(
                maestro.getIdMaestro(),
                maestro.getNombre(),
                toUbicacionDTO(maestro.getCubiculo()),
                maestro.getDescripcion(),
                maestro.getFoto()
        );
        maestroDTO.setIdBD(maestro.getIdConversion());
        if (eventos != null && !eventos.isEmpty()) {
            for (EntidadEvento ec : eventos) {
                eventosDTO.add(toEventoDTO(ec,maestroDTO));
            }
            maestroDTO.setCalendario(eventosDTO);
        }
        return maestroDTO;
    }
    
    protected EntidadEvento toEventoBO(EventoConsultableDTO evento) {
        EntidadEvento eventoConvertido=null;
        //EntidadUbicacion ubi=null;
//        EntidadMaestro maestroBO=null;
//        
//        if(evento.getMaestro()!=null) maestroBO=toMaestroBO(evento.getMaestro());
        
//        if(evento.getUbicacion()!=null)
//            ubi=toUbicacionBO(evento.getUbicacion());
        String identificadorUbicacion = null;
        if (evento.getUbicacion() != null) {
            identificadorUbicacion = evento.getUbicacion().getIdentificador();
        }
        
        String nombreMaestro = null;
        if (evento.getMaestro() != null) {
            nombreMaestro = evento.getMaestro().getNombre();
        }

        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new EntidadEvento(
                        evento.getNombre(),
                        evento.getColor(),
                        identificadorUbicacion,
                        evento.getDescripcion(),
                        evento.getFechaInicio().getTime(),
                        evento.getHoraInicio().getTime(),
                        (double) evento.getHorasDuracionEvento(),
                        nombreMaestro
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new EntidadEvento(
                        EntidadTipoEventoEnum.UNICO_VARIOS_DIAS,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        identificadorUbicacion,
                        evento.getColor(),
                        evento.getFechaInicio().getTime(),
                        evento.getFechaFin().getTime(),
                        evento.getHoraInicio().getTime(),
                        (double)evento.getHorasDuracionEvento(),
                        nombreMaestro
                );
            case SEMANAL ->
                eventoConvertido = new EntidadEvento(
                        EntidadTipoEventoEnum.SEMANAL,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        identificadorUbicacion,
                        evento.getColor(),
                        evento.getFechaInicio().getTime(),
                        evento.getFechaFin().getTime(),
                        evento.getHoraInicio().getTime(),
                        (double)evento.getHorasDuracionEvento(),
                        nombreMaestro
                );
        }
        if(eventoConvertido!=null)eventoConvertido.setIdConversion(evento.getId());
        return eventoConvertido;
    }
    
    protected EventoConsultableDTO toEventoDTO(EntidadEvento evento,MaestroEditableDTO maestro) {
        EventoConsultableDTO eventoConvertido = null;
        UbicacionDTO ubicacionDTO=null;
        Date date;
        Calendar fechaInicio = Calendar.getInstance();
        Calendar fechaFin = Calendar.getInstance();
        Calendar horaInicio = Calendar.getInstance();
        if(evento.getFechaInicio()!=null){
            date = evento.getFechaInicio();
            fechaInicio.setTime(date);
        }
        if(evento.getFechaFin()!=null){
            date = evento.getFechaFin();
            fechaFin.setTime(date);
        }
        if(evento.getHoraInicio()!=null){
            date = evento.getHoraInicio();
            horaInicio.setTime(date);
        }
        if(evento.getUbicacion()!=null) {
            CrudCampus crudCampus = new CrudCampus();

            EntidadUbicacion ubicacion = new EntidadUbicacion();
            ubicacion.setIdentificador(evento.getUbicacion());
            try {
                ubicacion = crudCampus.obtenerUbi(ubicacion);
                if(ubicacion!=null)ubicacionDTO=toUbicacionDTO(ubicacion);
                else
                    System.out.println("ubicacion null");
            } catch (PersistenciaExceptionn e) {
                System.out.println(e);
            }
        }
        
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->{
                eventoConvertido = new EventoConsultableDTO(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        ubicacionDTO,
                        fechaInicio,
                        horaInicio,
                        evento.getHorasDuracionEvento()
                );
            }
                
            case UNICO_VARIOS_DIAS ->{
                eventoConvertido = new EventoConsultableDTO(
                        TipoEventoEnumDTO.UNICO_VARIOS_DIAS,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getDiasSemana(),
                        ubicacionDTO,
                        fechaInicio,
                        fechaFin,
                        horaInicio,
                        evento.getHorasDuracionEvento()
                );
            }
                
            case SEMANAL ->{
                eventoConvertido = new EventoConsultableDTO(
                        TipoEventoEnumDTO.SEMANAL,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getDiasSemana(),
                        ubicacionDTO,
                        fechaInicio,
                        fechaFin,
                        horaInicio,
                        evento.getHorasDuracionEvento()
                );
            }
               
        }
        if(eventoConvertido!=null){
            eventoConvertido.setId(evento.getIdConversion());
            eventoConvertido.setMaestro(maestro);
        }
        return eventoConvertido;
    }
    
    protected EntidadCampus toCampusBO(CampusConsultableDTO campusDTO) {

        EntidadCampus entidadCampus = new EntidadCampus();
        List<EntidadUbicacion> entidadUbicaciones = new ArrayList<>();

        if (campusDTO.getUbicaciones() != null) {

            for (UbicacionDTO ubicacion : campusDTO.getUbicaciones()) {

                EntidadUbicacion ubicacionAux = toUbicacionBO(ubicacion);

                ubicacionAux.setCampus(entidadCampus.getNombre());

                entidadUbicaciones.add(ubicacionAux);
            }
            entidadCampus.setUbicaciones(entidadUbicaciones);
        }

        entidadCampus.setNombre(campusDTO.getNombre());
        entidadCampus.setIdConversion(campusDTO.getId());
        return entidadCampus;
    }

    protected CampusConsultableDTO toCampusDTO(EntidadCampus entidadCampus){
        CampusConsultableDTO campusDTO=new CampusConsultableDTO(entidadCampus.getNombre());
        campusDTO.setId(entidadCampus.getIdConversion());
        
        if (entidadCampus.getUbicaciones()!=null) {
            
            List<UbicacionDTO> ubicaciones=new ArrayList<>();
            
            for (EntidadUbicacion ubicacion: entidadCampus.getUbicaciones()){
                
                UbicacionDTO ubicacionBO=this.toUbicacionDTO(ubicacion);
                
                ubicacionBO.setCampus(campusDTO);
                
                ubicaciones.add(ubicacionBO);
            }
            campusDTO.setUbicaciones(ubicaciones);
            
        }
        return campusDTO;
    }
    
}
