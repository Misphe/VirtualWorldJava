package Plants;

import MVVM.World;
import Tools.Defines;

import java.awt.*;
import java.util.Objects;

public class Dandelion extends Plant {
    public Dandelion(World ref_world) {
        super(ref_world);
        SetStrength(0);
    }

    public Dandelion(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(0);
    }

    @Override
    public String GetName() {
        return "Dandelion";
    }

    @Override
    public void Action() {
        for (int i = 0; i < Defines.DANDELION_TURNS; i++) {
            super.Action();
        }
    }

    @Override
    public char GetSymbol() {
        return Defines.DANDELION_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return Color.YELLOW;
    }

    @Override
    protected Dandelion Spread() {
        return new Dandelion(world, GetX(), GetY());
    }

}

