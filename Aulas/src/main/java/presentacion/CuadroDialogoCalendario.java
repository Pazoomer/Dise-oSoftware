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
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 *
 * @author t1pas
 */
public class CuadroDialogoCalendario extends JDialog{
    private JColorChooser colorChooser;
    private JButton okButton;
    private JButton cancelButton;

    public CuadroDialogoCalendario(Frame parent) {
        super(parent, "Fecha de evento", true);
        colorChooser = new JColorChooser();
        okButton = new JButton("OK");
        cancelButton = new JButton("Cancelar");

        okButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
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
        add(colorChooser, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        pack();
        setLocationRelativeTo(parent);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }
}
