/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package lum.subadmineventos;

import DTOS.evento.EventoConsultableDTO;
import adminEventos.FachadaAdminEventos;
import excepciones.NegocioException;
import java.util.List;

/**
 *
 * @author luiis
 */
public class SubAdminEventos {

    public static void main(String[] args) {
        adminEventos.IAdminEventos admin=new FachadaAdminEventos();
        try {
            List<EventoConsultableDTO> eventos=admin.obtenerEventos();
            if(eventos!=null){
                for (EventoConsultableDTO ev : eventos) {
                    System.out.println(ev);
                }
            }
        } catch (NegocioException e) {
            System.out.println(e);
        }
    }
}
