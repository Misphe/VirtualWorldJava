package MVVM;
import javax.swing.*;
import java.awt.*;

public interface IView {

    JFrame GetFrame();
    JButton GetNextTurnBtn();
    JButton GetSaveBtn();
    JButton GetLoadBtn();
    JButton GetSuperPowerBtn();
    JButton[][] GetBoard();

    void UpdateView(Color[][] colors, String[] events);

    void AddLegend(String[] legendText, Color[] legendColors);

    void ChangeSize(int x, int y);
}
