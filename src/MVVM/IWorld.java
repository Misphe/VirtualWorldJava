package MVVM;

import javafx.util.Pair;
import Organisms.Organism;

public interface IWorld {
    void ExecuteTurn();
    Pair<Integer, Integer> GetSize();
    Organism[][] GetOrganismsTable();

    String[] GetEvents();

    void SetPlayerMove(int pressedKey);

    void SaveState();
    void LoadState();
    void ActivatePlayerPower();
}
