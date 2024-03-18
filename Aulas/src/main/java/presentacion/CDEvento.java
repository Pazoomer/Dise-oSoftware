
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
        lblInfoEventoEstatico = new javax.swing.JLabel();
        lblUbicacionEstatico = new javax.swing.JLabel();
        txtUbicacion = new javax.swing.JTextField();
        btnMapa = new javax.swing.JButton();
        lblNombreEstatico = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        lblDescripcionEstatico = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        btnAñadir = new javax.swing.JButton();
        lblAñadir = new javax.swing.JLabel();
        cmbTipo = new javax.swing.JComboBox<>();
        lblTipoEstatico = new javax.swing.JLabel();
        btnColor = new javax.swing.JButton();
        lblEjemploEstatico = new javax.swing.JLabel();
        lblLimpiarEstatico = new javax.swing.JLabel();
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
        btnAtras = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblInfoEventoEstatico.setText("Datos del evento");

        lblUbicacionEstatico.setText("Ubicacion");

        btnMapa.setText("Imagen de mapa");
        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });

        lblNombreEstatico.setText("Nombre del evento");

        lblDescripcionEstatico.setText("Descripcion del evento");

        btnAñadir.setText("Imagen de añadir");
        btnAñadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirActionPerformed(evt);
            }
        });

        lblAñadir.setText("Añadir evento");

        cmbTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Semanal", "Unico" }));
        cmbTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbTipoActionPerformed(evt);
            }
        });

        lblTipoEstatico.setText("Tipo de evento");

        btnColor.setText("Asignar color");
        btnColor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnColorActionPerformed(evt);
            }
        });

        lblEjemploEstatico.setText("EJEMPLO COLOR");

        lblLimpiarEstatico.setText("Limpiar");

        limpiar.setText("imagen de limpiar");
        limpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarActionPerformed(evt);
            }
        });

        chbLunes.setText("Lunes");

        chbMartes.setText("Martes");

        chbMiercoles.setText("Miercoles");

        chbJueves.setText("Jueves");

        chbViernes.setText("Viernes");

        chbSabado.setText("Sabado");

        chbDomingo.setText("Domingo");

        lblFecha.setText("Fecha");

        lblHora.setText("Hora");

        cmbHora.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1:00", "1:30", "2:00", "2:30", "3:00", "3:30", "4:00", "4:30", "5:00", "5:30", "6:00", "6:30", "7:00", "7:30", "8:00", "8:30", "9:00", "9:30", "10:00", "10:30", "11:00", "11:30", "12:00", "12:30" }));

        cmbAMPM.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "AM", "PM" }));

        javax.swing.GroupLayout pnlEventoLayout = new javax.swing.GroupLayout(pnlEvento);
        pnlEvento.setLayout(pnlEventoLayout);
        pnlEventoLayout.setHorizontalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEventoLayout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblAñadir)
                                        .addGap(5, 5, 5))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEventoLayout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(lblEjemploEstatico)))
                                .addGap(32, 32, 32))
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnColor)
                                    .addGroup(pnlEventoLayout.createSequentialGroup()
                                        .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cmbAMPM, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlEventoLayout.createSequentialGroup()
                                .addComponent(lblLimpiarEstatico)
                                .addGap(110, 110, 110))
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 189, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(68, 68, 68))))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEventoLayout.createSequentialGroup()
                                .addComponent(btnAñadir)
                                .addGap(78, 78, 78)
                                .addComponent(limpiar))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlEventoLayout.createSequentialGroup()
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblTipoEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblInfoEventoEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dtcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblHora, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(31, 31, 31)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addComponent(chbLunes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chbJueves, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(chbDomingo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addComponent(chbMartes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chbViernes, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addComponent(chbMiercoles, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(chbSabado, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlEventoLayout.setVerticalGroup(
            pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEventoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblInfoEventoEstatico)
                .addGap(18, 18, 18)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(lblTipoEstatico)
                            .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbLunes)
                            .addComponent(chbJueves)
                            .addComponent(chbDomingo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbMartes)
                            .addComponent(chbViernes))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(chbMiercoles)
                            .addComponent(chbSabado))
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(lblUbicacionEstatico)
                                .addGap(6, 6, 6)
                                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(pnlEventoLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblFecha)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(dtcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lblHora)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblDescripcionEstatico))
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cmbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cmbAMPM, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(pnlEventoLayout.createSequentialGroup()
                        .addComponent(btnColor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblEjemploEstatico))
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAñadir)
                    .addComponent(lblLimpiarEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlEventoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAñadir, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(13, 13, 13))
        );

        btnAtras.setText("Imagen de volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnAtras)
                .addContainerGap(240, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(pnlEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 362, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(477, Short.MAX_VALUE)
                .addComponent(btnAtras)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(10, 10, 10)
                    .addComponent(pnlEvento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(70, Short.MAX_VALUE)))
        );

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
    private javax.swing.JLabel lblAñadir;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblEjemploEstatico;
    private javax.swing.JLabel lblFecha;
    private javax.swing.JLabel lblHora;
    private javax.swing.JLabel lblInfoEventoEstatico;
    private javax.swing.JLabel lblLimpiarEstatico;
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
