
package presentacion.pantallas;

import DTOS.evento.EventoConsultableDTO;
import DTOS.maestro.MaestroEditableDTO;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import presentacion.CDEvento;
import presentacion.ModeloTablaHorario;


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
    static DefaultTableModel model;
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
        model=new DefaultTableModel();
        cargarCalendario();
        //cargarEventoInicio();
        ModeloTablaHorario model2=new ModeloTablaHorario();
        tablaEventos.setModel(model2);
        //cargarTablaEventos();
        this.setVisible(true);
    }
    
    //establece la fecha del dateChooser a la fecha actual
    private void cargarEventoInicio(){
        Calendar cal=Calendar.getInstance();
        //Date date=cal.getTime();
        calEsquinaSuperior.setCalendar(cal);
        //carga la tabla de eventos del dia actual
        cargarTablaEventos();
    }
    
    //carga la tabla de eventos de cada dia
    private void cargarTablaEventos() {
        model = new DefaultTableModel();
        
        Calendar fecha = calEsquinaSuperior.getCalendar();
        
        int diaDeLaSemana=fecha.get(Calendar.DAY_OF_WEEK);
        String[] diasSemana={"Domingo","Lunes","Martes","Miercoles","Jueves","Viernes","Sabado"};
        switch(diaDeLaSemana){
            case 1 -> setSemana(new String[]{"sumar","sumar","sumar","sumar","sumar","sumar","sumar",},
                        new int[]{0,1,2,3,4,5,6}, model, fecha, diasSemana);
            case 2 -> setSemana(new String[]{"restar","sumar","sumar","sumar","sumar","sumar","sumar",}, 
                        new int[]{1,0,1,2,3,4,5}, model, fecha, diasSemana);
            case 3 -> setSemana(new String[]{"restar","restar","sumar","sumar","sumar","sumar","sumar",}, 
                        new int[]{2,1,0,1,2,3,4}, model, fecha, diasSemana);
            case 4 -> setSemana(new String[]{"restar","restar","restar","sumar","sumar","sumar","sumar",}, 
                        new int[]{3,1,1,0,1,2,3}, model, fecha, diasSemana);
            case 5 -> setSemana(new String[]{"restar","restar","restar","restar","sumar","sumar","sumar",}, 
                        new int[]{4,3,2,1,0,1,2}, model, fecha, diasSemana);
            case 6 -> setSemana(new String[]{"restar","restar","restar","restar","restar","sumar","sumar",}, 
                        new int[]{5,4,3,2,1,0,1}, model, fecha, diasSemana);
            case 7 -> setSemana(new String[]{"restar","restar","restar","restar","restar","restar","restar",}, 
                        new int[]{6,5,4,3,2,1,0}, model, fecha, diasSemana);
        }
        
        tablaEventos.setModel(model);
    }
    /**
     * Carga todas las columnas de la tabla semanal. 
     * Este metodo usa el ciclo for con una cantidad maxima de 7 iteraciones, 
     * equivalente a la cantidad de columnas de la tabla (1 columna por cada dia de la semana). 
     * En cada iteracion se agrega una columna nueva iniciando desde la columna 1 donde se 
     * guardan los eventos del dia Domingo, la columna 2 guarda los eventos del dia Lunes, 
     * la columna 3 los del dia Martes y asi sucesivamente hasta llegar al sabado.
     * 
     * @param arrayOperaciones Arreglo con la secuencia de operaciones (suma o resta) 
     * que se van a hacer dentro del metodo agregarColumnasTabla. 
     * La explicacion de la necesidad de la especificacion del tipo de operacion
     * se encuentra en la documentacion del metodo agregarColumnasTabla
     * @param arrayCantidadDias Arreglo con la cantidad de dias que se van a sumar o restar
     * a la fecha determinada por el parametro 'fecha'.
     * @param model Modelo de la tabla a la que se le iran agregando las columnas. Posteriormente, 
     * este modelo se le asignara a la tabla semanal
     * @param fecha Fecha base a la cual se le van a agregar o quitar dias
     * @param diasSemana Arreglo que contiene los dias de la semana, siendo este: lunes, martes, miercoles, etc.
     */
    private void setSemana(String[] arrayOperaciones, int[] arrayCantidadDias, 
            DefaultTableModel model, Calendar fecha, String[] diasSemana){
        for (int i = 0; i < 7; i++) {
            agregarColumnasTabla(model, fecha, arrayOperaciones[i], arrayCantidadDias[i], diasSemana[i]);
        }
    }
    
    /**
     * Carga una columna de la tabla semanal.
     * Este metodo agrega la columna del dia de la semana especificado por el parametro 'nombreColumna',
     * 
     * Para obtener los eventos de cada dia de la semana seleccionada en el JCalendar 
     * se necesita el dia que se selecciono especificamente, para tomarlo como fecha base y luego
     * se le iran sumando o restando dias dependiendo que dia de la semana sea (entre domingo y sabado).
     * Por ejemplo: si la fecha seleccionada en el JCalendar es 15/03/2024 el dia de la semana es viernes,
     * por lo tanto si quiero obtener los eventos que hay en el dia martes 12/03/2024 debo restarle 3 dias
     * a la fecha base; si quiero obtener los eventos que hay en el dia sabado 16/03/2024 debo sumarle un dia
     * a la fecha base.
     * 
     * Los eventos se obtienen del metodo cargarEventosDelDia que recibe como parametro el dia del año. Los eventos
     * se guardan en un arreglo tipo Object para poder pasarlos como argumento al metodo addColumn, el cual
     * agrega una columna con un nombre especifico y unos valores especificos al modelo de la tabla. 
     * 
     * @param model Modelo de la tabla al cual se le agregara la columna
     * @param fecha Fecha base a la cual se le agregaran o quitaran dias
     * @param tipoOperacion Especificacion del tipo de operacion (suma o resta) que se le hara a la fecha base
     * @param cantidadDias Cantidad de dias que se le van a agrear o quitar a la fecha base
     * @param nombreColumna Nombre que se le asignara a la columna. El nombre de cada columna sera cada dia de la semana
     */
    private void agregarColumnasTabla(DefaultTableModel model, Calendar fecha, String tipoOperacion, 
            int cantidadDias,String nombreColumna){
        Calendar fechaCopia=fecha;
        if(tipoOperacion.equals("sumar"))
            fechaCopia.add(Calendar.DAY_OF_MONTH, cantidadDias);
        else if(tipoOperacion.equals("restar"))
            fechaCopia.add(Calendar.DAY_OF_MONTH, -cantidadDias);
        
        int diaDelAnio=fecha.get(Calendar.DAY_OF_YEAR);
        Object[] eventos=cargarEventosDelDia(diaDelAnio);
        model.addColumn(nombreColumna, eventos);
    }
    
    /**
     * Obtiene los eventos del dia especificado en el parametro
     * @param dia Dia del cual se quieren obtener los eventos
     * @return Un arreglo con los eventos obtenidos
     */
    private Object[] cargarEventosDelDia(int dia){
        ArrayList<String> eventosDelDia=new ArrayList<>();
        for(EventoConsultableDTO evento:calendarioMes){
            if (evento.getFechaInicio().get(Calendar.DAY_OF_YEAR) == dia) {
                eventosDelDia.add(evento.getNombre());
            }
        }
        
        return eventosDelDia.toArray();
    }
    
    //inicializa el calendario con los eventos del mes seleccionado
    private void cargarCalendario(){
       Calendar fecha=calEsquinaSuperior.getCalendar();
       int mes=fecha.get(Calendar.MONTH);
       calendarioMes=new ArrayList<>();
        
        for (EventoConsultableDTO evento : maestro.getCalendario()) {
            if (evento.getFechaInicio().get(Calendar.MONTH) == mes || evento.getTipo().equalsIgnoreCase("semanal")) {
                calendarioMes.add(evento);
            }
        }
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
    /*TODO
    Cambia la informacion de un evento
    private void editarEvento(){
        cdEvento = new CDEvento(this,this, evento,true);
        cdEvento.setVisible(true);
    }*/


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        btnEditarEvento = new javax.swing.JButton();
        btnAñadirEvento = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnAtras = new javax.swing.JButton();
        pnlCalendario = new javax.swing.JPanel();
        scpCalendario = new javax.swing.JScrollPane();
        tablaEventos = new javax.swing.JTable();
        calEsquinaSuperior = new com.toedter.calendar.JCalendar();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(null);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 600));

        btnEditarEvento.setBackground(new java.awt.Color(22, 81, 198));
        btnEditarEvento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnEditarEvento.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarEvento.setText("Editar Evento");
        btnEditarEvento.setBorder(null);
        btnEditarEvento.setBorderPainted(false);
        btnEditarEvento.setPreferredSize(new java.awt.Dimension(69, 15));
        btnEditarEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnEditarEventoMouseEntered(evt);
            }
        });
        btnEditarEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarEventoActionPerformed(evt);
            }
        });

        btnAñadirEvento.setBackground(new java.awt.Color(22, 81, 198));
        btnAñadirEvento.setFont(new java.awt.Font("SansSerif", 0, 11)); // NOI18N
        btnAñadirEvento.setForeground(new java.awt.Color(255, 255, 255));
        btnAñadirEvento.setText("Añadir evento");
        btnAñadirEvento.setBorder(null);
        btnAñadirEvento.setBorderPainted(false);
        btnAñadirEvento.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAñadirEventoMouseEntered(evt);
            }
        });
        btnAñadirEvento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAñadirEventoActionPerformed(evt);
            }
        });

        btnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-save-50.png"))); // NOI18N
        btnGuardar.setBorder(null);
        btnGuardar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnGuardarMouseEntered(evt);
            }
        });
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnAtras.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-return-50.png"))); // NOI18N
        btnAtras.setBorder(null);
        btnAtras.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnAtrasMouseEntered(evt);
            }
        });
        btnAtras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtrasActionPerformed(evt);
            }
        });

        pnlCalendario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        tablaEventos.setModel(new javax.swing.table.DefaultTableModel(
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
        scpCalendario.setViewportView(tablaEventos);

        javax.swing.GroupLayout pnlCalendarioLayout = new javax.swing.GroupLayout(pnlCalendario);
        pnlCalendario.setLayout(pnlCalendarioLayout);
        pnlCalendarioLayout.setHorizontalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlCalendarioLayout.createSequentialGroup()
                .addComponent(scpCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 462, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        pnlCalendarioLayout.setVerticalGroup(
            pnlCalendarioLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlCalendarioLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(scpCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(51, 51, 51)
                        .addComponent(btnAñadirEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(btnEditarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(calEsquinaSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(calEsquinaSuperior, javax.swing.GroupLayout.PREFERRED_SIZE, 204, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnEditarEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnAñadirEvento, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(39, 39, 39)
                        .addComponent(btnGuardar)
                        .addGap(82, 82, 82)
                        .addComponent(btnAtras, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(pnlCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);
        jPanel1.setBounds(0, 80, 800, 520);

        jPanel2.setBackground(new java.awt.Color(22, 81, 198));

        jLabel1.setFont(new java.awt.Font("SansSerif", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagenes/icons8-calendar-50white.png"))); // NOI18N
        jLabel1.setText("Calendario");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addComponent(jLabel1)
                .addContainerGap(609, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2);
        jPanel2.setBounds(0, 0, 800, 80);

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

    private void calEsquinaSuperiorPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calEsquinaSuperiorPropertyChange

        if (calendarioMes != null) {
            cargarTablaEventos();
        }

    }//GEN-LAST:event_calEsquinaSuperiorPropertyChange

    private void btnEditarEventoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarEventoActionPerformed
        //editarEvento();
    }//GEN-LAST:event_btnEditarEventoActionPerformed

    private void btnAñadirEventoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAñadirEventoMouseEntered
        // TODO add your handling code here:
        btnAñadirEvento.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAñadirEventoMouseEntered

    private void btnEditarEventoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEditarEventoMouseEntered
        // TODO add your handling code here:
        btnEditarEvento.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnEditarEventoMouseEntered

    private void btnGuardarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnGuardarMouseEntered
        // TODO add your handling code here:
        btnGuardar.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnGuardarMouseEntered

    private void btnAtrasMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnAtrasMouseEntered
        // TODO add your handling code here:
        btnAtras.setBackground(Color.lightGray);
    }//GEN-LAST:event_btnAtrasMouseEntered


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtras;
    private javax.swing.JButton btnAñadirEvento;
    private javax.swing.JButton btnEditarEvento;
    private javax.swing.JButton btnGuardar;
    private com.toedter.calendar.JCalendar calEsquinaSuperior;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel pnlCalendario;
    private javax.swing.JScrollPane scpCalendario;
    private javax.swing.JTable tablaEventos;
    // End of variables declaration//GEN-END:variables
}
