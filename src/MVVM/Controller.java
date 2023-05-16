package MVVM;

import javax.swing.*;
import MVVM.IController;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import Organisms.*;
import Organisms.Animals.*;
import Plants.*;
import Tools.Defines;
import javafx.util.Pair;

public class Controller implements IController {
    IWorld world;
    IView view;

    public Controller(IWorld world, IView view) {
        this.world = world;
        this.view = view;
        SetKeyListener();
        SetListeners();
        CreateLegend();
        UpdateView();
    }

    private void SetListeners() {
        SetNextTurnListener();
        SetSaveButtonListener();
        SetLoadButtonListener();
        SetSuperPowerButtonListener();
        SetBoardClickListener();
    }

    private void SetNextTurnListener() {
        JButton button = view.GetNextTurnBtn();
        button.addActionListener(e -> {
            System.out.println("klinales nastepna ture");
            world.ExecuteTurn();
            UpdateView();
        });
    }

    private void SetSaveButtonListener() {
        JButton button = view.GetSaveBtn();
        button.addActionListener(e -> {
            world.SaveState();
        });
    }
    private void SetLoadButtonListener() {
        JButton button = view.GetLoadBtn();
        button.addActionListener(e -> {
            world.LoadState();
            Pair<Integer, Integer> size = world.GetSize();
            view.ChangeSize(size.getKey(), size.getValue());
            SetListeners();
            CreateLegend();
            UpdateView();
        });
    }
    private void SetSuperPowerButtonListener() {
        JButton button = view.GetSuperPowerBtn();
        button.addActionListener(e -> {
            world.ActivatePlayerPower();
        });
    }

    private void SetBoardClickListener() {
        JButton[][] board = view.GetBoard();

        int columns = board.length;
        int rows = board[0].length;

        for (int column = 0; column < columns; column++) {
            for (int row = 0; row < rows; row++) {
                JButton button = board[column][row];
                int currentRow = row;
                int currentCol = column;

                button.addActionListener(e -> {

                    ArrayList<Organism> allOrganisms = world.GetOrganismsList();
                    String[] popUpOptions = new String[allOrganisms.size() - 1];
                    for(int i = 1; i < allOrganisms.size(); i++) {
                        popUpOptions[i - 1] = allOrganisms.get(i).GetName();
                    }
                    String title = "Create organism";

                    JPopupMenu popupMenu = view.CreatePopUpList(title, popUpOptions, button);

                    for (Component component : popupMenu.getComponents()) {
                        if (component instanceof JMenuItem) {
                            JMenuItem menuItem = (JMenuItem) component;
                            menuItem.addActionListener(e2 -> {
                                JMenuItem selectedMenuItem = (JMenuItem) e2.getSource();
                                String selectedOption = selectedMenuItem.getText();

                                world.AddNewOrganism(selectedOption, currentCol, currentRow);
                                UpdateView();
                            });
                        }
                    }
                });
            }
        }
    }

    private void SetKeyListener() {
        JFrame frame = view.GetFrame();
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int pressedKey = e.getKeyCode();
                if(pressedMoveKey(pressedKey)){
                    world.SetPlayerMove(pressedKey);
                    world.ExecuteTurn();
                    UpdateView();
                }
            }
        });
    }

    private boolean pressedMoveKey(int pressedKey) {
        if(pressedKey >= 37 && pressedKey <= 40) {
            return true;
        }
        return false;
    }

    private void UpdateView() {
        Pair<Integer,Integer> size = world.GetSize();
        Organism[][] board = world.GetOrganismsTable();
        int sizeX = size.getKey();
        int sizeY = size.getValue();
        Color[][] colors = new Color[sizeX][sizeY];

        for(int x = 0; x < sizeX; x++){
            for (int y = 0; y < sizeY; y++) {
                if(board[x][y] == null) {
                    colors[x][y] = new Color(240, 220, 130);
                }
                else {
                    colors[x][y] = board[x][y].GetColor();
                }
            }
        }

        String[] events = world.GetEvents();

        view.UpdateView(colors, events);
    }

    private void CreateLegend() {
        ArrayList<Organism> allOrganisms = world.GetOrganismsList();

        String[] legendText = new String[allOrganisms.size()];
        Color[] legendColors = new Color[allOrganisms.size()];

        for(int i = 0; i < allOrganisms.size(); i++) {
            legendText[i] = allOrganisms.get(i).GetName();
            legendColors[i] = allOrganisms.get(i).GetColor();
        }

        view.AddLegend(legendText, legendColors);
    }
}
