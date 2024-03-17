/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package presentacion;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luiis
 */
public class ModeloTablaHorario extends AbstractTableModel {
    // Definir los días de la semana y las horas del día
    private String[] diasSemanales = {"Horas","Domingo", "Lunes", "Martes", "Miercoles", "Jueves","Viernes","Sabado"};
    private String[] horas = {"7:00 AM", "7:30 AM","8:00 AM","8:30 AM","9:00 AM","9:30 AM","10:00 AM","10:30 AM",
                "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "1:00 PM", "1:30 PM", "2:00 PM", "2:30 PM", "3:00 PM","3:30 PM",
                "4:00 PM", "4:30 PM","5:00 PM","5:30 PM","6:00 PM","6:30 PM","7:00 PM","7:30 PM"};

    private String[][] horario = new String[horas.length][diasSemanales.length];

    public ModeloTablaHorario() {
        for (int i = 0; i < horas.length; i++) {
            for (int j = 0; j < diasSemanales.length; j++) {
                if(j==0)
                    horario[i][j]=horas[i];
                else
                    horario[i][j] = "";
                
            }
        }

        //prueba
        horario[0][1] = "Ingles";
        horario[1][2] = "diseño";
        horario[2][3] = "bda";
    }

    @Override
    public int getRowCount() {
        return horas.length;
    }

    @Override
    public int getColumnCount() {
        return diasSemanales.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return horario[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        horario[rowIndex][columnIndex] = (String) value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return diasSemanales[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true; // Permitir edición de celdas
    }
}
