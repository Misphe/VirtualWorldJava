package Plants;

import MVP.World;
import Tools.Defines;

import java.awt.*;

public class Grass extends Plant {

    @Override
    public Grass Spread() {
        return new Grass(world, GetX(), GetY());
    }

    @Override
    public String GetName() {
        return "Grass";
    }

    public Grass(World ref_world) {
        super(ref_world);
        SetStrength(Defines.PLANT_STRENGTH);
    }

    public Grass(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.PLANT_STRENGTH);
    }

    @Override
    public char GetSymbol() {
        return Defines.GRASS_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return Color.GREEN;
    }
}

