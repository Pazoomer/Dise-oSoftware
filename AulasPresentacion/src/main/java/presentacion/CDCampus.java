
package presentacion;

import DTOS.campus.CampusConsultableDTO;
import accesoUbicaciones.FachadaAccesoUbicaciones;
import accesoUbicaciones.IAccesoUbicaciones;
import excepciones.NegocioException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author t1pas
 */
public class CDCampus extends javax.swing.JDialog {

    IAccesoUbicaciones accesoUbicaciones=new FachadaAccesoUbicaciones();
    String operacion;
    CampusConsultableDTO campusSeleccionado;
    /**
     * Creates new form CDCampus
     * @param parent
     * @param modal
     * @param operacion
     * @param campusSeleccionado
     */
    public CDCampus(java.awt.Frame parent, boolean modal, String operacion, CampusConsultableDTO campusSeleccionado) {
        super(parent, modal);
        initComponents();
        this.operacion=operacion;
        this.campusSeleccionado=campusSeleccionado;
        colocarInformacion();
        colocarPermisos();
    }
    
    private void limpiar(){
        this.txtNombre.setText("");
    }

    private void cerrar() {
        this.dispose();
    }

    private void error(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
    
    private void colocarInformacion(){
        if ((operacion.equalsIgnoreCase("Editar") || operacion.equalsIgnoreCase("Eliminar"))) {
            this.txtNombre.setText(campusSeleccionado.getNombre());
        }
        this.lblOperacionDinamico.setText(operacion+" el campus");
    }

    private void colocarPermisos() {
        if (operacion.equalsIgnoreCase("Eliminar")) {
            this.txtNombre.setEnabled(false);
            
        } else if (operacion.equalsIgnoreCase("Editar")) {
            this.txtNombre.setEnabled(true);

        } else if (operacion.equalsIgnoreCase("Agregar")) {
            this.txtNombre.setEnabled(true);
        } else {
            error("No tiene permisos para acceder a los campus");
            cerrar();
        }
    }

    private void confirmar() {
        if (operacion.equalsIgnoreCase("Eliminar")) {
            eliminar();
        } else if (operacion.equalsIgnoreCase("Editar")) {
            editar();
        } else if (operacion.equalsIgnoreCase("Agregar")) {
            agregar();
        } else {
            error("No tiene permisos para acceder a los campus");
            cerrar();
        }
    }
    
    private void eliminar(){
        try {
            accesoUbicaciones.eliminarCampus(campusSeleccionado);
        } catch (NegocioException ex) {
            error("No se pudo eliminar el campus");
        }
    }
    
    private void editar(){
        campusSeleccionado.setNombre(this.txtNombre.getText());
        
        try {
            accesoUbicaciones.editarCampus(campusSeleccionado);
        } catch (NegocioException ex) {
            error("No se pudo editar el campus");
        }
    }
    
    private void agregar(){
        CampusConsultableDTO campus=new CampusConsultableDTO(this.txtNombre.getText());
        try {
            accesoUbicaciones.agregarCampus(campus);
        } catch (NegocioException ex) {
            error("No se pudo agregar el campus");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        lblNombreEstatico = new javax.swing.JLabel();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        lblOperacionDinamico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblNombreEstatico.setText("Nombre");

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.setText("Confirmar");
        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        lblOperacionDinamico.setText("Operacion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnConfirmar)
                .addGap(96, 96, 96))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(89, 89, 89)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addComponent(lblNombreEstatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 321, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarActionPerformed
        confirmar();
    }//GEN-LAST:event_btnConfirmarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnConfirmar;
    private javax.swing.JLabel lblNombreEstatico;
    private javax.swing.JLabel lblOperacionDinamico;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
