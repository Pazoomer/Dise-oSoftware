/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package subsistemas.accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import excepciones.NegocioException;
import java.util.ArrayList;
import java.util.List;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;
import objetosNegocio.TipoEventoEnum;
import subsistemas.recuperarMaestro.FachadaRecuperarMaestro;
import subsistemas.recuperarMaestro.IRecuperarMaestro;

/**
 *
 * @author pauli
 */
public class ControlMaestro {
    private IRecuperarMaestro recuperaM;
    
    
     public MaestroEditableDTO agregarEventoCalendario(MaestroEditableDTO maestro) throws NegocioException{
        Maestro maestroEditable=convertMaestro(maestro);
        for(Evento evento: maestroEditable.getCalendario()){ 
            maestroEditable.agregarEventoCalendario(evento);   
        }
       return maestro;   
    }
     public MaestroEditableDTO editarMaestro(MaestroEditableDTO maestro) throws NegocioException{
          
        recuperaM=new FachadaRecuperarMaestro();
        MaestroEditableDTO maestroRecuperado=recuperaM.recuperarMaestro();
        Maestro maestroEditado=convertMaestro(maestroRecuperado);
        return maestroRecuperado;
     }
    private Maestro convertMaestro(MaestroEditableDTO maestro){
        Maestro maestroCon=new Maestro();
        maestroCon.setId(maestro.getId());
        maestroCon.setNombre(maestro.getNombre());
        maestroCon.setCubiculo(maestro.getCubiculo());
        List<Evento> listaEventos = new ArrayList<>();
        for (EventoConsultableDTO dtoEvento: maestro.getCalendario()){
            Evento even=toBO(dtoEvento);
            listaEventos.add(even);
        }
        maestroCon.setCalendario(listaEventos);
        return maestroCon;
    }
    private Evento toBO(EventoConsultableDTO evento){
        Evento eventoConvertido = null;
        switch (evento.getTipo()) {
            case UNICO_UN_DIA ->
                eventoConvertido = new Evento(
                        evento.getNombre(),
                        evento.getDescripcion(),
                        evento.getFechaInicio(),
                        evento.getUbicacion(),
                        evento.getHoraInicio(),
                        evento.getHorasDuracionEvento()
                );
            case UNICO_VARIOS_DIAS ->
                eventoConvertido = new Evento(
                        TipoEventoEnum.UNICO_VARIOS_DIAS,
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
                eventoConvertido = new Evento(
                        TipoEventoEnum.SEMANAL,
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
}
