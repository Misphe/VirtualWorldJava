package Organisms.Animals;

import MVVM.World;
import Organisms.Organism;
import Tools.*;

import java.awt.*;

public class Turtle extends Animal {

    @Override
    public void Action() {

        // Unlinking
        world.DeleteOrganismFromSlot(this);
        UpdatePrevPosition();

        // If rand() returns 0 -> animal moves (that's 25% chance)
        if (!(Math.random() < 1.0 / Defines.TURTLE_MOVE_CHANCE)) {
            super.Action();
            return;
        }

        if(this.IsAlive()){
            world.SetOrganismToSlot(this);
        }
        aliveTime++;
    }

    @Override
    public int DefenseResult(Organism attacker) {
        if (attacker.GetStrength() <= Defines.MIN_ATTACK_TO_KILL) {
            return Defines.ATTACKER_RETREATS;
        } else {
            return super.DefenseResult(attacker);
        }
    }

    @Override
    public char GetSymbol() {
        return Defines.TURTLE_SYMBOL;
    }

    @Override
    public String GetName() {
        return "Turtle";
    }

    @Override
    public Color GetColor() {
        return new Color(0,100,0);
    }

    public Turtle(World ref_world) {
        super(ref_world);
        SetStrength(Defines.TURTLE_STRENGTH);
        SetInitiative(Defines.TURTLE_INITIATIVE);
    }

    public Turtle(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.TURTLE_STRENGTH);
        SetInitiative(Defines.TURTLE_INITIATIVE);
    }

    public Animal Breed() {
        return new Turtle(world, GetX(), GetY());
    }
}
