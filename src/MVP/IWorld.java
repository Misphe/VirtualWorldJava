package MVP;

import javafx.util.Pair;
import Organisms.Organism;

import java.util.ArrayList;

public interface IWorld {
    void ExecuteTurn();
    Pair<Integer, Integer> GetSize();
    Organism[][] GetOrganismsTable();

    String[] GetEvents();

    void SetPlayerMove(int pressedKey);

    void SaveState();
    void LoadState();
    void ActivatePlayerPower();

    void AddNewOrganism(String name, int x, int y);

    ArrayList<Organism> GetOrganismsList();
}
