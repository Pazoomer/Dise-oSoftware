package presentacion.pantallas;

import DTOS.campus.CampusConsultableDTO;
import DTOS.campus.UbicacionDTO;
import DTOS.maestro.MaestroEditableDTO;
import accesoMaestro.FachadaAccesoMaestro;
import accesoMaestro.IAccesoMaestro;
import accesoUbicaciones.FachadaAccesoUbicaciones;
import accesoUbicaciones.IAccesoUbicaciones;
import excepciones.NegocioException;
import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

/**
 *
 * @author t1pas
 */
public class PrincipalMaestro extends javax.swing.JFrame {

    /**
     * Es el maestro con el que iniciaste sesion
     */
    private MaestroEditableDTO maestroDTO;
    private final IAccesoMaestro accesoMaestro;
    private final IAccesoUbicaciones accesoUbicaciones;
    private DefaultComboBoxModel cmbBoxModel;
    private List<UbicacionDTO> edificiosCubiculos;


    /**
     * Creates new form PrincipalMaestro
     *
     * @param maestro
     */
    public PrincipalMaestro(MaestroEditableDTO maestro) {
        initComponents();
        this.maestroDTO = maestro;
        this.accesoMaestro=new FachadaAccesoMaestro();
        this.accesoUbicaciones=new FachadaAccesoUbicaciones();
        cargarMaestro();
        cargarIconos();
        this.setVisible(true);
        this.setSize(800, 600);
        cargarComboBox();
    }

    private void cargarEdificios() {
        edificiosCubiculos=new ArrayList<>();
        try {
            List<CampusConsultableDTO> listaCampus = accesoUbicaciones.recuperarTodosLosCampus();
            for (CampusConsultableDTO campus : listaCampus) {
                if (campus.getUbicaciones() != null) {
                    for (UbicacionDTO u : campus.getUbicaciones()) {
                        edificiosCubiculos.add(u);
                    }
                }
            }
        } catch (NegocioException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    private void cargarComboBox() {
        cmbBoxModel = new DefaultComboBoxModel();
        cargarEdificios();

        for (UbicacionDTO u : edificiosCubiculos) {
            cmbBoxModel.addElement(u.getIdentificador());
        }
        cmbBoxCubiculos.setModel(cmbBoxModel);
        setCubiculoCmbBox();

    }
    
    private void setCubiculoCmbBox(){
        if(edificiosCubiculos!=null && !edificiosCubiculos.isEmpty()){
            for (UbicacionDTO u : edificiosCubiculos) {
                if (u.getIdentificador().equals(maestroDTO.getCubiculo().getIdentificador())) {
                    cmbBoxCubiculos.setSelectedItem(u.getIdentificador());
                    break;
                }
            }
        }
        
    }
    /**
     * Carga los iconos en los botones de la interfaz.
     */
    private void cargarIconos() {
        // Carga el icono de retorno en el botón btnAtras
        ImageIcon iconoReturn = new ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"));
        
        btnAtras.setIcon(iconoReturn);
        // Carga el icono de calendario en el botón btnCalendario
        ImageIcon iconoCalendario = new ImageIcon(getClass().getResource("/imagenes/icons8-calendar-50.png"));
        btnCalendario.setIcon(iconoCalendario);
        // Carga el icono de guardar en el botón btnActualizar
        ImageIcon iconoGuardar = new ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"));
        btnActualizar.setIcon(iconoGuardar);
    }

    /**
     * Coloca los valores del maestro en los campos de texto y foto de perfil
     */
    private void cargarMaestro() {
        //this.txtCubiculo.setText(maestroDTO.getCubiculo().getIdentificador());
        this.txaDescripcion.setText(maestroDTO.getDescripcion());
        this.lblNombreMaestro.setText(maestroDTO.getNombre());
        this.lblFotoMaestro.setIcon(cargarIconoDesdeArchivo(maestroDTO.getFoto()));
        setCubiculoCmbBox();
    }
    
    public ImageIcon cargarIconoDesdeArchivo(String nombreArchivo) {
        // Obtener la ruta del directorio del proyecto
        String directorioProyecto = System.getProperty("user.dir");
        String rutaImagen = directorioProyecto + File.separator + nombreArchivo;

        // Verificar si el archivo de imagen existe
        File archivoImagen = new File(rutaImagen);
        if (!archivoImagen.exists()) {
            System.out.println("El archivo de imagen '" + nombreArchivo + "' no existe en la carpeta del proyecto.");
            return null;
        }

        // Cargar la imagen y convertirla en un ImageIcon
        ImageIcon icono = new ImageIcon(rutaImagen);
        return icono;
    }

    /**
     * Toma los campos de texto y foto de perfil y la guarda en la base de datos
     * actualizando el perfil de maestro
     */
    private void editarInformacion() {
        String descripcion = this.txaDescripcion.getText();
        String edificioCubiculo=cmbBoxCubiculos.getSelectedItem().toString();
        UbicacionDTO ubicacionReal=new UbicacionDTO();
        
        for (UbicacionDTO ubicacion: edificiosCubiculos) {
            if (ubicacion.getIdentificador().equals(edificioCubiculo)) {
                ubicacionReal=ubicacion;
            }
        }
        
        UbicacionDTO ubicacionCubiculo;
        MaestroEditableDTO maestroAuxiliar;
        
        try{
            if (ubicacionReal==null) {
                throw new NegocioException("El cubiculo no pertenece a ninguna ubicacion");
            }
            ubicacionCubiculo=accesoUbicaciones.recuperarUbicacion(ubicacionReal);
            
            maestroAuxiliar = new MaestroEditableDTO(maestroDTO.getId(), maestroDTO.getNombre(),
                    ubicacionCubiculo, descripcion, maestroDTO.getFoto(),maestroDTO.getCalendario());
            
            maestroAuxiliar.setIdBD(maestroDTO.getIdBD());
            
            if(accesoMaestro.editarMaestro(maestroAuxiliar)){
                maestroDTO=maestroAuxiliar;
                JOptionPane.showMessageDialog(null, "Se actualizo su informacion", "Mensaje de confirmación", JOptionPane.INFORMATION_MESSAGE);
                cargarMaestro();
            }
            
        }catch(NegocioException e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }

    }

    /**
     * Permite cambiar la foto de perfil al darle clic por una en la carpeta de
     * archivos
     */
    private void añadirImagen() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = fileChooser.getSelectedFile();
            ImageIcon icon = new ImageIcon(selectedFile.getAbsolutePath());

            ImageIcon scaledIcon = new ImageIcon(icon.getImage().getScaledInstance(124, 102, Image.SCALE_SMOOTH));
            this.lblFotoMaestro.setIcon(scaledIcon);
            this.lblFotoMaestro.setText(""); // Clear text
        }

    }

    /**
     * Al dar clic en el boton de calendario deberia desplegarse el frame
     * PrincipalCalendario y ocultarse este frame
     */
    private void abrirCalendario() {
        new PrincipalCalendario(this,this, maestroDTO).setVisible(true);
        this.setVisible(false);
    }

    /**
     * Cierra este frame y por consecuencia se acaba la ejecucion del programa
     */
    private void cerrar() {
        this.dispose();
        
    }

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnAtras = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnActualizar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txaDescripcion = new javax.swing.JTextArea();
        lblDescripcionEstatico = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        lblCubiculoEstatico = new javax.swing.JLabel();
        lblNombreMaestro = new javax.swing.JLabel();
        lblBienvenido = new javax.swing.JLabel();
        lblFotoMaestro = new javax.swing.JLabel();
        lblInfoFotoEstatico = new javax.swing.JLabel();
        cmbBoxCubiculos = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(800, 600));
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));
        setResizable(false);
        getContentPane().setLayout(null);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(null);

        btnAtras.setForeground(new java.awt.Color(255, 255, 255));
        btnAtras.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255), 0));
        btnAtras.setBorderPainted(false);
        btnAtras.setFocusPainted(false);
        btnAtras.setPreferredSize(new java.awt.Dimension(83, 59));
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnAtrasMouseExited(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                btnAtrasMouseReleased(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });
        jPanel2.add(btnAtras);
        btnAtras.setBounds(70, 370, 70, 50);

        btnCalendario.setBorder(null);
        btnCalendario.setBorderPainted(false);
        btnCalendario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCalendarioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCalendarioMouseExited(evt);
            }
        });
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });
        jPanel2.add(btnCalendario);
        btnCalendario.setBounds(363, 348, 80, 70);

        btnActualizar.setBorder(null);
        btnActualizar.setBorderPainted(false);
        btnActualizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnActualizarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnActualizarMouseExited(evt);
            }
        });
        btnActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarActionPerformed(evt);
            }
        });
        jPanel2.add(btnActualizar);
        btnActualizar.setBounds(650, 370, 80, 50);

        txaDescripcion.setColumns(20);
        txaDescripcion.setRows(5);
        jScrollPane1.setViewportView(txaDescripcion);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(100, 70, 570, 260);

        lblDescripcionEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblDescripcionEstatico.setText("Informacion del maestro");
        jPanel2.add(lblDescripcionEstatico);
        lblDescripcionEstatico.setBounds(100, 50, 296, 14);

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 160, 800, 440);

        jPanel1.setBackground(new java.awt.Color(22, 81, 198));

        lblCubiculoEstatico.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lblCubiculoEstatico.setForeground(new java.awt.Color(255, 255, 255));
        lblCubiculoEstatico.setText("Cubículo");

        lblNombreMaestro.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        lblNombreMaestro.setForeground(new java.awt.Color(255, 255, 255));
        lblNombreMaestro.setText("Nombre completo del maestro");

        lblBienvenido.setFont(new java.awt.Font("SansSerif", 0, 24)); // NOI18N
        lblBienvenido.setForeground(new java.awt.Color(255, 255, 255));
        lblBienvenido.setText("Bienvenido");

        lblFotoMaestro.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        lblFotoMaestro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblFotoMaestroMouseClicked(evt);
            }
        });

        lblInfoFotoEstatico.setFont(new java.awt.Font("SansSerif", 0, 10)); // NOI18N
        lblInfoFotoEstatico.setForeground(new java.awt.Color(204, 204, 204));
        lblInfoFotoEstatico.setText("Clic en la foto para cambiarla");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(lblFotoMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblBienvenido, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 172, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblCubiculoEstatico, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbBoxCubiculos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(67, 67, 67))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(lblInfoFotoEstatico)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblBienvenido)
                                .addGap(14, 14, 14)
                                .addComponent(lblNombreMaestro))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(lblCubiculoEstatico)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cmbBoxCubiculos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lblFotoMaestro, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(lblInfoFotoEstatico)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 0, 800, 160);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarActionPerformed
        editarInformacion();
    }//GEN-LAST:event_btnActualizarActionPerformed

    private void lblFotoMaestroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblFotoMaestroMouseClicked
        añadirImagen();
    }//GEN-LAST:event_lblFotoMaestroMouseClicked

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        abrirCalendario();
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnAtrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtrasActionPerformed
        cerrar();
    }//GEN-LAST:event_btnAtrasActionPerformed

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        // TODO add your handling code here:
        btnAtras.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAtrasMouseEntered

    private void btnCalendarioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalendarioMouseEntered

        btnCalendario.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnCalendarioMouseEntered

    private void btnActualizarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseEntered

        btnActualizar.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnActualizarMouseEntered

    private void btnAtrasMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseExited
        // TODO add your handling code here:
        btnAtras.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnAtrasMouseExited

    private void btnAtrasMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_btnAtrasMouseReleased

    private void btnCalendarioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCalendarioMouseExited
        // TODO add your handling code here:
        btnCalendario.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnCalendarioMouseExited

    private void btnActualizarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnActualizarMouseExited
        // TODO add your handling code here:
        btnActualizar.setBackground(Color.WHITE);
    }//GEN-LAST:event_btnActualizarMouseExited


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizar;
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnCalendario;
    private javax.swing.JComboBox<String> cmbBoxCubiculos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblBienvenido;
    private javax.swing.JLabel lblCubiculoEstatico;
    private javax.swing.JLabel lblDescripcionEstatico;
    private javax.swing.JLabel lblFotoMaestro;
    private javax.swing.JLabel lblInfoFotoEstatico;
    private javax.swing.JLabel lblNombreMaestro;
    private javax.swing.JTextArea txaDescripcion;
    // End of variables declaration//GEN-END:variables
}
