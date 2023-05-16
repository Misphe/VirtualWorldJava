import MVP.*;
import Tools.Defines;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        boolean correctSize = false;
        int columns = 0;
        int rows = 0;
        String columnsInput = null;
        String rowsInput = null;

        while(!correctSize) {
            columnsInput = JOptionPane.showInputDialog("Enter the number of columns:");
            if (columnsInput == null){
                return;
            }

            rowsInput = JOptionPane.showInputDialog("Enter the number of rows:");
            if (rowsInput == null){
                return;
            }

            try {
                rows = Integer.parseInt(rowsInput);
                columns = Integer.parseInt(columnsInput);
            } catch (RuntimeException e) {
                continue;
            }

            if(rows > 200 || columns > 200 || rows * columns <= Defines.ORGANISMS_COUNT){
                continue;
            }
            correctSize = true;
        }

        View view = new View(columns, rows);
        World world = new World(columns, rows);
        Controller controller = new Controller(world, view);
    }
}
