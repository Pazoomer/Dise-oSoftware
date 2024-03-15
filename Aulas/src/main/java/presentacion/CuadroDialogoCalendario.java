
package presentacion;

import com.toedter.calendar.JCalendar;
import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author t1pas
 */
public class CuadroDialogoCalendario extends JDialog{
    private JCalendar calendarioChooser;
    private JButton okButton;
    private JButton cancelButton;
    private Date fecha;

    public CuadroDialogoCalendario(Frame parent) {
        super(parent, "Fecha de evento", true);
        calendarioChooser = new JCalendar();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancelar");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fecha=calendarioChooser.getDate();
                //TODO
                //Aqui debe tomar los valores que selecciono el usuario
                dispose(); 
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el diálogo si se cancela
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(cancelButton);

        setLayout(new BorderLayout());
        add(calendarioChooser, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
    
    public Date getDate(){
        return fecha;
    }
}
