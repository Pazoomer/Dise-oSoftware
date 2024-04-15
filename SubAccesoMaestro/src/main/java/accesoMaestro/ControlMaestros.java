
package accesoMaestro;

import DTOS.evento.EventoConsultableDTO;
import DTOS.evento.TipoEventoEnumDTO;
import DTOS.maestro.MaestroEditableDTO;
//import conexion.IConexionDAO;
import excepciones.NegocioException;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.swing.ImageIcon;
import objetosNegocio.Evento;
import objetosNegocio.Maestro;
import objetosNegocio.TipoEventoEnum;
//import static objetosNegocio.TipoEventoEnum.SEMANAL;
//import static objetosNegocio.TipoEventoEnum.UNICO_UN_DIA;
//import static objetosNegocio.TipoEventoEnum.UNICO_VARIOS_DIAS;
/**
 *
 * @author t1pas
 */
public class ControlMaestros {
    private Maestro maestroBO;
    //private IConexionDAO conexion;
//
//    public ControlMaestros(IConexionDAO conexion) {
//        this.conexion = conexion;
//    }

    public ControlMaestros() {
        maestroBO=new Maestro();
    }
    
    protected boolean editarCalendario(List<EventoConsultableDTO> calendario) throws NegocioException{
        return false;
//        EntityManager entityManager=conexion.crearConexion();
//        try {
//            entityManager.getTransaction().begin();
//
//            // Obtener el primer maestro de la base de datos
//            Maestro maestro = entityManager.createQuery("SELECT m FROM Maestro m", Maestro.class)
//                    .setMaxResults(1)
//                    .getSingleResult();
//            
//            // Asociar la lista de eventos al maestro
//            List<Evento> eventos = new ArrayList<>();
//            for (EventoConsultableDTO eventoDTO : calendario) {
//                Evento evento=new Evento(
//                        TipoEventoEnum.UNICO_UN_DIA,
//                        eventoDTO.getNombre(),
//                        eventoDTO.getDescripcion(),
//                        eventoDTO.getDiasSemana(),
//                        eventoDTO.getUbicacion(),
//                        eventoDTO.getFechaInicio(),
//                        eventoDTO.getFechaFin(),
//                        eventoDTO.getHoraInicio(),
//                        eventoDTO.getHorasDuracionEvento(),
//                        maestro
//                );
//                if(eventoDTO.getTipo().equals(TipoEventoEnumDTO.SEMANAL)){
//                    evento.setTipoEvento(TipoEventoEnum.SEMANAL);
//                }else if(eventoDTO.getTipo().equals(TipoEventoEnumDTO.UNICO_VARIOS_DIAS))
//                    evento.setTipoEvento(TipoEventoEnum.UNICO_VARIOS_DIAS);
//                
//                // Configurar el evento con los datos del DTO (o como sea necesario)
//                eventos.add(evento);
//            }
//            maestro.setCalendario(eventos);
//
//            // Completar la transacción
//            entityManager.getTransaction().commit();
//            
//            return true;
//        } catch (Exception e) {
//            // Manejar cualquier excepción y hacer rollback en caso de error
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//            return false;
//        }
        //return calendarioEditado;
    }
    
    protected boolean editarMaestro(MaestroEditableDTO maestro) throws NegocioException {
        MaestroEditableDTO maestroEditado;
        try{
            maestroEditado=maestroBO.editarMaestro(maestro);
            return maestroEditado!=null;
        }catch(NegocioException e){
            throw e;
        }
//        EntityManager entityManager = conexion.crearConexion();
//        try {
//
//            entityManager.getTransaction().begin();
//
//            String jpql = "SELECT m FROM Maestro m WHERE m.idMaestro = :idMaestro";
//            TypedQuery<Maestro> query = entityManager.createQuery(jpql, Maestro.class);
//            query.setParameter("idMaestro", maestro.getId());
//            Maestro maestroPersistido = query.getSingleResult();
//
//            // Actualizar los atributos del maestro con los valores proporcionados en el DTO
//            maestroPersistido.setNombre(maestro.getNombre());
//            maestroPersistido.setCubiculo(maestro.getCubiculo());
//            maestroPersistido.setFoto(maestro.getFoto());
//            maestroPersistido.setDescripcion(maestro.getDescripcion());
//
//            // Completar la transacción
//            entityManager.getTransaction().commit();
//        } catch (NoResultException e) {
//            // Manejar el caso en que no se encuentre ningún Maestro con el valor proporcionado
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            throw new NegocioException("Maestro no encontrado para el id_maestro: " + maestro.getIdBD(), e);
//        } catch (Exception e) {
//            // Manejar cualquier otra excepción y hacer rollback en caso de error
//            if (entityManager.getTransaction().isActive()) {
//                entityManager.getTransaction().rollback();
//            }
//            e.printStackTrace();
//            throw new NegocioException("Error al editar el maestro", e);
//        } finally {
//            entityManager.close();
//        }
//        return true;

        /*
        recuperaM=new FachadaRecuperarMaestro();
        MaestroEditableDTO maestroRecuperado=recuperaM.recuperarMaestro();
        MaestroA maestroEditado=convertMaestro(maestroRecuperado);
        return maestroRecuperado;
         */
    }

    protected MaestroEditableDTO recuperarMaestro(MaestroEditableDTO maestro)
            throws NegocioException {
        try{
            return maestroBO.obtenerMaestro(maestro);
        }catch(NegocioException e){
            throw e;
        }
//        List<String> colores = new ArrayList<>();
//        for (EventoConsultableDTO ec : maestro.getCalendario()) {
//            colores.add(ec.getColor());
//        }
//        Maestro maestroBO = toMaestroBO(maestro);
//        maestroBO = maestroBO.obtenerMaestro(maestroBO);
//        MaestroEditableDTO maestroDTO = toMaestroDTO(maestroBO);
//        return maestroDTO;
    }

    protected MaestroEditableDTO recuperarMaestro2() throws NegocioException {
        return null;
//        List<EventoConsultableDTO> calendario = new ArrayList<>();
////
////        Calendar calendar = Calendar.getInstance();
////        calendar.set(Calendar.HOUR_OF_DAY, 10);
////        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
////        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);
//
//        Calendar fecha = Calendar.getInstance();
//        fecha.set(2024, 2, 17);
//        Calendar fecha2 = Calendar.getInstance();
//        fecha2.set(2024, 2, 20);
//
//        String diasSemana = "0101010";
//        Calendar horaInicio = Calendar.getInstance();
//        horaInicio.set(Calendar.HOUR_OF_DAY, 10);
//        horaInicio.set(Calendar.MINUTE, 30);
//        EventoConsultableDTO ev1 = new EventoConsultableDTO(TipoEventoEnumDTO.SEMANAL, "diseño de software", "clase de diseño", "amarillo",
//                diasSemana, "1826", fecha2, fecha2, horaInicio, 2.5f);
//        calendario.add(ev1);
//
//        String rutaRelativa = "fotoMaestro.png";
//
//        ImageIcon icon = new ImageIcon(rutaRelativa);
//
//        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));
//
//        MaestroEditableDTO maestro = new MaestroEditableDTO(1L, "Gibran Duran", "AV0900", "Doy asesorias de 9 a 11 de bases de datos los sabados y domingos", rutaRelativa, calendario);
//
//        return maestro;
    }

    /*
    protected MaestroEditableDTO AccesoCia() throws NegocioException {
        EntityManager entityManager=conexion.crearConexion();
        try {
            entityManager.getTransaction().begin();

            // Crear un nuevo maestro
            Maestro maestro = new Maestro();
            maestro.setNombre("Gibran Duran"); // Nombre del maestro harcodeado (puedes ajustarlo según tus necesidades)
            maestro.setCubiculo("AV0900");
            maestro.setDescripcion("Doy asesorias de 9 a 11 de bases de datos los sabados y domingos");
            maestro.setFoto("fotoMaestro.png");
            maestro.setIdMaestro(1L);
            
            // Persistir el maestro en la base de datos
            entityManager.persist(maestro);

            // Crear una lista de eventos asociados al maestro
            List<Evento> eventos = new ArrayList<>();
            
            Calendar calendar1d=Calendar.getInstance();
            calendar1d.set(Calendar.MONTH, 4);
            calendar1d.set(Calendar.DATE, 7);
            
            Calendar calendar1h=Calendar.getInstance();
            calendar1h.set(Calendar.HOUR, 9);
            
            Calendar calendar2d=Calendar.getInstance();
            calendar1d.set(Calendar.MONTH, 4);
            calendar1d.set(Calendar.DATE, 7);
            
            Calendar calendar2h=Calendar.getInstance();
            calendar2h.set(Calendar.HOUR, 7);
            
            Calendar calendar3d=Calendar.getInstance();
            calendar3d.set(Calendar.MONTH, 4);
            calendar3d.set(Calendar.DATE, 7);
            
            Calendar calendar3h=Calendar.getInstance();
            calendar3h.set(Calendar.HOUR, 12);
            
            Evento evento1 = new Evento(TipoEventoEnum.SEMANAL, "Examen 4", "Ultimo examen del semestre, no faltar", "1111111", "AV0100", "RED", calendar1d, null, calendar1h, 1.5f,maestro);
            eventos.add(evento1);
            
            Evento evento2 = new Evento(TipoEventoEnum.SEMANAL, "Clases de repaso", "Repaso para el ultimo examen", "1110000", "AV0100", "RED", calendar2d, null, calendar2h, 1.0f,maestro);
            eventos.add(evento2);
            
            Evento evento3 = new Evento(TipoEventoEnum.SEMANAL, "Fiesta de salida", "Para todos, incluso reprobados", "0001111", "AV0100", "RED", calendar3d, null, calendar3h, 2.0f,maestro);
            eventos.add(evento3);

            // Asociar la lista de eventos al maestro
            maestro.setCalendario(eventos);

            // Completar la transacción
            entityManager.getTransaction().commit();

            // Convertir el maestro persistido a un DTO editable y devolverlo
            return convertirAMaestroEditableDTO(maestro);
        } catch (Exception e) {
            // Manejar cualquier excepción y hacer rollback en caso de error
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            throw new NegocioException("Error al acceder al Maestro Cia", e);
        }
        
        
        /*
        List<EventoConsultableDTO> calendario = new ArrayList<>();
//
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.HOUR_OF_DAY, 10);
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
//        calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

        Calendar fecha=Calendar.getInstance();
        fecha.set(2024, 2, 17);
        Calendar fecha2=Calendar.getInstance();
        fecha2.set(2024, 2, 20);
        
        List<DiasSemanaDTO> diasSemana = new ArrayList<>();
        diasSemana.add(new DiasSemanaDTO(2));
        diasSemana.add(new DiasSemanaDTO(4));
        diasSemana.add(new DiasSemanaDTO(6));
        Calendar horaInicio = Calendar.getInstance();
        horaInicio.set(Calendar.HOUR, 10);
        EventoConsultableDTO ev1 = new EventoConsultableDTO(TipoEventoEnumDTO.SEMANAL, "diseño de software", "clase de diseño", "yellow",
                diasSemana, "1826", fecha2,fecha2, horaInicio, 2.5f);
        calendario.add(ev1);
        
        String rutaRealtiva = "fotoMaestro.png";

        ImageIcon icon = new ImageIcon(rutaRealtiva);

        ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));

        MaestroEditableDTO maestro = new MaestroEditableDTO(1L, "Gibran Duran", "AV0900", "Doy asesorias de 9 a 11 de bases de datos los sabados y domingos", scaledIcon, calendario);

        return maestro;
         */
    //}
    
}
