
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import presentacion.CDEvento;


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
        scpCalendario = new javax.swing.JScrollPane();
        btnAñadirEvento = new javax.swing.JButton();
        btnEditarEvento = new javax.swing.JButton();
        calEsquinaSuperior = new com.toedter.calendar.JCalendar();

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

        lblGuardar.setText("Guardar calendario");

        btnAtras.setText("Imagen de volver");
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        pnlCalendario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout pnlCalendarioLayout = new javax.swing.GroupLayout(pnlCalendario);
        pnlCalendario.setLayout(pnlCalendarioLayout);
        pnlCalendarioLayout.setHorizontalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCalendarioLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scpCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 410, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        pnlCalendarioLayout.setVerticalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scpCalendario, javax.swing.GroupLayout.DEFAULT_SIZE, 471, Short.MAX_VALUE)
        );

        btnAñadirEvento.setText("Añadir evento");
        btnAñadirEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirEventoActionPerformed(evt);
            }
        });

        btnEditarEvento.setText("Editar Evento");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(btnAñadirEvento)
                        .addGap(33, 33, 33)
                        .addComponent(btnEditarEvento))
                    .addComponent(calEsquinaSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 314, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(84, 84, 84)
                        .addComponent(btnGuardar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(lblGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnAtras)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(calEsquinaSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnAñadirEvento)
                            .addComponent(btnEditarEvento))
                        .addGap(24, 24, 24)
                        .addComponent(lblGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 18, Short.MAX_VALUE))
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

    private void btnAñadirEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAñadirEventoActionPerformed
        añadirEvento();
    }//GEN-LAST:event_btnAñadirEventoActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        cerrar();
    }//GEN-LAST:event_formWindowClosed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadirEvento;
    private javax.swing.JButton btnEditarEvento;
    private javax.swing.JButton btnGuardar;
    private com.toedter.calendar.JCalendar calEsquinaSuperior;
    private javax.swing.JLabel lblGuardar;
    private javax.swing.JPanel pnlCalendario;
    private javax.swing.JScrollPane scpCalendario;
    // End of variables declaration//GEN-END:variables
}
