
package presentacion.pantallas;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import presentacion.CDUbicacion;

/**
 *
 * @author t1pas
 */
public class PrincipalUbicacion extends javax.swing.JFrame {

    JFrame pantallaAnterior;
    CampusConsultableDTO campusSeleccionado;
    UbicacionDTO ubicacionSeleccionada;
    List<UbicacionDTO> ubicaciones;
    /**
     * Creates new form PrincipalUbicacion
     * @param pantallaAnterior
     * @param campusSeleccionado
     */
    public PrincipalUbicacion(JFrame pantallaAnterior, CampusConsultableDTO campusSeleccionado) {
        initComponents();
        this.campusSeleccionado=campusSeleccionado;
        this.pantallaAnterior=pantallaAnterior;
        actualizarTabla();
    }
    
    private void cerrar(){
        pantallaAnterior.setVisible(true);
        this.dispose();
    }
    
    private void actualizarTabla() {
        consultarUbicaciones();
        actualizarModeloTabla();
    }

    private void consultarUbicaciones() {
        //CONSULTAR LOS CAMPUS EN LA BASE DE DATOS Y COLOCARLOS EN ubicaciones
    }

    private void actualizarModeloTabla() {
        //COlOCAR ubicaciones EN LA TABLA
    }
    
    private void error(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
    
    private void abrirCDUbicacion(String operacion){
        if ((operacion.equalsIgnoreCase("Editar") || operacion.equalsIgnoreCase("Eliminar")) && ubicacionSeleccionada==null) {
            error("Seleccione una ubicacione");
        }
        new CDUbicacion(this,true,operacion,ubicacionSeleccionada,campusSeleccionado).setVisible(true);
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnVolver = new javax.swing.JButton();
        lblUbicacionEstatico = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblUbicaciones = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        lblUbicacionEstatico.setText("Ubicaciones");

        tblUbicaciones.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblUbicaciones);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAgregar.setText("Agregar");
        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVolver)
                                .addGap(131, 131, 131)
                                .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(132, 132, 132)
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32)
                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(51, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 395, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 46, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        cerrar();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
        abrirCDUbicacion("Eliminar");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
       abrirCDUbicacion("editar");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
        abrirCDUbicacion("Agregar");
    }//GEN-LAST:event_btnAgregarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JTable tblUbicaciones;
    // End of variables declaration//GEN-END:variables
}
