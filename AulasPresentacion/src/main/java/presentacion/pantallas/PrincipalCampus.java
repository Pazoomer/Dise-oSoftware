
package presentacion.pantallas;

import DTOS.campus.CampusConsultableDTO;
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
import presentacion.CDCampus;

/**
 *
 * @author t1pas
 */
public class PrincipalCampus extends javax.swing.JFrame {

    JFrame pantallaAnterior;
    CampusConsultableDTO campusSeleccionado;
    List<CampusConsultableDTO> campuses;
    IAccesoUbicaciones accesoUbicaciones=new FachadaAccesoUbicaciones();
    /**
     * Creates new form PrincipalCampus
     * @param pantallaAnterior
     */
    public PrincipalCampus(JFrame pantallaAnterior) {
        setUndecorated(true);
        this.setResizable(false);
        initComponents();
        this.setSize(800, 600);
        this.pantallaAnterior = pantallaAnterior;
        actualizarTabla();
        decorar();
    }
    
    private void decorar(){
        // Carga el icono de retorno en el botón btnAtras
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
            
            ImageIcon iconoUbicaciones = new ImageIcon(getClass().getResource("/imagenes/ubicaciones.png"));
            Image imagenRedimensionadaUbicaciones = iconoUbicaciones.getImage().getScaledInstance(btnConsultarUbicaciones.getWidth(), btnConsultarUbicaciones.getHeight(), Image.SCALE_SMOOTH);
            this.btnConsultarUbicaciones.setIcon(new ImageIcon(imagenRedimensionadaUbicaciones));
        } catch (Exception e) {
            System.out.println("No cargaron los iconos");
        }

    }

    private void actualizarTabla() {
        consultarCampuses();
        actualizarModeloTabla();
    }
    
    /**
     * Actualiza la tabla con las personas
     */
    private void actualizarModeloTabla() {
        // Crear un modelo de tabla sin encabezados
        DefaultTableModel modeloTabla = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Esto evita que las celdas sean editables
            }
        };

        // Agregar las columnas
        modeloTabla.addColumn("Nombre");
        modeloTabla.addColumn("Numero de ubicaciones");

        // Agregar los datos de las personas
        for (CampusConsultableDTO campus : campuses) {
            int numeroUbicaciones = 0;
            if (campus.getUbicaciones() != null) {
                numeroUbicaciones = campus.getUbicaciones().size();
            }

            Object[] fila = {campus.getNombre(), numeroUbicaciones};
            modeloTabla.addRow(fila);
        }
        // Establecer el modelo de la tabla
        this.tblCampuses.setModel(modeloTabla);
        
        //Al hacer clic en un campus se coloca ese campus como campusSeleccionado
        this.tblCampuses.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int filaSeleccionada = tblCampuses.getSelectedRow();
                
                if (filaSeleccionada != -1) {
                    campusSeleccionado=campuses.get(filaSeleccionada);
                }
            }
        });
    }

    private void consultarCampuses() {
        try {
            campuses=accesoUbicaciones.recuperarTodosLosCampus();
        } catch (NegocioException ex) {
            error("No se pudieron consultar los campus");
        }
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
            return;
        }
        this.setVisible(false);
        new CDCampus(this,this,true,operacion,campusSeleccionado).setVisible(true);
        actualizarTabla();
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

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCampuses = new javax.swing.JTable();
        btnEliminar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnAgregar = new javax.swing.JButton();
        btnConsultarUbicaciones = new javax.swing.JButton();
        lblUbicacionesEstatico = new javax.swing.JLabel();
        lblEliminarEstatico = new javax.swing.JLabel();
        lblEditarEstatico = new javax.swing.JLabel();
        lblAgregarEstatico = new javax.swing.JLabel();
        pnlTitulo = new javax.swing.JPanel();
        lblEstaticoCampus = new javax.swing.JLabel();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblCampuses.setBackground(new java.awt.Color(22, 81, 198));
        tblCampuses.setForeground(new java.awt.Color(255, 255, 255));
        tblCampuses.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Nombre", "Numero de ubicaciones"
            }
        ));
        tblCampuses.setSelectionBackground(new java.awt.Color(22, 81, 198));
        tblCampuses.setSelectionForeground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setViewportView(tblCampuses);

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

        btnConsultarUbicaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConsultarUbicacionesActionPerformed(evt);
            }
        });

        lblUbicacionesEstatico.setText("Consultar ubicaciones");

        lblEliminarEstatico.setText("Eliminar");

        lblEditarEstatico.setText("Editar");

        lblAgregarEstatico.setText("Agregar");

        pnlTitulo.setBackground(new java.awt.Color(22, 81, 198));

        lblEstaticoCampus.setFont(new java.awt.Font("Segoe UI Black", 3, 24)); // NOI18N
        lblEstaticoCampus.setForeground(new java.awt.Color(255, 255, 255));
        lblEstaticoCampus.setText("Campus");

        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlTituloLayout = new javax.swing.GroupLayout(pnlTitulo);
        pnlTitulo.setLayout(pnlTituloLayout);
        pnlTituloLayout.setHorizontalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlTituloLayout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(btnVolver)
                .addGap(150, 150, 150)
                .addComponent(lblEstaticoCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(263, Short.MAX_VALUE))
        );
        pnlTituloLayout.setVerticalGroup(
            pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlTituloLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(pnlTituloLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblEstaticoCampus, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 738, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lblEliminarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(53, 53, 53)
                                    .addComponent(lblEditarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(35, 35, 35))
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(67, 67, 67)
                                    .addComponent(btnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(56, 56, 56)))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(btnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnConsultarUbicaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 173, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                    .addComponent(lblAgregarEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(224, 224, 224)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(591, 591, 591)
                        .addComponent(lblUbicacionesEstatico, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblUbicacionesEstatico)
                    .addComponent(lblEliminarEstatico)
                    .addComponent(lblEditarEstatico)
                    .addComponent(lblAgregarEstatico))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(btnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEditar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnConsultarUbicaciones, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                .addGap(14, 14, 14))
        );

        pack();
        setLocationRelativeTo(null);
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
    private javax.swing.JLabel lblAgregarEstatico;
    private javax.swing.JLabel lblEditarEstatico;
    private javax.swing.JLabel lblEliminarEstatico;
    private javax.swing.JLabel lblEstaticoCampus;
    private javax.swing.JLabel lblUbicacionesEstatico;
    private javax.swing.JPanel pnlTitulo;
    private javax.swing.JTable tblCampuses;
    // End of variables declaration//GEN-END:variables
}
