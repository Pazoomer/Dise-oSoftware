
package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import static DTOS.evento.TipoEventoEnumDTO.SEMANAL;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_UN_DIA;
import static DTOS.evento.TipoEventoEnumDTO.UNICO_VARIOS_DIAS;
import DTOS.maestro.MaestroEditableDTO;
import entidades.EntidadCampus;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import entidades.EntidadUbicacion;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
class Conversiones {

    protected EntidadUbicacion toUbicacionBO(UbicacionDTO ubicacionDTO) {
        EntidadUbicacion ubicacion = new EntidadUbicacion();

        if (ubicacionDTO.getCampus() != null) {

            ubicacion.setCampus(toCampusBO(ubicacionDTO.getCampus()));

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
                    new CampusConsultableDTO(ubicacionBO.getCampus().getNombre())
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
        maestroBO.setIdConversion(maestro.getIdBD());
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
        EntidadUbicacion ubi=null;
        EntidadMaestro maestroBO=null;
        
        if(evento.getMaestro()!=null) maestroBO=toMaestroBO(evento.getMaestro());
        
        if(evento.getUbicacion()!=null)
            ubi=toUbicacionBO(evento.getUbicacion());
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new EntidadEvento(
                        evento.getNombre(),
                        evento.getColor(),
                        ubi,
                        evento.getDescripcion(),
                        evento.getFechaInicio(),
                        evento.getHoraInicio(),
                        (double) evento.getHorasDuracionEvento(),
                        maestroBO
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new EntidadEvento(
                        EntidadTipoEventoEnum.UNICO_VARIOS_DIAS,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        ubi,
                        evento.getColor(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        (double)evento.getHorasDuracionEvento(),
                        maestroBO
                );
            case SEMANAL ->
                eventoConvertido = new EntidadEvento(
                        EntidadTipoEventoEnum.SEMANAL,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        ubi,
                        evento.getColor(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        (double)evento.getHorasDuracionEvento(),
                        maestroBO
                );
        }
        if(eventoConvertido!=null)eventoConvertido.setIdConversion(evento.getId());
        return eventoConvertido;
    }
    
    protected EventoConsultableDTO toEventoDTO(EntidadEvento evento,MaestroEditableDTO maestroDTO) {
        EventoConsultableDTO eventoConvertido = null;
        UbicacionDTO ubicacionDTO=null;
        MaestroEditableDTO maestroDTO2=null;
        
        if(evento.getUbicacion()!=null) ubicacionDTO=toUbicacionDTO(evento.getUbicacion());
        
        if(maestroDTO!=null)maestroDTO2=maestroDTO;
        
        else if(evento.getMaestro()!=null)maestroDTO2=toMaestroDTO(evento.getMaestro());
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new EventoConsultableDTO(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        ubicacionDTO,
                        evento.getFechaInicio(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new EventoConsultableDTO(
                        TipoEventoEnumDTO.UNICO_VARIOS_DIAS,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getDiasSemana(),
                        ubicacionDTO,
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case SEMANAL ->
                eventoConvertido = new EventoConsultableDTO(
                        TipoEventoEnumDTO.SEMANAL,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getDiasSemana(),
                        ubicacionDTO,
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
        }
        if(eventoConvertido!=null){
            eventoConvertido.setId(evento.getIdConversion());
            eventoConvertido.setMaestro(maestroDTO2);
        }
        return eventoConvertido;
    }
    
    protected EntidadCampus toCampusBO(CampusConsultableDTO campusDTO) {

        EntidadCampus entidadCampus = new EntidadCampus();
        List<EntidadUbicacion> entidadUbicaciones = new ArrayList<>();

        if (campusDTO.getUbicaciones() != null) {

            for (UbicacionDTO ubicacion : campusDTO.getUbicaciones()) {

                EntidadUbicacion ubicacionAux = toUbicacionBO(ubicacion);

                ubicacionAux.setCampus(entidadCampus);

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
