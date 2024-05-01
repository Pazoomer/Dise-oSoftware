
package presentacion;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import accesoUbicaciones.FachadaAccesoUbicaciones;
import accesoUbicaciones.IAccesoUbicaciones;
import excepciones.NegocioException;
import javax.swing.JOptionPane;

/**
 *
 * @author t1pas
 */
public class CDUbicacion extends javax.swing.JDialog {

    IAccesoUbicaciones accesoUbicaciones=new FachadaAccesoUbicaciones();
    String operacion;
    UbicacionDTO ubicacionSeleccionada;
    CampusConsultableDTO campusSeleccionado;
    
    /**
     * Creates new form CDUbicacion
     * @param parent
     * @param modal
     * @param operacion
     * @param ubicacionSeleccionada
     * @param campusSeleccionado
     */
    public CDUbicacion(java.awt.Frame parent, boolean modal, String operacion, UbicacionDTO ubicacionSeleccionada, CampusConsultableDTO campusSeleccionado) {
        super(parent, modal);
        initComponents();
        this.ubicacionSeleccionada = ubicacionSeleccionada;
        this.campusSeleccionado = campusSeleccionado;
        this.operacion = operacion;
        colocarInformacion();
        colocarPermisos();
    }

    private void limpiar(){
        this.txtDescripcionDinamico.setText("");
        this.txtIdentificadorDinamico.setText("");
    }

    private void cerrar() {
        this.dispose();
    }

    private void error(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
    
    private void colocarInformacion(){
        if ((operacion.equalsIgnoreCase("Editar") || operacion.equalsIgnoreCase("Eliminar"))) {
            this.txtDescripcionDinamico.setText(ubicacionSeleccionada.getDescripcion());
            this.txtIdentificadorDinamico.setText(ubicacionSeleccionada.getIdentificador());
        }
        this.lblOperacionDinamico.setText(operacion+" la ubicacion");
    }

    private void colocarPermisos() {
        if (operacion.equalsIgnoreCase("Eliminar")) {
            this.txtDescripcionDinamico.setEnabled(false);
            this.txtIdentificadorDinamico.setEnabled(false);

        } else if (operacion.equalsIgnoreCase("Editar")) {
            this.txtDescripcionDinamico.setEnabled(true);
            this.txtIdentificadorDinamico.setEnabled(true);

        } else if (operacion.equalsIgnoreCase("Agregar")) {
            this.txtDescripcionDinamico.setEnabled(true);
            this.txtIdentificadorDinamico.setEnabled(true);
        } else {
            error("No tiene permisos para acceder a las ubicaciones");
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
            error("No tiene permisos para acceder a las ubicaciones");
            cerrar();
        }
    }
    
    private void eliminar(){
        try {
            accesoUbicaciones.eliminarUbicacion(ubicacionSeleccionada);
        } catch (NegocioException ex) {
            error("No se pudo eliminar la ubicacion");
        }
    }
    
    private void editar(){
        ubicacionSeleccionada.setDescripcion(this.txtDescripcionDinamico.getText());
        ubicacionSeleccionada.setIdentificador(this.txtIdentificadorDinamico.getText());
        
        try {
            accesoUbicaciones.editarUbicacion(ubicacionSeleccionada);
        } catch (NegocioException ex) {
            error("No se pudo editar la ubicacion");
        }
    }
    
    private void agregar(){
        UbicacionDTO ubicacion=new UbicacionDTO();
        ubicacion.setIdentificador(this.txtIdentificadorDinamico.getText());
        ubicacion.setDescripcion(this.txtDescripcionDinamico.getText());
        ubicacion.setCampus(campusSeleccionado);
        try {
            accesoUbicaciones.agregarUbicacion(ubicacion);
        } catch (NegocioException ex) {
            error("No se pudo agregar la ubicacion");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        lblOperacionDinamico = new javax.swing.JLabel();
        lblIdentificadorEstatico = new javax.swing.JLabel();
        txtIdentificadorDinamico = new javax.swing.JTextField();
        lblDescripcionEstatico = new javax.swing.JLabel();
        txtDescripcionDinamico = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.setText("Aceptar");
        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        lblOperacionDinamico.setText("Operacion");

        lblIdentificadorEstatico.setText("Identificador");

        lblDescripcionEstatico.setText("Descripcion");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnAceptar)
                        .addGap(90, 90, 90))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lblIdentificadorEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIdentificadorDinamico)
                            .addComponent(txtDescripcionDinamico, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 341, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(140, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(lblIdentificadorEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdentificadorDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDescripcionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 222, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(32, 32, 32))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        confirmar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblIdentificadorEstatico;
    private javax.swing.JLabel lblOperacionDinamico;
    private javax.swing.JTextField txtDescripcionDinamico;
    private javax.swing.JTextField txtIdentificadorDinamico;
    // End of variables declaration//GEN-END:variables
}
