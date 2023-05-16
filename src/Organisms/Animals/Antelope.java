package Organisms.Animals;

import MVP.World;
import Organisms.Organism;
import Tools.Defines;

import java.awt.*;
import java.util.Random;

public class Antelope extends Animal {

    public Antelope(World ref_world) {
        super(ref_world);
        SetStrength(Defines.ANTELOPE_STRENGTH);
        SetInitiative(Defines.ANTELOPE_INITIATIVE);
    }

    public Antelope(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.ANTELOPE_STRENGTH);
        SetInitiative(Defines.ANTELOPE_INITIATIVE);
    }

    @Override
    public void Action() {
        world.DeleteOrganismFromSlot(this);
        UpdatePrevPosition();
        aliveTime++;

        Random random = new Random();
        do {
            if (random.nextInt(2) == 0) {
                if (random.nextInt(2) == 0) {
                    this.MoveX(Defines.ANTELOPE_JUMP);
                } else {
                    this.MoveX(-Defines.ANTELOPE_JUMP);
                }
            } else {
                if (random.nextInt(2) == 0) {
                    this.MoveY(Defines.ANTELOPE_JUMP);
                } else {
                    this.MoveY(-Defines.ANTELOPE_JUMP);
                }
            }
        } while (GetPosition().equals(GetPrevPosition()));

        Collision();

        if(this.IsAlive()) {
            world.SetOrganismToSlot(this);
        }
    }

    @Override
    public void Collision() {
        Organism defender = world.CheckForCollision(this);
        if (defender != null) {
            if (defender.getClass().equals(this.getClass())) {
                GoBack();
                Animal new_animal = Breed();
                if (new_animal.SetChildsPosition(this.GetPosition(), defender.GetPosition())) {
                    world.PushNewLog(world.CreateBreedLog(new_animal));
                    world.AddNewOrganism(new_animal);
                }
                return;
            }
            if (AntelopeEscaped()) {
                return;
            }
            super.Collision();
        }

    }

    @Override
    public int DefenseResult(Organism attacker) {
        UpdatePrevPosition();
        if (AntelopeEscaped()) {
            world.UpdateAnimalSlot(this);
            return Defines.DEFENDER_RUNS_AWAY;
        }
        return super.DefenseResult(attacker);
    }

    @Override
    public Animal Breed() {
        return new Antelope(world, GetX(), GetY());
    }

    private boolean AntelopeEscaped() {
        Random random = new Random();
        if (random.nextInt(2) == 0) {
            return false;
        }
        Point tmp = GetEmptyAdjacent();
        if (tmp.x != Defines.NO_EMPTY_ADJACENT) {
            SetPosition(tmp);
            return true;
        }
        return false;
    }

    @Override
    public char GetSymbol() {
        return Defines.ANTELOPE_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return new Color(207, 85, 135);
    }

    @Override
    public String GetName() {
        return "Antelope";
    }
}
