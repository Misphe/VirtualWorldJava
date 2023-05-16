package Organisms.Animals;

import java.awt.*;

import MVP.*;
import Tools.*;

public class Sheep extends Animal {
    public Sheep(World ref_world) {
        super(ref_world);
        SetStrength(Defines.SHEEP_STRENGTH);
        SetInitiative(Defines.SHEEP_INITIATIVE);
    }

    public Sheep(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.SHEEP_STRENGTH);
        SetInitiative(Defines.SHEEP_INITIATIVE);
    }

    @Override
    public Sheep Breed() {
        return new Sheep(world, GetX(), GetY());
    }

    @Override
    public char GetSymbol() {
        return Defines.SHEEP_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return Color.WHITE;
    }

    @Override
    public String GetName() {
        return "Sheep";
    }
}

