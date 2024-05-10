
package presentacion;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import accesoUbicaciones.FachadaAccesoUbicaciones;
import accesoUbicaciones.IAccesoUbicaciones;
import excepciones.NegocioException;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import presentacion.pantallas.PrincipalUbicacion;

/**
 *
 * @author t1pas
 */
public class CDUbicacion extends javax.swing.JDialog {

    private static IAccesoUbicaciones accesoUbicaciones;
    private final String operacion;
    private final UbicacionDTO ubicacionSeleccionada;
    private final CampusConsultableDTO campusSeleccionado;
    private final java.awt.Frame parent;
    private final PrincipalUbicacion pantallaAnterior;
    boolean modal;
    
    /**
     * Creates new form CDUbicacion
     * @param pantallaAnterior
     * @param parent
     * @param modal
     * @param operacion
     * @param ubicacionSeleccionada
     * @param campusSeleccionado
     */
    public CDUbicacion(PrincipalUbicacion pantallaAnterior,java.awt.Frame parent, boolean modal, String operacion, UbicacionDTO ubicacionSeleccionada, CampusConsultableDTO campusSeleccionado) {
        super(parent, modal);
        setUndecorated(true);
        this.setResizable(false);
        accesoUbicaciones=new FachadaAccesoUbicaciones();
        initComponents();
        this.setSize(500, 620);
        if (ubicacionSeleccionada != null) {
            this.ubicacionSeleccionada = ubicacionSeleccionada;
        } else {
            this.ubicacionSeleccionada=new UbicacionDTO();
        }
        
        this.campusSeleccionado = campusSeleccionado;
        this.operacion = operacion;
        this.parent=parent;
        this.modal=modal;
        this.pantallaAnterior=pantallaAnterior;
        decorar();
        colocarPermisos();

    }
    
    private void actualizarTabla(){
        pantallaAnterior.actualizarTabla();
    }

    private void decorar() {
        if ((operacion.equalsIgnoreCase("Editar") || operacion.equalsIgnoreCase("Eliminar"))) {
            if (ubicacionSeleccionada.getDescripcion() != null) {
                this.txtDescripcionDinamico.setText(ubicacionSeleccionada.getDescripcion());
            }
            if (ubicacionSeleccionada.getIdentificador() != null) {
                this.txtIdentificadorDinamico.setText(ubicacionSeleccionada.getIdentificador());
            }
        }

        this.lblOperacionDinamico.setText(operacion + " la ubicacion");

        try {
            ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
            this.btnCancelar.setIcon(iconoReturn);

            ImageIcon iconoAceptar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
            this.btnAceptar.setIcon(iconoAceptar);
            
            ImageIcon iconoMapa = new ImageIcon(getClass().getResource("/imagenes/marcador.jpg"));
            Image imagenRedimensionadaMapa = iconoMapa.getImage().getScaledInstance(btnMapa.getWidth(), btnMapa.getHeight(), Image.SCALE_SMOOTH);
            this.btnMapa.setIcon(new ImageIcon(imagenRedimensionadaMapa));
        } catch (Exception e) {
            error("No cargaron los iconos");
        }

    }

    private void limpiar(){
        this.txtDescripcionDinamico.setText("");
        this.txtIdentificadorDinamico.setText("");
    }

    private void cerrar() {
        this.dispose();
        this.pantallaAnterior.setVisible(true);
    }

    private void error(String error) {
        JOptionPane.showMessageDialog(this, error);
    }

    private void colocarPermisos() {
        if (operacion.equalsIgnoreCase("Eliminar")) {
            this.txtDescripcionDinamico.setEnabled(false);
            this.txtIdentificadorDinamico.setEnabled(false);
            this.btnMapa.setEnabled(false);

        } else if (operacion.equalsIgnoreCase("Editar")) {
            this.txtDescripcionDinamico.setEnabled(true);
            this.txtIdentificadorDinamico.setEnabled(true);
            this.btnMapa.setEnabled(true);

        } else if (operacion.equalsIgnoreCase("Agregar")) {
            this.txtDescripcionDinamico.setEnabled(true);
            this.txtIdentificadorDinamico.setEnabled(true);
            this.btnMapa.setEnabled(true);
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
    
    public void colocarCoordenadas(Double x, Double y){
        this.ubicacionSeleccionada.setPosicionX(x);
        this.ubicacionSeleccionada.setPosicionY(y);
    }
    
    private void eliminar(){
        try {
            accesoUbicaciones.eliminarUbicacion(ubicacionSeleccionada);
        } catch (NegocioException ex) {
            error("No se pudo eliminar la ubicacion");
            return;
        }
        error("Ubicacion eliminada con exito");
        actualizarTabla();
        cerrar();
    }
    
    private void editar(){
        
        ubicacionSeleccionada.setDescripcion(this.txtDescripcionDinamico.getText());
        ubicacionSeleccionada.setIdentificador(this.txtIdentificadorDinamico.getText());

        try {
            accesoUbicaciones.editarUbicacion(ubicacionSeleccionada);
        } catch (NegocioException ex) {
            error("No se pudo editar la ubicacion");
            return;
        }
        error("Ubicacion editada con exito");
        actualizarTabla();
        cerrar();
    }

    private void agregar() {
        UbicacionDTO ubicacion = new UbicacionDTO();
        ubicacion.setIdentificador(this.txtIdentificadorDinamico.getText());
        ubicacion.setDescripcion(this.txtDescripcionDinamico.getText());
        ubicacion.setCampus(campusSeleccionado);
        if (ubicacionSeleccionada != null) {

            if (ubicacionSeleccionada.getPosicionX() != null && ubicacionSeleccionada.getPosicionY() != null) {
                ubicacion.setPosicionX(ubicacionSeleccionada.getPosicionX());
                ubicacion.setPosicionY(ubicacionSeleccionada.getPosicionY());
            }
        }

        try {
            accesoUbicaciones.agregarUbicacion(ubicacion);
        } catch (NegocioException ex) {
            error("No se pudo agregar la ubicacion");
            return;
        }
        error("Ubicacion agregada con exito");
        actualizarTabla();
        cerrar();
    }

    private void abrirCDMapa() {
        this.setVisible(false);
        new CDMapa(this, parent, modal, campusSeleccionado, ubicacionSeleccionada).setVisible(true);
    }

    

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnCancelar = new javax.swing.JButton();
        btnAceptar = new javax.swing.JButton();
        txtIdentificadorDinamico = new javax.swing.JTextField();
        txtDescripcionDinamico = new javax.swing.JTextField();
        btnMapa = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblOperacionDinamico = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lblDescripcionEstatico = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        lblIdentificadorEstatico = new javax.swing.JLabel();
        lblCancelarEstatico = new javax.swing.JLabel();
        lblAceptarEstatico = new javax.swing.JLabel();
        lblAñadirIconoMapaEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        btnAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAceptarActionPerformed(evt);
            }
        });

        btnMapa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMapaActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));

        lblOperacionDinamico.setFont(new java.awt.Font("Segoe UI Black", 1, 18)); // NOI18N
        lblOperacionDinamico.setForeground(new java.awt.Color(255, 255, 255));
        lblOperacionDinamico.setText("Operacion");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(lblOperacionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        jPanel2.setBackground(new java.awt.Color(22, 81, 198));
        jPanel2.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N

        lblDescripcionEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescripcionEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblDescripcionEstatico.setText("Descripcion");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(lblDescripcionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel3.setBackground(new java.awt.Color(22, 81, 198));

        lblIdentificadorEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblIdentificadorEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblIdentificadorEstatico.setText("Identificador");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(72, 72, 72)
                .addComponent(lblIdentificadorEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblIdentificadorEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lblCancelarEstatico.setText("Cancelar");

        lblAceptarEstatico.setText("Aceptar");

        lblAñadirIconoMapaEstatico.setText("Añadir icono en mapa");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                        .addComponent(btnAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(90, 90, 90))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtIdentificadorDinamico)
                            .addComponent(txtDescripcionDinamico, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(lblCancelarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblAceptarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblAñadirIconoMapaEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtIdentificadorDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDescripcionDinamico, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(lblAñadirIconoMapaEstatico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnMapa, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblAceptarEstatico, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblCancelarEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, 72, Short.MAX_VALUE)
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        limpiar();
        cerrar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAceptarActionPerformed
        confirmar();
    }//GEN-LAST:event_btnAceptarActionPerformed

    private void btnMapaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMapaActionPerformed
        abrirCDMapa();
    }//GEN-LAST:event_btnMapaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAceptar;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnMapa;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lblAceptarEstatico;
    private javax.swing.JLabel lblAñadirIconoMapaEstatico;
    private javax.swing.JLabel lblCancelarEstatico;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblIdentificadorEstatico;
    private javax.swing.JLabel lblOperacionDinamico;
    private javax.swing.JTextField txtDescripcionDinamico;
    private javax.swing.JTextField txtIdentificadorDinamico;
    // End of variables declaration//GEN-END:variables
}
