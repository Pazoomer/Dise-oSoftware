
package presentacion;

import DTOS.evento.EventoConsultableDTO;
import java.awt.Color;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
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
        actualizarPermisos();
    }
    
    public CDEvento(java.awt.Frame parent,PrincipalCalendario calendario,EventoConsultableDTO eventoEditable , boolean modal) {
        super(parent, modal);
        initComponents();
        this.calendario=calendario;
        this.parent=parent;
        this.eventoEditable=eventoEditable;
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
        if (hora==null) {
            hora="7:00";
        }
        int[] horaNumerica=convertirHora(hora);
        
        if (fecha==null && tipo.equalsIgnoreCase("unico")) {
            //TODO
            //MOSTRAR MENSAJE DE ERROR DE QUE NO HAY FECHA PARA EL EVENTO        
            return;
        }else if (fecha==null && tipo.equalsIgnoreCase("semanal")) {
            fecha=new Date();
        }
        

        
        if (AMPM.equalsIgnoreCase("PM")) {
            horaNumerica[0]=horaNumerica[0]+12;
        }

        if (horaNumerica[0] == 12) {
            horaNumerica[0] = 0;
        } else if (horaNumerica[0] < 7 || horaNumerica[0] > 19) {
            //TODO
            //MOSTRAR MENSAJE DE QUE NO SE PUEDEN HACER EVENTOS FUERA DE HORARIO ESCOLAR
            return;
        }
        
        try {
            calendar = Calendar.getInstance();
            calendar.setTime(fecha);
            calendar.set(Calendar.HOUR_OF_DAY,horaNumerica[0] );
            calendar.set(Calendar.MINUTE,horaNumerica[1] );
        } catch (Exception e) {
            e.printStackTrace();
            //TODO
            //Mostrar mensaje de que la fecha no es valida
        }
        if (calendar==null) {
            System.out.println("calendario null");
            //TODO
            //Mostrar mensaje de que la fecha no es valida
        }
 
        EventoConsultableDTO evento = new EventoConsultableDTO(tipo, nombre, descripcion, color, diasSemana, ubicacion, calendar);
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
    
    /**
     * Establece vacio los campos de texto y la fecha
     */
    private void limpiar(){
       txtDescripcion.setText("");
        txtNombre.setText("");
        txtUbicacion.setText("");
        this.dtcFecha.setDate(Calendar.getInstance().getTime()); 
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
        limpiar = new javax.swing.JButton();
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
        btnMapa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-map-30 blue.png"))); // NOI18N
        btnMapa.setBorder(null);
        btnMapa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnMapaMouseEntered(evt);
            }
        });
        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });
        pnlEvento.add(btnMapa);
        btnMapa.setBounds(420, 110, 50, 30);

        lblDescripcionEstatico.setBackground(new java.awt.Color(255, 255, 255));
        lblDescripcionEstatico.setFont(new java.awt.Font("SansSerif", 1, 12)); // NOI18N
        lblDescripcionEstatico.setForeground(new java.awt.Color(22, 81, 198));
        lblDescripcionEstatico.setText(" Descripcion del evento:");
        lblDescripcionEstatico.setOpaque(true);
        pnlEvento.add(lblDescripcionEstatico);
        lblDescripcionEstatico.setBounds(220, 180, 138, 14);
        pnlEvento.add(txtDescripcion);
        txtDescripcion.setBounds(220, 200, 250, 100);

        btnAñadir.setBackground(new java.awt.Color(255, 255, 255));
        btnAñadir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"))); // NOI18N
        btnAñadir.setBorder(null);
        btnAñadir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadirMouseEntered(evt);
            }
        });
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });
        pnlEvento.add(btnAñadir);
        btnAñadir.setBounds(110, 410, 70, 50);

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
        btnColor.setBounds(30, 310, 65, 15);

        lblEjemploEstatico.setText("EJEMPLO COLOR");
        pnlEvento.add(lblEjemploEstatico);
        lblEjemploEstatico.setBounds(40, 340, 82, 14);

        limpiar.setBackground(new java.awt.Color(255, 255, 255));
        limpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-remove-48.png"))); // NOI18N
        limpiar.setBorder(null);
        limpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                limpiarMouseEntered(evt);
            }
        });
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });
        pnlEvento.add(limpiar);
        limpiar.setBounds(310, 410, 60, 50);

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
        dtcFecha.setBounds(30, 200, 160, 20);

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

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00", "5:30", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30" }));
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
        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"))); // NOI18N
        btnAtras.setBorder(null);
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        pnlEvento.add(btnAtras);
        btnAtras.setBounds(10, 470, 50, 40);

        getContentPane().add(pnlEvento);
        pnlEvento.setBounds(0, 60, 500, 520);

        lblInfoEventoEstatico.setBackground(new java.awt.Color(22, 81, 198));
        lblInfoEventoEstatico.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblInfoEventoEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblInfoEventoEstatico.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-note-50.png"))); // NOI18N
        lblInfoEventoEstatico.setText("Datos del evento");
        lblInfoEventoEstatico.setOpaque(true);
        getContentPane().add(lblInfoEventoEstatico);
        lblInfoEventoEstatico.setBounds(0, 0, 500, 60);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        abrirMapa();
    }//GEN-LAST:event_btnMapaActionPerformed

    private void btnAñadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirActionPerformed
        añadirEvento();
    }//GEN-LAST:event_btnAñadirActionPerformed

    private void btnColorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnColorActionPerformed
        abrirSeleccionColor();
    }//GEN-LAST:event_btnColorActionPerformed

    private void limpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_limpiarActionPerformed

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

    private void limpiarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_limpiarMouseEntered
        // TODO add your handling code here:
        limpiar.setBackground(Color.lightGray);
    }//GEN-LAST:event_limpiarMouseEntered

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
    private javax.swing.JButton btnMapa;
    private javax.swing.JCheckBox chbDomingo;
    private javax.swing.JCheckBox chbJueves;
    private javax.swing.JCheckBox chbLunes;
    private javax.swing.JCheckBox chbMartes;
    private javax.swing.JCheckBox chbMiercoles;
    private javax.swing.JCheckBox chbSabado;
    private javax.swing.JCheckBox chbViernes;
    private javax.swing.JComboBox<String> cmbAMPM;
    private javax.swing.JComboBox<String> cmbHora;
    private javax.swing.JComboBox<String> cmbTipo;
    private com.toedter.calendar.JDateChooser dtcFecha;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblEjemploEstatico;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblInfoEventoEstatico;
    private javax.swing.JLabel lblNombreEstatico;
    private javax.swing.JLabel lblTipoEstatico;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JButton limpiar;
    private javax.swing.JPanel pnlEvento;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables
}
