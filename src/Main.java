import MVVM.*;

public class Main {

    public static void main(String[] args) {
        // Get the number of rows from user input
        // int rows = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of rows:"));
        // Get the number of columns from user input
        // int columns = Integer.parseInt(JOptionPane.showInputDialog("Enter the number of columns:"));

        View view = new View(50, 50);
        World world = new World(50,50);
        Controller controller = new Controller(world, view);
    }
}
