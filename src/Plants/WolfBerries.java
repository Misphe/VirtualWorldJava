package Plants;

import MVP.World;
import Organisms.Organism;
import Tools.Defines;

import java.awt.*;

public class WolfBerries extends Plant {

    @Override
    public Plant Spread() {
        return new WolfBerries(world, GetX(), GetY());
    }

    public WolfBerries(World ref_world) {
        super(ref_world);
        SetStrength(Defines.WOLFBERRIES_STRENGTH);
    }

    public WolfBerries(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.WOLFBERRIES_STRENGTH);
    }

    @Override
    public char GetSymbol() {
        return Defines.WOLFBERRIES_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return Color.LIGHT_GRAY;
    }

    @Override
    public int DefenseResult(Organism attacker) {
        return Defines.BOTH_DIED;
    }

    @Override
    public String GetName() {
        return "Wolf Berries";
    }
}

