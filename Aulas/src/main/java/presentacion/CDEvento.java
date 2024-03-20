
package presentacion;

import DTOS.evento.EventoConsultableDTO;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import presentacion.pantallas.MapaCalendario;
import presentacion.pantallas.PrincipalCalendario;

/**
 *
 * @author t1pas
 */
public class CDEvento extends javax.swing.JDialog {

    public final PrincipalCalendario calendario;
    private final java.awt.Frame parent;
    private EventoConsultableDTO eventoEditable;
    /**
     * Creates new form CDEvento
     * @param parent
     * @param calendario
     * @param modal
     */
    public CDEvento(java.awt.Frame parent,PrincipalCalendario calendario, boolean modal) {
        super(parent, modal);
        initComponents();
        this.calendario=calendario;
        this.parent=parent;
        this.setSize(500, 620);
        actualizarPermisos();
        cargarIconos();
        
        
    }
    
    /**
     * Carga los iconos en los botones de la interfaz.
     */
    private void cargarIconos() {
        // Carga el icono de retorno en el botón btnAtras
        ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
        btnAtras.setIcon(iconoReturn);
        // Carga el icono de mapa en el botón btnMapa
        ImageIcon iconoMapa = new ImageIcon(getClass().getResource("/imagenes/icons8-map-30 blue.png"));
        btnMapa.setIcon(iconoMapa);
        // Carga el icono de guardar en el botón btnGuardar
        ImageIcon iconoGuardar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
        btnAñadir.setIcon(iconoGuardar);
        // Carga el icono de limpiar en el botón btnLimpiar
        ImageIcon iconoLimpiar = new ImageIcon(getClass().getResource("/imagenes/icons8-remove-48.png"));
        btnLimpiar.setIcon(iconoLimpiar);
        // Carga el icono de datos en el label lblInfoEventoEstatico
        ImageIcon iconoDatos = new ImageIcon(getClass().getResource("/imagenes/icons8-note-50.png"));
        lblInfoEventoEstatico.setIcon(iconoDatos);
    }
    
    public CDEvento(java.awt.Frame parent,PrincipalCalendario calendario,EventoConsultableDTO eventoEditable , boolean modal) {
        super(parent, modal);
        initComponents();
        this.calendario=calendario;
        this.parent=parent;
        this.eventoEditable=eventoEditable;
        this.setSize(500, 620);
    }
    
    private void añadirEvento2(){
        String tipo=(String) this.cmbTipo.getSelectedItem();
        String descripcion = this.txtDescripcion.getText();
        String nombre = this.txtNombre.getText();
        String ubicacion = this.txtUbicacion.getText();
        Calendar fecha=Calendar.getInstance();
        if(tipo.equalsIgnoreCase("Unico"))
            fecha = this.dtcFecha.getCalendar();
        Color color = this.lblEjemploEstatico.getForeground();
        float horasDuracion=Float.parseFloat((String)cmbDuracionEvento.getSelectedItem());
        List<Integer> diasSemana=new ArrayList<>();
        JCheckBox[] arrChkBx={chbDomingo,chbLunes,chbMartes,chbMiercoles,chbJueves,chbViernes,chbSabado};
        for (int i = 0; i < 7; i++) {
            if(arrChkBx[i].isSelected()){
                diasSemana.add(i+1);
                System.out.println("dia numero: "+(i+1));
            } 
        }
        
        String horaSeleccionada=(String)cmbHora.getSelectedItem();
        int hora=Integer.parseInt(horaSeleccionada.substring(0, 2));
        int minutos=Integer.parseInt(horaSeleccionada.substring(3));
        LocalTime horaInicio=LocalTime.of(hora, minutos);
        System.out.println("hora inicio evento desde cdEvento: "+hora+":"+minutos);
        
        EventoConsultableDTO eventoN;
        
//        if(tipo.equalsIgnoreCase("unico")){
            eventoN=new EventoConsultableDTO(tipo, nombre, descripcion, color, diasSemana, ubicacion, fecha, horaInicio, horasDuracion);
//        }else{
//            eventoN=new EventoConsultableDTO(tipo, nombre, descripcion, color, 
//                diasSemana, ubicacion, fecha, horaInicio, horasDuracion);
//        }
        
        JOptionPane.showMessageDialog(null, "Evento añadido con exito", "Mensaje de confirmación", JOptionPane.INFORMATION_MESSAGE);
        calendario.añadirEvento(eventoN);
    }
    
    //TODO
    //Al hacer clic sobre el boton deberia intentar añadir el evento al calendario.
    //primero se asegura que no haya eventos a la misma hora
    private void añadirEvento(){
        String tipo=(String) this.cmbTipo.getSelectedItem();
        String descripcion = this.txtDescripcion.getText();
        String nombre = this.txtNombre.getText();
        String ubicacion = this.txtUbicacion.getText();
        Date fecha = this.dtcFecha.getDate();
        Color color = this.lblEjemploEstatico.getForeground();
        Calendar calendar=null;
        boolean[] diasSemana=new boolean[7];
        diasSemana[0]=this.chbLunes.isSelected();
        diasSemana[1]=this.chbMartes.isSelected();
        diasSemana[2]=this.chbMiercoles.isSelected();
        diasSemana[3]=this.chbJueves.isSelected();
        diasSemana[4]=this.chbViernes.isSelected();
        diasSemana[5]=this.chbSabado.isSelected();
        diasSemana[6]=this.chbDomingo.isSelected();
        String AMPM=(String)this.cmbAMPM.getSelectedItem();
        String hora=(String)this.cmbHora.getSelectedItem();
        if (hora.equals("-1")) {
            hora="7:00";
        }
        int[] horaNumerica = convertirHora(hora);

        if (tipo.equalsIgnoreCase("semanal")) {

            int dias = 0;
            for (int i = 0; i < 7; i++) {
                if (diasSemana[i] == true) {
                    dias++;
                }
            }
            if (dias == 0) {
                JOptionPane.showMessageDialog(null, "Debes seleccionar al menos un dia de la semana para los eventos semanales", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
        }
        if (fecha == null && tipo.equalsIgnoreCase("unico")) {
            JOptionPane.showMessageDialog(null, "Debes colocar una fecha para los eventos unicos", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            return;
        } else if (fecha == null && tipo.equalsIgnoreCase("semanal")) {
            fecha = new Date();
        }

        if (AMPM.equalsIgnoreCase("PM")) {
            horaNumerica[0] = horaNumerica[0] + 12;
        }

        if (horaNumerica[0] == 12) {
            horaNumerica[0] = 0;
        } else if (horaNumerica[0] < 7 || horaNumerica[0] > 19) {
            JOptionPane.showMessageDialog(null, "No se pueden hacer eventos fuera del horario escolar (7:00 AM - 7:30 PM)", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        
        try {
            calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY,horaNumerica[0] );
            calendar.set(Calendar.MINUTE,horaNumerica[1] );
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "La fecha no es valida", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        if (calendar==null) {
            JOptionPane.showMessageDialog(null, "La fecha no es valida", "Mensaje de error", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        EventoConsultableDTO evento = new EventoConsultableDTO(tipo, nombre, descripcion, color, diasSemana, ubicacion, calendar,null);
        JOptionPane.showMessageDialog(null, "Evento añadido con exito", "Mensaje de confirmación", JOptionPane.INFORMATION_MESSAGE);
        System.out.println(evento);
        calendario.añadirEvento(evento);

    }

    private int[] convertirHora(String horaString) {
        String[] partes = horaString.split(":");
        int hora = Integer.parseInt(partes[0]);
        int minutos = Integer.parseInt(partes[1]);

        return new int[]{hora, minutos};
    }

    public void guardarUbicacion(String ubicacion) {
        this.txtUbicacion.setText(ubicacion);
    }
    
    /**
     * Abre el frame MapaCalendario
     */
    private void abrirMapa(){
        new MapaCalendario(this).setVisible(true);
        calendario.setVisible(false);
        this.setVisible(false);
    }
    
    /**
     * Abre un seleccionador de color y se le asigna al evento
     */
    private void abrirSeleccionColor() {
        CuadroDialogoColor color = new CuadroDialogoColor(parent);
        color.setVisible(true);
        this.lblEjemploEstatico.setForeground(color.getColor());
    }
    
    /**
     * Abre un seleccionador de fecha y se le asigna al evento
     */
    private void abrirSeleccionFecha(){
        CuadroDialogoCalendario calendarioFecha = new CuadroDialogoCalendario(parent);
        calendarioFecha.setVisible(true);
        if (calendarioFecha.getDate()!=null) {
            this.dtcFecha.setDate(calendarioFecha.getDate());  
        }
        
    }
    
    public void desplegarEvento(){
        txtNombre.setText(this.eventoEditable.getNombre());
        txtDescripcion.setText(this.eventoEditable.getDescripcion());
        txtUbicacion.setText(this.eventoEditable.getUbicacion());
        int horasDuracion=(int)eventoEditable.getHorasDuracionEvento();
        cmbDuracionEvento.setSelectedItem(String.valueOf(horasDuracion));
        String horaInicio=eventoEditable.getHoraInicio().toString();
        System.out.println(horaInicio);
        for (int i = 0; i < cmbHora.getModel().getSize(); i++) {
            if(horaInicio.equals(cmbHora.getModel().getElementAt(i))){
                cmbHora.setSelectedIndex(i);
                break;
            }
        }
        if(Integer.parseInt(horaInicio.substring(0,2))<12)
            cmbAMPM.setSelectedIndex(0);
        else
            cmbAMPM.setSelectedIndex(1);
        if(eventoEditable.getTipo().equals("unico")){
            dtcFecha.setDate(this.eventoEditable.getFechaInicio().getTime());
            cmbTipo.setSelectedIndex(1);
        }
        else if(eventoEditable.getTipo().equalsIgnoreCase("semanal")){
            cmbTipo.setSelectedIndex(0);
            List<Integer> diasSemana=eventoEditable.getDiasSemana2();
            for(int i:diasSemana){
                switch(i){
                    case 1 -> chbDomingo.setSelected(true);
                    case 2 -> chbLunes.setSelected(true);
                    case 3 -> chbMartes.setSelected(true);
                    case 4 -> chbMiercoles.setSelected(true);
                    case 5 -> chbJueves.setSelected(true);
                    case 6 -> chbViernes.setSelected(true);
                    case 7 -> chbSabado.setSelected(true);
                }
            }
        }
        txtNombre.setEditable(false);
        txtDescripcion.setEditable(false);
        txtUbicacion.setEditable(false);
        cmbHora.setEnabled(false);
        cmbAMPM.setEnabled(false);
        cmbTipo.setEnabled(false);
        cmbDuracionEvento.setEnabled(false);
        dtcFecha.setEnabled(false);
        chbDomingo.setEnabled(false);
        chbLunes.setEnabled(false);
        chbMartes.setEnabled(false);
        chbMiercoles.setEnabled(false);
        chbJueves.setEnabled(false);
        chbViernes.setEnabled(false);
        chbSabado.setEnabled(false);
    }
    
    /**
     * Establece vacio los campos de texto y la fecha
     */
    private void limpiar(){
       txtDescripcion.setText("");
        txtNombre.setText("");
        txtUbicacion.setText("");
        this.dtcFecha.setDate(null);
        this.chbLunes.setSelected(false);
        this.chbMartes.setSelected(false);
        this.chbMiercoles.setSelected(false);
        this.chbJueves.setSelected(false);
        this.chbViernes.setSelected(false);
        this.chbSabado.setSelected(false);
        this.chbDomingo.setSelected(false);
        actualizarPermisos();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlEvento = new javax.swing.JPanel();
        lblUbicacionEstatico = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnMapa = new javax.swing.JButton();
        lblDescripcionEstatico = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAñadir = new javax.swing.JButton();
        cmbTipo = new javax.swing.JComboBox<>();
        lblTipoEstatico = new javax.swing.JLabel();
        btnColor = new javax.swing.JButton();
        lblEjemploEstatico = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        chbLunes = new javax.swing.JCheckBox();
        chbMartes = new javax.swing.JCheckBox();
        chbMiercoles = new javax.swing.JCheckBox();
        chbJueves = new javax.swing.JCheckBox();
        chbViernes = new javax.swing.JCheckBox();
        chbSabado = new javax.swing.JCheckBox();
        chbDomingo = new javax.swing.JCheckBox();
        dtcFecha = new com.toedter.calendar.JDateChooser();
        lblFecha = new javax.swing.JLabel();
        lblHora = new javax.swing.JLabel();
        cmbHora = new javax.swing.JComboBox<>();
        cmbAMPM = new javax.swing.JComboBox<>();
        lblNombreEstatico = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        btnAtras = new javax.swing.JButton();
        lblDuracion = new javax.swing.JLabel();
        cmbDuracionEvento = new javax.swing.JComboBox<>();
        lblHorasDuracion = new javax.swing.JLabel();
        lblInfoEventoEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        pnlEvento.setBackground(new java.awt.Color(255, 255, 255));
        pnlEvento.setLayout(null);

        lblUbicacionEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblUbicacionEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblUbicacionEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblUbicacionEstatico.setText(" Ubicación:");
        lblUbicacionEstatico.setOpaque(true);
        pnlEvento.add(lblUbicacionEstatico);
        lblUbicacionEstatico.setBounds(220, 90, 200, 20);
        pnlEvento.add(txtUbicacion);
        txtUbicacion.setBounds(220, 110, 200, 30);

        btnMapa.setBackground(new java.awt.Color(255, 255, 255));
        btnMapa.setBorder(null);
        btnMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMapaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnMapaMouseExited(evt);
            }
        });
        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });
        pnlEvento.add(btnMapa);
        btnMapa.setBounds(420, 100, 50, 50);

        lblDescripcionEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblDescripcionEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDescripcionEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblDescripcionEstatico.setText(" Descripcion del evento:");
        lblDescripcionEstatico.setOpaque(true);
        pnlEvento.add(lblDescripcionEstatico);
        lblDescripcionEstatico.setBounds(220, 150, 138, 14);
        pnlEvento.add(txtDescripcion);
        txtDescripcion.setBounds(220, 170, 250, 100);

        btnAñadir.setBackground(new java.awt.Color(255, 255, 255));
        btnAñadir.setBorder(null);
        btnAñadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadirMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAñadirMouseExited(evt);
            }
        });
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        pnlEvento.add(btnAñadir);
        btnAñadir.setBounds(100, 400, 70, 50);

        cmbTipo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semanal", "Unico" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });
        pnlEvento.add(cmbTipo);
        cmbTipo.setBounds(30, 50, 160, 21);

        lblTipoEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblTipoEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblTipoEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblTipoEstatico.setText(" Tipo de evento:");
        lblTipoEstatico.setOpaque(true);
        pnlEvento.add(lblTipoEstatico);
        lblTipoEstatico.setBounds(30, 30, 160, 20);

        btnColor.setBackground(new java.awt.Color(22, 81, 198));
        btnColor.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnColor.setForeground(new java.awt.Color(255, 255, 255));
        btnColor.setText("Asignar color");
        btnColor.setBorder(null);
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });
        pnlEvento.add(btnColor);
        btnColor.setBounds(30, 305, 140, 20);

        lblEjemploEstatico.setText("EJEMPLO COLOR");
        pnlEvento.add(lblEjemploEstatico);
        lblEjemploEstatico.setBounds(30, 340, 140, 14);

        btnLimpiar.setBackground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setBorder(null);
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseExited(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        pnlEvento.add(btnLimpiar);
        btnLimpiar.setBounds(310, 400, 60, 50);

        chbLunes.setBackground(new java.awt.Color(255, 255, 255));
        chbLunes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbLunes.setText("Lunes");
        chbLunes.setBorder(null);
        pnlEvento.add(chbLunes);
        chbLunes.setBounds(30, 80, 85, 15);

        chbMartes.setBackground(new java.awt.Color(255, 255, 255));
        chbMartes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbMartes.setText("Martes");
        chbMartes.setBorder(null);
        pnlEvento.add(chbMartes);
        chbMartes.setBounds(30, 100, 85, 15);

        chbMiercoles.setBackground(new java.awt.Color(255, 255, 255));
        chbMiercoles.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbMiercoles.setText("Miercoles");
        chbMiercoles.setBorder(null);
        chbMiercoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbMiercolesActionPerformed(evt);
            }
        });
        pnlEvento.add(chbMiercoles);
        chbMiercoles.setBounds(30, 120, 85, 15);

        chbJueves.setBackground(new java.awt.Color(255, 255, 255));
        chbJueves.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbJueves.setText("Jueves");
        chbJueves.setBorder(null);
        chbJueves.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                chbJuevesActionPerformed(evt);
            }
        });
        pnlEvento.add(chbJueves);
        chbJueves.setBounds(30, 140, 85, 15);

        chbViernes.setBackground(new java.awt.Color(255, 255, 255));
        chbViernes.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbViernes.setText("Viernes");
        chbViernes.setBorder(null);
        pnlEvento.add(chbViernes);
        chbViernes.setBounds(120, 80, 85, 15);

        chbSabado.setBackground(new java.awt.Color(255, 255, 255));
        chbSabado.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbSabado.setText("Sabado");
        chbSabado.setBorder(null);
        pnlEvento.add(chbSabado);
        chbSabado.setBounds(120, 100, 85, 15);

        chbDomingo.setBackground(new java.awt.Color(255, 255, 255));
        chbDomingo.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        chbDomingo.setText("Domingo");
        chbDomingo.setBorder(null);
        pnlEvento.add(chbDomingo);
        chbDomingo.setBounds(120, 120, 85, 15);
        pnlEvento.add(dtcFecha);
        dtcFecha.setBounds(30, 200, 160, 30);

        lblFecha.setBackground(new java.awt.Color(255, 255, 255));
        lblFecha.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblFecha.setForeground(new java.awt.Color(22, 81, 198));
        lblFecha.setText(" Fecha:");
        lblFecha.setOpaque(true);
        pnlEvento.add(lblFecha);
        lblFecha.setBounds(30, 176, 160, 20);

        lblHora.setBackground(new java.awt.Color(255, 255, 255));
        lblHora.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHora.setForeground(new java.awt.Color(22, 81, 198));
        lblHora.setText(" Hora:");
        lblHora.setOpaque(true);
        pnlEvento.add(lblHora);
        lblHora.setBounds(30, 240, 160, 20);

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "01:00", "01:30", "02:00", "02:30", "03:00", "03:30", "04:00", "04:30", "05:00", "05:30", "06:00", "06:30", "07:00", "07:30", "08:00", "08:30", "09:00", "09:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30" }));
        pnlEvento.add(cmbHora);
        cmbHora.setBounds(30, 260, 68, 20);

        cmbAMPM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));
        pnlEvento.add(cmbAMPM);
        cmbAMPM.setBounds(100, 260, 59, 20);

        lblNombreEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblNombreEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblNombreEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblNombreEstatico.setText(" Nombre del evento:");
        lblNombreEstatico.setOpaque(true);
        pnlEvento.add(lblNombreEstatico);
        lblNombreEstatico.setBounds(220, 30, 250, 20);
        pnlEvento.add(txtNombre);
        txtNombre.setBounds(220, 50, 250, 28);

        btnAtras.setBackground(new java.awt.Color(255, 255, 255));
        btnAtras.setBorder(null);
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasMouseExited(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        pnlEvento.add(btnAtras);
        btnAtras.setBounds(10, 470, 50, 40);

        lblDuracion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDuracion.setForeground(new java.awt.Color(22, 81, 198));
        lblDuracion.setText("Duracion del evento:");
        pnlEvento.add(lblDuracion);
        lblDuracion.setBounds(220, 280, 120, 16);

        cmbDuracionEvento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1", "1.5", "2", "2.5", "3", "3.5", "4", "4.5", "5" }));
        pnlEvento.add(cmbDuracionEvento);
        cmbDuracionEvento.setBounds(270, 310, 72, 22);

        lblHorasDuracion.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblHorasDuracion.setForeground(new java.awt.Color(22, 81, 198));
        lblHorasDuracion.setText("horas:");
        pnlEvento.add(lblHorasDuracion);
        lblHorasDuracion.setBounds(220, 310, 40, 16);

        getContentPane().add(pnlEvento);
        pnlEvento.setBounds(0, 60, 500, 520);

        lblInfoEventoEstatico.setBackground(new java.awt.Color(22, 81, 198));
        lblInfoEventoEstatico.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblInfoEventoEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoEventoEstatico.setText("Datos del evento");
        lblInfoEventoEstatico.setOpaque(true);
        getContentPane().add(lblInfoEventoEstatico);
        lblInfoEventoEstatico.setBounds(0, 0, 500, 60);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        abrirMapa();
    }//GEN-LAST:event_btnMapaActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        añadirEvento2();
        this.dispose();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        abrirSeleccionColor();
    }//GEN-LAST:event_btnColorActionPerformed

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        this.dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void cmbTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbTipoActionPerformed
        actualizarPermisos();
    }//GEN-LAST:event_cmbTipoActionPerformed

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        // TODO add your handling code here:
        btnAtras.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAtrasMouseEntered

    private void btnLimpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseEntered
        // TODO add your handling code here:
        btnLimpiar.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnLimpiarMouseEntered

    private void btnAñadirMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirMouseEntered
        // TODO add your handling code here:
        btnAñadir.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAñadirMouseEntered

    private void btnMapaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMapaMouseEntered
        // TODO add your handling code here:
        btnMapa.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnMapaMouseEntered

    private void chbJuevesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbJuevesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbJuevesActionPerformed

    private void chbMiercolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_chbMiercolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_chbMiercolesActionPerformed

    private void btnAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseExited
        // TODO add your handling code here:
        btnAtras.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAtrasMouseExited

    private void btnLimpiarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseExited
        // TODO add your handling code here:
        btnLimpiar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnLimpiarMouseExited

    private void btnAñadirMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirMouseExited
        // TODO add your handling code here:
        btnAñadir.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAñadirMouseExited

    private void btnMapaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnMapaMouseExited
        // TODO add your handling code here:
        btnMapa.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnMapaMouseExited

    private void actualizarPermisos(){
      if (this.cmbTipo.getSelectedItem().equals("Unico")) {
            this.chbLunes.setSelected(false);
            this.chbMartes.setSelected(false);
            this.chbMiercoles.setSelected(false);
            this.chbJueves.setSelected(false);
            this.chbViernes.setSelected(false);
            this.chbSabado.setSelected(false);
            this.chbDomingo.setSelected(false);
            
            this.chbLunes.setEnabled(false);
            this.chbMartes.setEnabled(false);
            this.chbMiercoles.setEnabled(false);
            this.chbJueves.setEnabled(false);
            this.chbViernes.setEnabled(false);
            this.chbSabado.setEnabled(false);
            this.chbDomingo.setEnabled(false);
            
            this.dtcFecha.setEnabled(true);
        }else if (this.cmbTipo.getSelectedItem().equals("Semanal")) {
            this.chbLunes.setEnabled(true);
            this.chbMartes.setEnabled(true);
            this.chbMiercoles.setEnabled(true);
            this.chbJueves.setEnabled(true);
            this.chbViernes.setEnabled(true);
            this.chbSabado.setEnabled(true);
            this.chbDomingo.setEnabled(true);
            
            this.dtcFecha.setEnabled(false);
            this.dtcFecha.setDate(null);
        }  
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadir;
    private javax.swing.JButton btnColor;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnMapa;
    private javax.swing.JCheckBox chbDomingo;
    private javax.swing.JCheckBox chbJueves;
    private javax.swing.JCheckBox chbLunes;
    private javax.swing.JCheckBox chbMartes;
    private javax.swing.JCheckBox chbMiercoles;
    private javax.swing.JCheckBox chbSabado;
    private javax.swing.JCheckBox chbViernes;
    private javax.swing.JComboBox<String> cmbAMPM;
    private javax.swing.JComboBox<String> cmbDuracionEvento;
    private javax.swing.JComboBox<String> cmbHora;
    private javax.swing.JComboBox<String> cmbTipo;
    private com.toedter.calendar.JDateChooser dtcFecha;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblDuracion;
    private javax.swing.JLabel lblEjemploEstatico;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblHorasDuracion;
    private javax.swing.JLabel lblInfoEventoEstatico;
    private javax.swing.JLabel lblNombreEstatico;
    private javax.swing.JLabel lblTipoEstatico;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JPanel pnlEvento;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
