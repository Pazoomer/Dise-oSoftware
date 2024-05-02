
package presentacion;

import DTOS.campus.CampusConsultableDTO;
import accesoUbicaciones.FachadaAccesoUbicaciones;
import accesoUbicaciones.IAccesoUbicaciones;
import excepciones.NegocioException;
import java.awt.Image;
import javax.swing.ImageIcon;
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
        setUndecorated(true);
        this.setResizable(false);
        initComponents();
        this.setSize(500, 620);
        this.operacion=operacion;
        this.campusSeleccionado=campusSeleccionado;
        decorar();
        colocarPermisos();
    }
    
    private void decorar() {
        
        if ((operacion.equalsIgnoreCase("Editar") || operacion.equalsIgnoreCase("Eliminar"))) {
            this.txtNombre.setText(campusSeleccionado.getNombre());
        }
        this.lblOperacionDinamico.setText(operacion+" el campus");

        try {
            ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
            this.btnCancelar.setIcon(iconoReturn);
            
            ImageIcon iconoConfirmar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
            this.btnConfirmar.setIcon(iconoConfirmar);
        } catch (Exception e) {
            System.out.println("No cargaron los iconos");
        }

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
            return;
        }
        error("Campus eliminado exitosamente");
        cerrar();
    }

    private void editar(){
        campusSeleccionado.setNombre(this.txtNombre.getText());
        
        try {
            accesoUbicaciones.editarCampus(campusSeleccionado);
        } catch (NegocioException ex) {
            error("No se pudo editar el campus");
            return;
        }
        error("Campus editado exitosamente");
        cerrar();
    }
    
    private void agregar() {
        CampusConsultableDTO campus = new CampusConsultableDTO(this.txtNombre.getText());
        try {
            accesoUbicaciones.agregarCampus(campus);
        } catch (NegocioException ex) {
            error("No se pudo agregar el campus");
            return;
        }
        error("Campus agregado exitosamente");
        cerrar();
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtNombre = new javax.swing.JTextField();
        btnCancelar = new javax.swing.JButton();
        btnConfirmar = new javax.swing.JButton();
        pnlTitulo = new javax.swing.JPanel();
        lblOperacionDinamico = new javax.swing.JLabel();
        lblConfirmarEstatico = new javax.swing.JLabel();
        lblCancelarEstatico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblNombreEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarActionPerformed(evt);
            }
        });

        pnlTitulo.setBackground(new java.awt.Color(22, 81, 198));

        lblOperacionDinamico.setBackground(new java.awt.Color(22, 81, 198));
        lblOperacionDinamico.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblOperacionDinamico.setForeground(new java.awt.Color(255, 255, 255));
        lblOperacionDinamico.setText("Operacion");

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(34, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );

        lblConfirmarEstatico.setText("Confirmar");

        lblCancelarEstatico.setText("Cancelar");

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));

        lblNombreEstatico.setBackground(new java.awt.Color(22, 81, 198));
        lblNombreEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblNombreEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreEstatico.setText("                   Nombre");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblNombreEstatico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lblNombreEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlTitulo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addComponent(lblCancelarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lblConfirmarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addGap(98, 98, 98))
            .addGroup(layout.createSequentialGroup()
                .addGap(89, 89, 89)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtNombre, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(78, 78, 78)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 211, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblConfirmarEstatico)
                    .addComponent(lblCancelarEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConfirmar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 36, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCancelarEstatico;
    private javax.swing.JLabel lblConfirmarEstatico;
    private javax.swing.JLabel lblNombreEstatico;
    private javax.swing.JLabel lblOperacionDinamico;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
