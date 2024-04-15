
package presentacion;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author luiis
 */
public class ModeloTablaHorario extends AbstractTableModel {
    // Definir los días de la semana y las horas del día
    private String[] diasSemanales = {"Horas","Domingo", "Lunes", "Martes", "Miercoles", "Jueves","Viernes","Sabado"};
    private String[] horas = {"07:00 AM", "07:30 AM","08:00 AM","08:30 AM","09:00 AM","09:30 AM","10:00 AM","10:30 AM",
                "11:00 AM", "11:30 AM", "12:00 PM", "12:30 PM", "01:00 PM", "01:30 PM", "02:00 PM", "02:30 PM", "03:00 PM","03:30 PM",
                "04:00 PM", "04:30 PM","05:00 PM","05:30 PM","06:00 PM","06:30 PM","07:00 PM","07:30 PM","08:00 PM"};

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

//        //prueba
//        horario[0][1] = "Ingles";
//        horario[1][2] = "diseño";
//        horario[2][3] = "bda";
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
