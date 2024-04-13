/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package objetosNegocio;

import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import DTOS.maestro.MaestroEditableDTO;
import entidades.EntidadEvento;
import entidades.EntidadMaestro;
import entidades.EntidadTipoEventoEnum;
import static entidades.EntidadTipoEventoEnum.SEMANAL;
import static entidades.EntidadTipoEventoEnum.UNICO_UN_DIA;
import static entidades.EntidadTipoEventoEnum.UNICO_VARIOS_DIAS;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luiis
 */
class Conversiones {
    
    protected EntidadMaestro toMaestroBO(MaestroEditableDTO maestro) {
        List<EventoConsultableDTO> eventos = maestro.getCalendario();
        List<EntidadEvento> eventosBO = new ArrayList<>();
        if (!eventos.isEmpty()) {
            for (EventoConsultableDTO ec : eventos) {
                eventosBO.add(toEventoBO(ec));
            }
        }
        EntidadMaestro maestroConvertido = new EntidadMaestro(
                1L,
                maestro.getNombre(),
                maestro.getCubiculo(),
                maestro.getDescripcion(),
                maestro.getFoto(),
                eventosBO
        );
        return maestroConvertido;
    }

    protected MaestroEditableDTO toMaestroDTO(EntidadMaestro maestro) {
        List<EntidadEvento> eventos = maestro.getCalendario();
        List<EventoConsultableDTO> eventosDTO = new ArrayList<>();
        if (!eventos.isEmpty()) {
            for (EntidadEvento ec : eventos) {
                eventosDTO.add(toEventoDTO(ec)
                );
            }
        }
        MaestroEditableDTO maestroDTO = new MaestroEditableDTO(
                maestro.getNombre(),
                maestro.getCubiculo(),
                maestro.getDescripcion(),
                maestro.getFoto(),
                eventosDTO
        );
        return maestroDTO;
    }
    
    protected EntidadEvento toEventoBO(EventoConsultableDTO evento) {
        EntidadEvento eventoConvertido = null;
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new EntidadEvento(
                        evento.getColor(),
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getFechaInicio(),
                        evento.getUbicacion(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new EntidadEvento(
                        evento.getColor(),
                        EntidadTipoEventoEnum.UNICO_VARIOS_DIAS,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case SEMANAL ->
                eventoConvertido = new EntidadEvento(
                        evento.getColor(),
                        EntidadTipoEventoEnum.SEMANAL,
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getDiasSemana(),
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
        }
        return eventoConvertido;
    }
    
    protected EventoConsultableDTO toEventoDTO(EntidadEvento evento) {
        EventoConsultableDTO eventoConvertido = null;
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new EventoConsultableDTO(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getColor(),
                        evento.getUbicacion(),
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
                        evento.getUbicacion(),
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
                        evento.getUbicacion(),
                        evento.getFechaInicio(),
                        evento.getFechaFin(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
        }
        return eventoConvertido;
    }
    
}
