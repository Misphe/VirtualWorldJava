package Organisms.Animals;

import MVVM.World;
import Organisms.Organism;
import Tools.*;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Human extends Animal {
    private char move;
    private boolean powerActivated;
    private int cooldown;

    @Override
    public void Action() {
        aliveTime++;

        // unlinking ( see Animal::Action() )
        world.DeleteOrganismFromSlot(this);
        UpdatePrevPosition();
        int boost = CurrentBoost();

        switch (move) {
            case KeyEvent.VK_UP:
                MoveY(-1 - boost);
                break;

            case KeyEvent.VK_DOWN:
                MoveY(1 + boost);
                break;

            case KeyEvent.VK_LEFT:
                MoveX(-1 - boost);
                break;

            case KeyEvent.VK_RIGHT:
                MoveX(1 + boost);
                break;
        }
        // collision

        Collision();

        if(this.IsAlive()) {
            world.SetOrganismToSlot(this);
        }
    }

    protected void Collision() {
        // depends on his power and whether it's activated or not
        if (!powerActivated) {
            super.Collision();
        } else {
            super.Collision();
        }
        DecrementCooldown();
    }

    public Human(World refWorld) {
        super(refWorld);
        SetStrength(Defines.HUMAN_STRENGTH);
        SetInitiative(Defines.HUMAN_INITIATIVE);
        cooldown = 0;
        move = 0;
    }

    public Human(World refWorld, int x, int y) {
        super(refWorld, x, y);
        SetStrength(Defines.HUMAN_STRENGTH);
        SetInitiative(Defines.HUMAN_INITIATIVE);
        cooldown = 0;
        move = '\0';
    }

    public Human Breed() {
        return new Human(world);
    }

    public int DefenseResult(Organism attacker) {
        return super.DefenseResult(attacker);
    }

    public void SetMove(int input) {
        move = (char) input;
    }

    public void FlickPowerState() {
        powerActivated = !powerActivated;
    }

    public char GetSymbol() {
        return Defines.HUMAN_SYMBOL;
    }

    public boolean PowerActivated() {
        return powerActivated;
    }

    public int GetCooldown() {
        return cooldown;
    }

    public String GetName() {
        return "HUMAN";
    }

    public Color GetColor() {
        return Color.BLACK;
    }

    public void SetCooldown() {
        cooldown = Defines.MAX_COOLDOWN;
    }

    public void SetCooldown(int setCooldown) {
        cooldown = setCooldown;
    }

    public void SetPowerState(boolean state) {
        powerActivated = state;
    }

    public void DecrementCooldown() {
        if (cooldown > 0) {
            cooldown--;
        }
        if (cooldown == Defines.END_OF_POWER) {
            FlickPowerState();
        }
    }

    public int CurrentBoost() {
        if (!PowerActivated()) {
            return 0;
        }

        if (GetCooldown() > Defines.POWER_WORSE_STATE) {
            return 1;
        } else if (GetCooldown() > Defines.END_OF_POWER) {
            return (int) (Math.random() * 2);
        } else {
            return 0;
        }
    }

}