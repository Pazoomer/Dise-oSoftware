/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JPanel;

/**
 *
 * @author t1pas
 */
public class CuadroDialogoColor extends JDialog {

    private JColorChooser colorChooser;
    private JButton okButton;
    private JButton cancelButton;

    public CuadroDialogoColor(Frame parent) {
        super(parent, "Color de evento", true);
        colorChooser = new JColorChooser();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancelar");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Color chosenColor = colorChooser.getColor();
                // Aquí puedes hacer algo con el color elegido
                dispose(); // Cierra el diálogo después de que se selecciona el color
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
        add(colorChooser, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
