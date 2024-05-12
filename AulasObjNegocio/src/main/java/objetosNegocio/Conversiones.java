
package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import static DTOS.evento.TipoEventoEnumDTO.SEMANAL;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_UN_DIA;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_VARIOS_DIAS;
import DTOS.maestro.MaestroEditableDTO;
import DTOS.usuarios.UsuarioDTO;
import entidades.CrudCampus;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import static entidades.EntidadTipoEventoEnum.SEMANAL;
import static entidades.EntidadTipoEventoEnum.UNICO_UN_DIA;
import static entidades.EntidadTipoEventoEnum.UNICO_VARIOS_DIAS;
import entidades.EntidadUbicacion;
import entidades.EntidadUsuario;
import excepcioness.PersistenciaExceptionn;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author luiis
 */
class Conversiones {

    protected EntidadUbicacion toUbicacionBO(UbicacionDTO ubicacionDTO) {
        EntidadUbicacion ubicacion = new EntidadUbicacion();

        if (ubicacionDTO.getCampus() != null) {

            ubicacion.ssetCampusConversion(ubicacionDTO.getCampus().getId());

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
        ubicacion.ssetIdConversion(ubicacionDTO.getId());
        ubicacion.setPosicionX(ubicacionDTO.getPosicionX());
        ubicacion.setPosicionY(ubicacionDTO.getPosicionY());
        
        return ubicacion;
    }

    protected UbicacionDTO toUbicacionDTO(EntidadUbicacion ubicacionBO) {
        UbicacionDTO ubicacion;

        CampusConsultableDTO campusAux = new CampusConsultableDTO();
        campusAux.setId(ubicacionBO.ggetCampusConversion());
        if (ubicacionBO.getCampus() != null) {

            ubicacion = new UbicacionDTO(
                    ubicacionBO.getIdentificador(),
                    ubicacionBO.getDescripcion(),
                    campusAux
            );
        } else {
            ubicacion = new UbicacionDTO(ubicacionBO.getIdentificador());
        }
        ubicacion.setId(ubicacionBO.ggetIdConversion());
        ubicacion.setPosicionX(ubicacionBO.getPosicionX());
        ubicacion.setPosicionY(ubicacionBO.getPosicionY());
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
        if(maestro.getIdBD()!=null)maestroBO.ssetIdConversion(maestro.getIdBD());
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
        maestroDTO.setIdBD(maestro.ggetIdConversion());
        EventoConsultableDTO evento;
        if (eventos != null && !eventos.isEmpty()) {
            for (EntidadEvento ec : eventos) {
                evento=toEventoDTO(ec);
                evento.setMaestro(maestroDTO);
                eventosDTO.add(evento);
            }
            maestroDTO.setCalendario(eventosDTO);
        }
        return maestroDTO;
    }
    
    protected EntidadEvento toEventoBO(EventoConsultableDTO evento) {
        EntidadEvento eventoConvertido=null;
        String identificadorUbicacion = null;
        if (evento.getUbicacion() != null) {
            identificadorUbicacion = evento.getUbicacion().getId();
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
                        evento.getColor(),
                        evento.getFechaInicio().getTime(),
                        evento.getFechaFin().getTime(),
                        evento.getHoraInicio().getTime(),
                        (double) evento.getHorasDuracionEvento(),
                        nombreMaestro
                );
        }

        if (eventoConvertido != null) {
            eventoConvertido.setIdConversion(evento.getId());
            eventoConvertido.ssetUbicacionConversion(identificadorUbicacion);
        }

        return eventoConvertido;
    }

    protected EventoConsultableDTO toEventoDTO(EntidadEvento evento) {
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
            ubicacionDTO=new UbicacionDTO();
            ubicacionDTO.setId(evento.ggetUbicacionConversion());
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
            //eventoConvertido.setMaestro(maestro);
        }
        return eventoConvertido;
    }
    
    protected EntidadCampus toCampusBO(CampusConsultableDTO campusDTO) {

        EntidadCampus entidadCampus = new EntidadCampus();
        List<EntidadUbicacion> entidadUbicaciones = new ArrayList<>();
        entidadCampus.ssetIdConversion(campusDTO.getId());

        if (campusDTO.getUbicaciones() != null) {

            for (UbicacionDTO ubicacion : campusDTO.getUbicaciones()) {

                EntidadUbicacion ubicacionAux = toUbicacionBO(ubicacion);

                ubicacionAux.setCampus(entidadCampus.getId());
                
                entidadUbicaciones.add(ubicacionAux);
            }
            entidadCampus.setUbicaciones(entidadUbicaciones);
        }

        entidadCampus.setNombre(campusDTO.getNombre());
        entidadCampus.setUrl(campusDTO.getUrl());
        
        return entidadCampus;
    }

    protected CampusConsultableDTO toCampusDTO(EntidadCampus entidadCampus){
        CampusConsultableDTO campusDTO=new CampusConsultableDTO(entidadCampus.getNombre());
        campusDTO.setId(entidadCampus.ggetIdConversion());
        campusDTO.setUrl(entidadCampus.getUrl());
        
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
    
    protected UsuarioDTO toUsuarioDTO(EntidadUsuario  usuario){
        UsuarioDTO usaurioDto=new UsuarioDTO();
        usaurioDto.setIdUsuario(usuario.getIdUsuario());
        usaurioDto.setContraseña(usuario.getContraseña());
        usaurioDto.setAdministrador(usuario.isAdministrador());
        return usaurioDto;
        
    }
    
}
