
package presentacion.pantallas;

import DTOS.campus.CampusConsultableDTO;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import presentacion.CDCampus;

/**
 *
 * @author t1pas
 */
public class PrincipalCampus extends javax.swing.JFrame {

    JFrame pantallaAnterior;
    CampusConsultableDTO campusSeleccionado;
    List<CampusConsultableDTO> campuses;
    /**
     * Creates new form PrincipalCampus
     * @param pantallaAnterior
     */
    public PrincipalCampus(JFrame pantallaAnterior) {
        initComponents();
        this.pantallaAnterior = pantallaAnterior;
        actualizarTabla();
    }

    private void actualizarTabla() {
        consultarCampuses();
        actualizarModeloTabla();
    }

    private void consultarCampuses() {
        //CONSULTAR LOS CAMPUS EN LA BASE DE DATOS Y COLOCARLOS EN campuses
    }

    private void actualizarModeloTabla() {
        //COlOCAR campuses EN LA TABLA
    }
    
    private void cerrar(){
        this.pantallaAnterior.setVisible(true);
        this.dispose();
    }
    
    private void error(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
    
    private void abrirCDCampus(String operacion){
        if ((operacion.equalsIgnoreCase("Editar") || operacion.equalsIgnoreCase("Eliminar")) && campusSeleccionado==null) {
            error("Seleccione un campus");
        }
        new CDCampus(this,true,operacion,campusSeleccionado).setVisible(true);
    }
    
    private void consultarUbicaciones() {
        if (campusSeleccionado == null) {
            error("Seleccione un campus");
            return;
        } else if (campusSeleccionado.getUbicaciones() == null) {
            error("El campus no tiene ubicaciones");
            return;
        } else if (campusSeleccionado.getUbicaciones().isEmpty()) {
            error("El campus no tiene ubicaciones");
            return;
        }
        new PrincipalUbicacion(this, campusSeleccionado).setVisible(true);
        this.setVisible(false);
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblEstaticoCampus = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblCampuses = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnConsultarUbicaciones = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblEstaticoCampus.setText("Campus");

        tblCampuses.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tblCampuses);

        btnEliminar.setText("Eliminar");
        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
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

        btnConsultarUbicaciones.setText("Consultar Ubicaciones");
        btnConsultarUbicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarUbicacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnVolver)
                        .addGap(167, 167, 167)
                        .addComponent(lblEstaticoCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(41, 41, 41)
                            .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(34, 34, 34)
                            .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(39, 39, 39)
                            .addComponent(btnConsultarUbicaciones))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(61, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEstaticoCampus, javax.swing.GroupLayout.DEFAULT_SIZE, 58, Short.MAX_VALUE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 376, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnConsultarUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        cerrar();
    }//GEN-LAST:event_btnVolverActionPerformed

    private void btnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarActionPerformed
       abrirCDCampus("Eliminar");
    }//GEN-LAST:event_btnEliminarActionPerformed

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        abrirCDCampus("Editar");
    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarActionPerformed
       abrirCDCampus("Agregar");
    }//GEN-LAST:event_btnAgregarActionPerformed

    private void btnConsultarUbicacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConsultarUbicacionesActionPerformed
       consultarUbicaciones();
    }//GEN-LAST:event_btnConsultarUbicacionesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregar;
    private javax.swing.JButton btnConsultarUbicaciones;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnEliminar;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblEstaticoCampus;
    private javax.swing.JTable tblCampuses;
    // End of variables declaration//GEN-END:variables
}
