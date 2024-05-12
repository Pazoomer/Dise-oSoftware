
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import adminEventos.FachadaAdminEventos;
import adminEventos.IAdminEventos;
import java.util.ArrayList;
import java.util.List;
import excepciones.NegocioException;
import java.awt.Color;
import java.util.Calendar;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import presentacion.CDEvento;

/**
 *
 * @author luiis
 */
public class FrmAdminEventos extends javax.swing.JFrame {
    private static IAdminEventos adminEventos;
    private static List<EventoConsultableDTO> eventos;
    private static DefaultTableModel modeloTabla;
    private static EventoConsultableDTO eventoSeleccionado;
    private PrincipalInicio parent;
    public FrmAdminEventos(){
    }
    
    /**
     * Creates new form FrmAdminEventos
     * @param parent
     */
    public FrmAdminEventos(PrincipalInicio parent) {
        initComponents();
        this.parent=parent;
        adminEventos=new FachadaAdminEventos();
        iniciar();
        calendar.setEnabled(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        scrollPane = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        calendar = new com.toedter.calendar.JCalendar();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        panelFiltros = new javax.swing.JPanel();
        rdbSemanal = new javax.swing.JRadioButton();
        rdbUnico = new javax.swing.JRadioButton();
        tgbFecha = new javax.swing.JToggleButton();
        rdbTodos = new javax.swing.JRadioButton();
        jPanel3 = new javax.swing.JPanel();
        btnAgregar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnEliminar = new javax.swing.JButton();
        btnDetalle = new javax.swing.JButton();
        btnRegresar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(920, 620));
        setResizable(false);
        setSize(new java.awt.Dimension(920, 620));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scrollPane.setMaximumSize(new java.awt.Dimension(450, 400));
        scrollPane.setPreferredSize(new java.awt.Dimension(450, 400));

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollPane.setViewportView(tablaEventos);

        getContentPane().add(scrollPane, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 150, -1, -1));

        calendar.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calendarPropertyChange(evt);
            }
        });
        getContentPane().add(calendar, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 160, 420, 219));

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setText("Calendario Escolar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(71, 71, 71)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 920, -1));

        panelFiltros.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        buttonGroup1.add(rdbSemanal);
        rdbSemanal.setText("semanales");
        rdbSemanal.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbSemanalStateChanged(evt);
            }
        });
        rdbSemanal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbSemanalActionPerformed(evt);
            }
        });
        panelFiltros.add(rdbSemanal, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 0, -1, -1));

        buttonGroup1.add(rdbUnico);
        rdbUnico.setText("unicos de un dia");
        rdbUnico.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbUnicoStateChanged(evt);
            }
        });
        rdbUnico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbUnicoActionPerformed(evt);
            }
        });
        panelFiltros.add(rdbUnico, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 0, -1, -1));

        tgbFecha.setText("filtrar por fecha");
        tgbFecha.setMaximumSize(new java.awt.Dimension(120, 40));
        tgbFecha.setMinimumSize(new java.awt.Dimension(120, 40));
        tgbFecha.setPreferredSize(new java.awt.Dimension(120, 40));
        tgbFecha.setRequestFocusEnabled(false);
        tgbFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tgbFechaActionPerformed(evt);
            }
        });
        panelFiltros.add(tgbFecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, -1, -1));

        buttonGroup1.add(rdbTodos);
        rdbTodos.setText("todos");
        rdbTodos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                rdbTodosStateChanged(evt);
            }
        });
        rdbTodos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdbTodosActionPerformed(evt);
            }
        });
        panelFiltros.add(rdbTodos, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 0, -1, -1));

        getContentPane().add(panelFiltros, new org.netbeans.lib.awtextra.AbsoluteConstraints(11, 106, 880, 40));

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.setMaximumSize(new java.awt.Dimension(90, 30));
        btnEditar.setMinimumSize(new java.awt.Dimension(90, 30));
        btnEditar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnEliminar.setText("Eliminar");
        btnEliminar.setMaximumSize(new java.awt.Dimension(90, 30));
        btnEliminar.setMinimumSize(new java.awt.Dimension(90, 30));
        btnEliminar.setPreferredSize(new java.awt.Dimension(90, 30));
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnDetalle.setText("Ver detalles");
        btnDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(153, 153, 153)
                .addComponent(btnDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 420, -1));

        btnRegresar.setText("regresar");
        btnRegresar.setMaximumSize(new java.awt.Dimension(120, 45));
        btnRegresar.setMinimumSize(new java.awt.Dimension(120, 45));
        btnRegresar.setPreferredSize(new java.awt.Dimension(120, 45));
        btnRegresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegresarActionPerformed(evt);
            }
        });
        getContentPane().add(btnRegresar, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 500, -1, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegresarActionPerformed
        // TODO add your handling code here:
        this.dispose();
        this.parent.setVisible(true);
    }//GEN-LAST:event_btnRegresarActionPerformed

    private void rdbUnicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbUnicoActionPerformed
        // TODO add your handling code here:
        if(adminEventos!=null)cambiarTipoEventos();
    }//GEN-LAST:event_rdbUnicoActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        // TODO add your handling code here:
        desplegarCDEvento("agregar");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        // TODO add your handling code here:
        desplegarCDEvento("editar");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void rdbSemanalStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbSemanalStateChanged
        // TODO add your handling code here:
        //if(adminEventos!=null)cambiarTipoEventos();
    }//GEN-LAST:event_rdbSemanalStateChanged

    private void rdbUnicoStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbUnicoStateChanged
        // TODO add your handling code here:
        //if(adminEventos!=null)cambiarTipoEventos();
    }//GEN-LAST:event_rdbUnicoStateChanged

    private void btnDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDetalleActionPerformed
        // TODO add your handling code here:
        desplegarCDEvento("desplegar");
    }//GEN-LAST:event_btnDetalleActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        // TODO add your handling code here:
        eliminarEvento();
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void tgbFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tgbFechaActionPerformed
        // TODO add your handling code here:
        cambiarEstadoFiltroFecha();
    }//GEN-LAST:event_tgbFechaActionPerformed

    private void rdbTodosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbTodosActionPerformed
        // TODO add your handling code here:
        if(adminEventos!=null)cambiarTipoEventos();
    }//GEN-LAST:event_rdbTodosActionPerformed

    private void rdbTodosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_rdbTodosStateChanged
        // TODO add your handling code here:
        //if(adminEventos!=null)cambiarTipoEventos();
    }//GEN-LAST:event_rdbTodosStateChanged

    private void calendarPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calendarPropertyChange
        // TODO add your handling code here:
        if(adminEventos!=null)cambiarTipoEventos();
    }//GEN-LAST:event_calendarPropertyChange

    private void rdbSemanalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdbSemanalActionPerformed
        // TODO add your handling code here:
        if(adminEventos!=null)cambiarTipoEventos();
    }//GEN-LAST:event_rdbSemanalActionPerformed

    public void agregarEvento(EventoConsultableDTO evento){
        try {
            eventoSeleccionado=adminEventos.agregarEvento(evento);
            if(eventoSeleccionado!=null){
                JOptionPane.showMessageDialog(this, "Se agrego el evento");
                setTablaEventos2(null);
            }else 
                JOptionPane.showMessageDialog(this, "No agrego el evento");
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
    
    public void editarEvento(EventoConsultableDTO evento,List<String> camposModificados){
        try {
            eventoSeleccionado=adminEventos.editarEvento(evento,camposModificados);
            if(eventoSeleccionado!=null){
                JOptionPane.showMessageDialog(this, "Se edito el evento");
                setTablaEventos2(null);
            }else 
                JOptionPane.showMessageDialog(this, "No se edito el evento");
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }
    
    private void desplegarCDEvento(String operacion){
        CDEvento cdEvento;
        if(operacion.equals("agregar")){
            cdEvento = new CDEvento(this, true, "agregar");
            //cdEvento.setVisible(true);
        }
        else if(eventoSeleccionado!=null){
            if(operacion.equals("editar")){
                cdEvento = new CDEvento(this, eventoSeleccionado, true, "editar");
                cdEvento.desplegarEventoEditable();
            }else{
                cdEvento = new CDEvento(this, eventoSeleccionado, true, "desplegar");
                cdEvento.desplegarEvento();
            }
            //cdEvento.setVisible(true);
        }else{
            JOptionPane.showMessageDialog(this, "No se selecciono algun evento");
            return;
        }
        cdEvento.setVisible(true);
    }
    
    private void iniciar(){
        modeloTabla=new DefaultTableModel();
        //System.out.println(calendar.getCalendar().get(Calendar.MONTH)+"/"+calendar.getCalendar().get(Calendar.DAY_OF_MONTH));
        //calendar.setSundayForeground(Color.PINK);
        tgbFecha.setSelected(false);
        cambiarEstadoFiltroFecha();
        this.rdbTodos.setSelected(true);
        setTablaEventos2(null);
        tablaEventos.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int fila=tablaEventos.getSelectedRow();
                    if(fila>=0){
                        for(EventoConsultableDTO t:eventos){
                            if(eventos.indexOf(t)==fila){
                                eventoSeleccionado=t;
                                System.out.println(t.toString());
                                break;
                            }
                                
                        }
                    }
                }
            }
        });
        
    }
    
    private void setTablaEventos2(String tipoFiltro) {
        try {
            if(tgbFecha.isSelected()){
                if (tipoFiltro != null) {
                        eventos = adminEventos.obtenerEventos(tipoFiltro, calendar.getCalendar());
                } else
                    eventos = adminEventos.obtenerEventos(calendar.getCalendar());
            }else{
                if(tipoFiltro!=null)eventos=adminEventos.obtenerEventos(tipoFiltro);
                else eventos = adminEventos.obtenerEventos();
            }
        } catch (NegocioException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return;
        }
        if(eventos!=null){
            List<List<String>> datos = new ArrayList<>();
            List<String> registros;
            for (EventoConsultableDTO ev : eventos) {
                //System.out.println("evvvento: "+ev);
                registros = new ArrayList<>();
                registros.add(ev.getNombre());
                if(tipoFiltro!=null){
                    switch (tipoFiltro) {
                        case "semanal" -> registros.add(ev.getDiasSemana());
                        case "unico" -> registros.add(ev.fechaToString(ev.getFechaInicio()));
                        case "fecha" -> registros.add(ev.getTipo().toString());
                    }
                }else registros.add(ev.getTipo().toString());
                
                Calendar hora = ev.getHoraInicio();
                String horaStr = hora.get(Calendar.HOUR_OF_DAY) + ":"
                        + hora.get(Calendar.MINUTE);
                if (hora.get(Calendar.MINUTE) == 0) {
                    horaStr = horaStr + '0';
                }
                registros.add(horaStr);
                registros.add(ev.getUbicacion().getIdentificador());
                datos.add(registros);
            }
            Object[][] filas = new Object[datos.size()][4];
            int contador = 0;
            for (List<String> lista : datos) {
                filas[contador][0] = lista.get(0);
                filas[contador][1] = lista.get(1);
                filas[contador][2] = lista.get(2);
                filas[contador][3] = lista.get(3);
                contador++;
            }
            List<String> columnas = new ArrayList<>();
            columnas.add("Nombre");
            if(tipoFiltro== null || tipoFiltro.equals("fecha"))
                columnas.add("Tipo");
            else{
                if(tipoFiltro.equals("semanal"))
                    columnas.add("Dias");
                else 
                    columnas.add("Fecha inicio");
            }
            columnas.add("Hora inicio");
            columnas.add("Edificio");

            modeloTabla = new DefaultTableModel(filas, columnas.toArray());
            tablaEventos.setModel(modeloTabla);
        }
    }
   
    private void cambiarTipoEventos(){
        if(this.rdbUnico.isSelected())
            setTablaEventos2("unico");
        else if(this.rdbSemanal.isSelected())
            setTablaEventos2("semanal");
        else if(this.rdbTodos.isSelected())
            setTablaEventos2(null);
    }
    
    private void eliminarEvento() {
        String msj;
        try{
            if(eventoSeleccionado!=null){
                if(adminEventos.eliminarEvento(eventoSeleccionado)){
                    msj="El evento se elimino correctamente";
                    setTablaEventos2(null);
                }
                else msj="No se elimino el evento";
            }else msj="No hay un evento seleccionado";
        }catch(NegocioException e){
            msj=e.getMessage();
        }
        JOptionPane.showMessageDialog(this, msj);
    }
    
    private void cambiarEstadoFiltroFecha(){
        boolean flag=tgbFecha.isSelected();
        calendar.setEnabled(flag);
        //tgbFecha.setSelected(flag);
        if(flag){
            tgbFecha.setBackground(Color.GREEN);
        }
        else{
            tgbFecha.setBackground(Color.DARK_GRAY);
        }
    }

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnDetalle;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnRegresar;
    private javax.swing.ButtonGroup buttonGroup1;
    private com.toedter.calendar.JCalendar calendar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel panelFiltros;
    private javax.swing.JRadioButton rdbSemanal;
    private javax.swing.JRadioButton rdbTodos;
    private javax.swing.JRadioButton rdbUnico;
    private javax.swing.JScrollPane scrollPane;
    private javax.swing.JTable tablaEventos;
    private javax.swing.JToggleButton tgbFecha;
    // End of variables declaration//GEN-END:variables

    
}
