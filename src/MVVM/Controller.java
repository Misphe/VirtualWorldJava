package MVVM;

import javax.swing.*;
import MVVM.IController;

import java.awt.*;
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
        SetListeners();
    }

    private void SetListeners() {
        SetNextTurnListener();
        SetSaveButtonListener();
        SetLoadButtonListener();
        SetSuperPowerButtonListener();
        SetBoardClickListener();
        SetKeyListener();
        CreateLegend();
        UpdateView();
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
                    button.setBackground(Color.BLACK);
                    System.out.println(currentCol + ", " + currentRow);
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
        ArrayList<Organism> allOrganisms = new ArrayList<>();
        allOrganisms.add(new Human(null,		Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Wolf(null,		Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Sheep(null,		Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Fox(null,			Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Turtle(null,		Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Antelope(null,	Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Grass(null,		Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Dandelion(null,	Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new Guarana(null,		Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new WolfBerries(null, Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));
        allOrganisms.add(new PineBorscht(null, Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY));

        String[] legendText = new String[allOrganisms.size()];
        Color[] legendColors = new Color[allOrganisms.size()];

        for(int i = 0; i < allOrganisms.size(); i++) {
            legendText[i] = allOrganisms.get(i).GetName();
            legendColors[i] = allOrganisms.get(i).GetColor();
        }

        view.AddLegend(legendText, legendColors);
    }
}
