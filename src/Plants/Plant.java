package Plants;

import MVVM.World;
import Organisms.Organism;
import Tools.Defines;

import java.awt.*;
import java.util.Random;

public abstract class Plant extends Organism {
    @Override
    public void Action() {
        // 5% to continue
        if (new Random().nextInt(100) > Defines.PLANT_SPREAD_CHANCE) {
            return;
        }

        aliveTime++;

        Point new_pos = GetEmptyAdjacent();
        if (new_pos.x == Defines.NO_EMPTY_ADJACENT) {
            return;
        }

        Plant new_plant = Spread();
        new_plant.SetPosition(new_pos);

        world.AddNewOrganism(new_plant);
    }

    public Plant(World ref_world) {
        super(ref_world);
        SetInitiative(0);
    }

    @Override
    public int DefenseResult(Organism attacker) {
        return Defines.ATTACKER_EATS;
    }

    public Plant(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetInitiative(0);
    }

    protected abstract Plant Spread();
}

