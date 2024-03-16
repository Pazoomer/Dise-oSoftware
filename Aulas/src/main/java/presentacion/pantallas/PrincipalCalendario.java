
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import presentacion.CDEvento;

import presentacion.CuadroDialogoCalendario;
import presentacion.CuadroDialogoColor;

/**
 *
 * @author t1pas
 */
public class PrincipalCalendario extends javax.swing.JFrame {

    /**
     * Es el maestro al que le pertenece el calendario
     */
    private MaestroEditableDTO maestro;
    PrincipalMaestro prinMaestro;
    List<EventoConsultableDTO> calendarioMes;
    List<EventoConsultableDTO> eventosDia;
    private CDEvento cdEvento;
    
    /**
     * Creates new form PrincipalCalendario
     * @param prinMaestro
     * @param maestro
     */
    public PrincipalCalendario(PrincipalMaestro prinMaestro, MaestroEditableDTO maestro) {
        initComponents();
        this.maestro=maestro;
        this.prinMaestro=prinMaestro;
        cargarEventoInicio();
        cargarCalendario();
        this.setVisible(true);
    }
    
    
    //establece la fecha del dateChooser a la fecha actual
    private void cargarEventoInicio(){
        Calendar cal=Calendar.getInstance();
        Date date=cal.getTime();
        dateChooser.setDate(date);
        //carga la lista de eventos del dia actual
        cargarListaEventos();
    }
    //carga la lista de eventos de cada dia
    private void cargarListaEventos() {
        DefaultListModel<String> listModel = new DefaultListModel<>();
        
        eventosDia=new ArrayList<>();
        Calendar fecha = dateChooser.getCalendar();
        int dia = fecha.get(Calendar.DAY_OF_YEAR);
        
        for(EventoConsultableDTO evento:maestro.getCalendario()){
            if (evento.getTipo().equalsIgnoreCase("semanal")) {
                if (evento.getFechaSemanal().get(Calendar.DAY_OF_YEAR) == dia) {
                    listModel.addElement(evento.getNombre());
                    this.eventosDia.add(evento);
                }

            } else if (evento.getTipo().equalsIgnoreCase("unico")) {
                if (evento.getFechaUnica().get(Calendar.DAY_OF_YEAR) == dia) {
                    listModel.addElement(evento.getNombre());
                    eventosDia.add(evento);
                }
            }
        }
        this.listaEventos.setModel(listModel);
    }
    //inicializa el calendario con los eventos del mes seleccionado
    private void cargarCalendario(){
       Calendar fecha=dateChooser.getCalendar();
       int mes=fecha.get(Calendar.MONTH);
       calendarioMes=new ArrayList<>();
        
        for (EventoConsultableDTO evento : maestro.getCalendario()) {

            if (evento.getTipo().equalsIgnoreCase("semanal")) {
                if (evento.getFechaSemanal().get(Calendar.MONTH) == mes) {
                    calendarioMes.add(evento);
                }

            } else if (evento.getTipo().equalsIgnoreCase("unico")) {
                if (evento.getFechaUnica().get(Calendar.MONTH) == mes) {
                    calendarioMes.add(evento);
                }
            }
            
        }
    }
    
        //TODO
    //Toma el calendario de eventos del maestro y los acomoda en el calendario
    private void actualizarCalendario() {

        //TODO
        //Actualizar la vista del calendario
    }

    //TODO
    //Al hacer clic en guardar calendario debe actualizar el calendario del maestro y guardar al maestro en la base de datos
    private void guardarCalendario() {
        this.prinMaestro.setVisible(true);
        this.dispose();
    }

    /**
     * Cierra este frame y abre el frame PrincipalMaestro
     */
    private void cerrar() {
        this.prinMaestro.setVisible(true);
        this.dispose();
    }

    public void añadirEvento(EventoConsultableDTO evento) {
        maestro.getCalendario().add(evento);

        cargarCalendario();
    }

    private void añadirEvento() {
        cdEvento = new CDEvento(this,this, true);
        cdEvento.setVisible(true);
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnGuardar = new javax.swing.JButton();
        lblGuardar = new javax.swing.JLabel();
        btnAtras = new javax.swing.JButton();
        pnlCalendario = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaEventos = new javax.swing.JList<>();
        dateChooser = new com.toedter.calendar.JDateChooser();
        btnAñadirEvento = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        btnGuardar.setText("Imagen de guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        lblGuardar.setText("Guardar");

        btnAtras.setText("Imagen de volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        pnlCalendario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        listaEventos.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        listaEventos.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                listaEventosValueChanged(evt);
            }
        });
        jScrollPane2.setViewportView(listaEventos);

        javax.swing.GroupLayout pnlCalendarioLayout = new javax.swing.GroupLayout(pnlCalendario);
        pnlCalendario.setLayout(pnlCalendarioLayout);
        pnlCalendarioLayout.setHorizontalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 344, Short.MAX_VALUE)
        );
        pnlCalendarioLayout.setVerticalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 340, Short.MAX_VALUE)
        );

        dateChooser.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                dateChooserPropertyChange(evt);
            }
        });

        btnAñadirEvento.setText("Añadir evento");
        btnAñadirEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirEventoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAtras)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(btnGuardar)
                                .addGap(170, 170, 170))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(lblGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(198, 198, 198))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(65, 65, 65)
                        .addComponent(btnAñadirEvento)
                        .addGap(104, 104, 104)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 174, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(dateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(156, 156, 156)
                        .addComponent(btnAñadirEvento)))
                .addGap(46, 46, 46)
                .addComponent(lblGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCalendario();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        dispose();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void dateChooserPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_dateChooserPropertyChange
        // TODO add your handling code here:
        if(dateChooser.getCalendar()!=null){
            cargarListaEventos();
        }
    }//GEN-LAST:event_dateChooserPropertyChange

    private void listaEventosValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_listaEventosValueChanged
        // TODO add your handling code here:
        /*
        if(!eventosDia.isEmpty()){
            for (EventoConsultableDTO evento : eventosDia) {
                if(evento.getNombre().equals(listaEventos.getSelectedValue())){
                    txtNombre.setText(evento.getNombre());
                    txtDescripcion.setText(evento.getDescripcion());
                    txtUbicacion.setText(evento.getUbicacion());
                    if (evento.getTipo().equalsIgnoreCase("semanal")) {
                        spdFecha.setDate(evento.getFechaSemanal().getTime());
                        cmbTipo.setSelectedIndex(1);
                    } else if (evento.getTipo().equalsIgnoreCase("unico")) {
                        spdFecha.setDate(evento.getFechaUnica().getTime());
                        cmbTipo.setSelectedIndex(0);
                    }
                    break;
                }
            }
        }*/
    }//GEN-LAST:event_listaEventosValueChanged

    private void btnAñadirEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirEventoActionPerformed
        añadirEvento();
    }//GEN-LAST:event_btnAñadirEventoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cerrar();
    }//GEN-LAST:event_formWindowClosed

    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PrincipalCalendario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //TODO
                //Este maestro es solo una fachada
                List<EventoConsultableDTO> calendario = new ArrayList<>();

                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 10);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
                calendar.set(Calendar.DAY_OF_WEEK, Calendar.TUESDAY);

                String rutaRealtiva = "fotoMaestro.png";

                ImageIcon icon = new ImageIcon(rutaRealtiva);
                
                ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(120, 100, Image.SCALE_SMOOTH));

                EventoConsultableDTO evento = new EventoConsultableDTO("semanal", "Bases de datos", "...", Color.BLUE, null, calendar);

                calendario.add(evento);

                MaestroEditableDTO maestro = new MaestroEditableDTO(1L, "Gibran Duran", "AV0900", "Doy asesorias de 9 a 11 de bases de datos los sabados y domingos", scaledIcon, calendario);

                //new PrincipalCalendario(maestro).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadirEvento;
    private javax.swing.JButton btnGuardar;
    private com.toedter.calendar.JDateChooser dateChooser;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblGuardar;
    private javax.swing.JList<String> listaEventos;
    private javax.swing.JPanel pnlCalendario;
    // End of variables declaration//GEN-END:variables
}
