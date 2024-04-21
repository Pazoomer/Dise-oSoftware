
package objetosNegocio;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
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
    
    protected EntidadUbicacion toUbicacionBO(UbicacionDTO ubicacionDTO){
        EntidadUbicacion ubicacion;
        if(ubicacionDTO.getCampus()!=null){
            ubicacion=new EntidadUbicacion(
                ubicacionDTO.getIdentificador(),
                new EntidadCampus(ubicacionDTO.getCampus().getNombre()),
                ubicacionDTO.getDescripcion());
        }else{
            ubicacion=new EntidadUbicacion(ubicacionDTO.getIdentificador());
        }
        ubicacion.setId(ubicacionDTO.getId());
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
        ubicacion.setId(ubicacionBO.getId());
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
        maestroBO.setIdBd(maestro.getIdBD());
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
        maestroDTO.setIdBD(maestro.getIdBd());
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
                        evento.getHorasDuracionEvento(),
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
                        evento.getHorasDuracionEvento(),
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
                        evento.getHorasDuracionEvento(),
                        maestroBO
                );
        }
        if(eventoConvertido!=null)eventoConvertido.setId(evento.getId());
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
            eventoConvertido.setId(evento.getId());
            eventoConvertido.setMaestro(maestroDTO2);
        }
        return eventoConvertido;
    }
    
}
