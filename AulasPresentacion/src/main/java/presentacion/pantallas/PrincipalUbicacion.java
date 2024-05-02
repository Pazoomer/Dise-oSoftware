
package presentacion.pantallas;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import accesoUbicaciones.FachadaAccesoUbicaciones;
import accesoUbicaciones.IAccesoUbicaciones;
import excepciones.NegocioException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
    IAccesoUbicaciones accesoUbicaciones=new FachadaAccesoUbicaciones();
    /**
     * Creates new form PrincipalUbicacion
     * @param pantallaAnterior
     * @param campusSeleccionado
     */
    public PrincipalUbicacion(JFrame pantallaAnterior, CampusConsultableDTO campusSeleccionado) {
        setUndecorated(true);
        this.setResizable(false);
        initComponents();
        this.setSize(800, 600);
        this.campusSeleccionado=campusSeleccionado;
        this.pantallaAnterior=pantallaAnterior;
        actualizarTabla();
        decorar();
    }
    
    private void decorar() {
        this.lblUbicacionEstatico.setText("Ubicaciones de " + campusSeleccionado.getNombre());
        try {
            ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
            this.btnVolver.setIcon(iconoReturn);
            ImageIcon iconoAgregar = new ImageIcon(getClass().getResource("/imagenes/agregar.png"));
            Image imagenRedimensionadaAgregar = iconoAgregar.getImage().getScaledInstance(btnAgregar.getWidth(), btnAgregar.getHeight(), Image.SCALE_SMOOTH);
            this.btnAgregar.setIcon(new ImageIcon(imagenRedimensionadaAgregar));

            ImageIcon iconoEditar = new ImageIcon(getClass().getResource("/imagenes/editar.png"));
            Image imagenRedimensionadaEditar = iconoEditar.getImage().getScaledInstance(btnEditar.getWidth(), btnEditar.getHeight(), Image.SCALE_SMOOTH);
            this.btnEditar.setIcon(new ImageIcon(imagenRedimensionadaEditar));

            ImageIcon iconoEliminar = new ImageIcon(getClass().getResource("/imagenes/eliminar.png"));
            Image imagenRedimensionadaEliminar = iconoEliminar.getImage().getScaledInstance(btnEliminar.getWidth(), btnEliminar.getHeight(), Image.SCALE_SMOOTH);
            this.btnEliminar.setIcon(new ImageIcon(imagenRedimensionadaEliminar));
        } catch (Exception e) {
            System.out.println("No cargaron los iconos");
        }

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
         try {
            ubicaciones=accesoUbicaciones.recuperarEdificiosPorCampus(campusSeleccionado);
        } catch (NegocioException ex) {
            error("No se pudieron consultar las ubicaciones");
        }
    }

    private void actualizarModeloTabla() {
        // Crear un modelo de tabla sin encabezados
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };

        // Agregar las columnas
        modeloTabla.addColumn("Identificador");
        modeloTabla.addColumn("Descripcion");

        // Agregar los datos de las personas
        for (UbicacionDTO ubicacion : ubicaciones) {

            Object[] fila = {ubicacion.getIdentificador(), ubicacion.getDescripcion()};
            modeloTabla.addRow(fila);
        }
        // Establecer el modelo de la tabla
        this.tblUbicaciones.setModel(modeloTabla);
        
        //Al hacer clic en un campus se coloca esa ubicacion como ubicacionSeleccionada
        this.tblUbicaciones.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblUbicaciones.getSelectedRow();
                
                if (filaSeleccionada != -1) {
                    ubicacionSeleccionada=ubicaciones.get(filaSeleccionada);
                }
            }
        });
    }
    
    private void error(String error) {
        JOptionPane.showMessageDialog(this, error);
    }
    
    private void abrirCDUbicacion(String operacion){
        if ((operacion.equalsIgnoreCase("Editar") || operacion.equalsIgnoreCase("Eliminar")) && ubicacionSeleccionada==null) {
            error("Seleccione una ubicacion");
            return;
        }
        new CDUbicacion(this,true,operacion,ubicacionSeleccionada,campusSeleccionado).setVisible(true);
        actualizarTabla();
    }
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblUbicaciones = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblUbicacionEstatico = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();
        lblAgregarEstatico = new javax.swing.JLabel();
        lblEditarrEstatico = new javax.swing.JLabel();
        lblEliminarEstatico = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblUbicaciones.setBackground(new java.awt.Color(22, 81, 198));
        tblUbicaciones.setForeground(new java.awt.Color(255, 255, 255));
        tblUbicaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Identificador", "Descripcion"
            }
        ));
        tblUbicaciones.setSelectionBackground(new java.awt.Color(22, 81, 198));
        tblUbicaciones.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblUbicaciones);

        btnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarActionPerformed(evt);
            }
        });

        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));

        lblUbicacionEstatico.setFont(new java.awt.Font("Segoe UI Black", 3, 18)); // NOI18N
        lblUbicacionEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblUbicacionEstatico.setText("Ubicaciones");

        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(btnVolver)
                .addGap(29, 29, 29)
                .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUbicacionEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(20, 20, 20))
        );

        lblAgregarEstatico.setText("Agregar");

        lblEditarrEstatico.setText("Editar");

        lblEliminarEstatico.setText("Eliminar");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(39, 39, 39)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 708, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(174, 174, 174)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblEliminarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(lblEditarrEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(102, 102, 102)
                                        .addComponent(lblAgregarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(67, 67, 67)
                                        .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAgregarEstatico)
                    .addComponent(lblEditarrEstatico)
                    .addComponent(lblEliminarEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 4, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblAgregarEstatico;
    private javax.swing.JLabel lblEditarrEstatico;
    private javax.swing.JLabel lblEliminarEstatico;
    private javax.swing.JLabel lblUbicacionEstatico;
    private javax.swing.JTable tblUbicaciones;
    // End of variables declaration//GEN-END:variables
}
