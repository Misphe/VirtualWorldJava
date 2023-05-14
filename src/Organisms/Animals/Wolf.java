package Organisms.Animals;

import java.awt.*;

import MVVM.*;
import Tools.*;

public class Wolf extends Animal {
    public Wolf(World ref_world) {
        super(ref_world);
        SetStrength(Defines.WOLF_STRENGTH);
        SetInitiative(Defines.WOLF_INITIATIVE);
    }

    public Wolf(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.WOLF_STRENGTH);
        SetInitiative(Defines.WOLF_INITIATIVE);
    }

    @Override
    public Wolf Breed() {
        return new Wolf(world, GetX(), GetY());
    }

    @Override
    public char GetSymbol() {
        return Defines.WOLF_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return Color.DARK_GRAY;
    }

    @Override
    public String GetName() {
        return "Wolf";
    }
}


