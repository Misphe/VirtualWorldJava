package MVVM;

import Organisms.*;
import Organisms.Animals.*;
import Plants.*;
import Tools.Defines;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class View implements IView {
    JFrame frame;
    JPanel buttonsPanel;
    JPanel logsPanel;
    JPanel legendPanel;

    JButton[][] buttons;

    JLabel[] logs;

    JButton nextTurn;
    JButton saveButton;
    JButton loadButton;
    JButton superPowerButton;

    private final int windowsSizeX = 1000;
    private final int windowsSizeY = 1000;
    public final int buttonSize;
    public int columns;
    public int rows;


    public View(int columns, int rows) {
        this.buttonsPanel = new JPanel();
        this.logsPanel = new JPanel();
        this.legendPanel = new JPanel();
        this.frame = new JFrame("Adrian");
        this.buttons = new JButton[columns][rows];
        this.logs = new JLabel[Defines.LOG_LENGTH];
        this.nextTurn = new JButton();
        this.saveButton = new JButton();
        this.loadButton = new JButton();
        this.superPowerButton = new JButton();
        this.nextTurn.setText("next turn");
        this.saveButton.setText("save");
        this.loadButton.setText("load");
        this.superPowerButton.setText("activate power");
        this.columns = columns;
        this.rows = rows;

        buttonSize = (int) Math.floor(windowsSizeX / (columns * 1.5));

        frame.setFocusable(true);
        frame.requestFocusInWindow();
        frame.requestFocus();
        AddButtons();
        AddLogs();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        buttonsPanel.setLayout(null);
        //buttonsPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        frame.setSize(windowsSizeX, windowsSizeY);
        frame.setLayout(null);
        buttonsPanel.setBounds(Defines.BOARD_POS_X, Defines.BOARD_POS_Y, buttonSize * (columns + 3), buttonSize * (columns + 5));
        logsPanel.setBounds(buttonSize * (columns + 3), 50, Defines.LOG_WIDTH, Defines.LOG_HEIGHT);
        frame.add(buttonsPanel);
        frame.add(logsPanel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void AddButtons() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < columns; col++) {
                JButton button = new JButton();
                button.setBounds(col * buttonSize, row * buttonSize, buttonSize, buttonSize);
                buttons[col][row] = button;
                button.setFocusable(false);
                buttonsPanel.add(button);
            }
        }

        int nextTurnX = 0;
        int nextTurnY = rows * buttonSize + 2 * buttonSize;

        nextTurn.setBounds(nextTurnX, nextTurnY, Defines.NEXTTURNWIDTH, Defines.NEXTTURNHEIGHT);
        nextTurn.setFocusable(false);
        saveButton.setBounds(nextTurnX + 100, nextTurnY, Defines.NEXTTURNWIDTH, Defines.NEXTTURNHEIGHT);
        saveButton.setFocusable(false);
        loadButton.setBounds(nextTurnX + 200, nextTurnY, Defines.NEXTTURNWIDTH, Defines.NEXTTURNHEIGHT);
        loadButton.setFocusable(false);
        superPowerButton.setBounds(nextTurnX + 300, nextTurnY, (int) (Defines.NEXTTURNWIDTH * 1.5), Defines.NEXTTURNHEIGHT);
        superPowerButton.setFocusable(false);
        buttonsPanel.add(nextTurn);
        buttonsPanel.add(saveButton);
        buttonsPanel.add(loadButton);
        buttonsPanel.add(superPowerButton);
    }

    private void AddLogs() {
        logsPanel.setLayout(new BoxLayout(logsPanel, BoxLayout.Y_AXIS));
        int logsSize = logs.length;
        for(int i = 0; i < logsSize; i++) {
            logs[i] = new JLabel("");
            logsPanel.add(logs[i]);
        }
    }

    @Override
    public void AddLegend(String[] legendText, Color[] legendColors) {
        legendPanel.setLayout(new BoxLayout(legendPanel, BoxLayout.Y_AXIS));
        legendPanel.setBounds( buttonSize * (columns + 3), Defines.LEGEND_Y, Defines.LEGEND_WIDTH, Defines.LEGEND_HEIGHT);

        for(int i = 0; i < legendText.length; i++) {
            JButton colorButton = new JButton();
            colorButton.setBackground(legendColors[i]);
            colorButton.setPreferredSize(new Dimension(20, 20));
            colorButton.setEnabled(false);

            JLabel nameLabel = new JLabel(" - " + legendText[i]);

            JPanel linePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
            linePanel.add(colorButton);
            linePanel.add(nameLabel);

            legendPanel.add(linePanel);
        }

        frame.add(legendPanel);
    }


    @Override
    public JButton GetNextTurnBtn() {
        return nextTurn;
    }
    @Override
    public JButton GetSaveBtn() {
        return saveButton;
    }
    @Override
    public JButton GetLoadBtn() {
return loadButton;
    }
    @Override
    public JButton GetSuperPowerBtn() {
return superPowerButton;
    }

    @Override
    public JButton[][] GetBoard() {
        return buttons;
    }

    @Override
    public JFrame GetFrame() {
        return frame;
    }

    @Override
    public void UpdateView(Color[][] colors, String[] events) {
        for(int x = 0; x < columns; x++) {
            for(int y = 0; y < rows; y++) {
                buttons[x][y].setBackground(colors[x][y]);
            }
        }

        for(int i = 0; i < logs.length; i++) {
            logs[i].setText(events[i]);
        }
    }

    @Override
    public void ChangeSize(int x, int y) {
        this.columns = x;
        this.rows = y;
        buttons = new JButton[x][y];

        frame.remove(buttonsPanel);
        frame.remove(logsPanel);

        frame.revalidate();
        frame.repaint();

        buttonsPanel = new JPanel();
        logsPanel = new JPanel();
        buttonsPanel.setLayout(null);
        buttonsPanel.setBounds(0, 0, buttonSize * (columns + 3), buttonSize * (columns + 5));
        logsPanel.setBounds(buttonSize * (columns + 3), 50, 500, 500);
        AddLogs();
        AddButtons();

        frame.add(buttonsPanel);
        frame.add(logsPanel);
    }
}
