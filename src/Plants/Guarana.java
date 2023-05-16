package Plants;

import MVP.World;
import Tools.Defines;

import java.awt.Color;
import Organisms.Organism;

public class Guarana extends Plant {

    @Override
    public Plant Spread() {
        return new Guarana(world, GetX(), GetY());
    }

    public Guarana(World ref_world) {
        super(ref_world);
        SetStrength(Defines.PLANT_STRENGTH);
    }

    public Guarana(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.PLANT_STRENGTH);
    }

    @Override
    public char GetSymbol() {
        return Defines.GUARANA_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return new Color(204, 102, 0);
    }

    public int DefenseResult(Organism attacker) {
        attacker.SetStrength(attacker.GetStrength() + Defines.GUARANA_BOOST);
        return Defines.ATTACKER_EATS;
    }

    @Override
    public String GetName() {
        return "Guarana";
    }
}

