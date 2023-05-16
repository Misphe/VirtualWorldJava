package Organisms.Animals;

import MVP.World;
import Organisms.Organism;
import Tools.*;

import java.awt.*;

public class Fox extends Animal {

    public void Action() {
        // unlinking
        world.DeleteOrganismFromSlot(this);
        UpdatePrevPosition();
        aliveTime++;

        // smart type of movement -> Fox never goes where he loses
        FoxMove();
        Collision();

        if(this.IsAlive()) {
            world.SetOrganismToSlot(this);
        }
    }

    public Fox(World ref_world) {
        super(ref_world);
        SetStrength(Defines.FOX_STRENGTH);
        SetInitiative(Defines.FOX_INITIATIVE);
    }

    public Fox(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.FOX_STRENGTH);
        SetInitiative(Defines.FOX_INITIATIVE);
    }

    @Override
    public Fox Breed() {
        return new Fox(world, GetX(), GetY());
    }

    public void FoxMove() {
        Point[] adjacent = { new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1) };
        boolean[] tested = { false, false, false, false };
        int random;
        Point tmp;
        for (int i = 0; i < 4; i++) {
            while (true) {
                random = (int) (Math.random() * 4);
                if (!tested[random]) {
                    tested[random] = true;
                    tmp = new Point(GetX() + adjacent[random].x, GetY() + adjacent[random].y);
                    if (InsideWorld(tmp) && FoxSafeAtSpot(tmp)) {
                        SetPosition(tmp);
                        return;
                    }
                    break;
                }
            }
        }
    }

    public boolean FoxSafeAtSpot(Point spot) {
        Organism adjacent = world.GetOrganismAtPos(spot);
        if (adjacent == null) {
            return true;
        }
        if (adjacent instanceof Fox) {
            return true;
        }
        if (GetStrength() > adjacent.GetStrength()) {
            return true;
        } else if (GetStrength() < adjacent.GetStrength()) {
            return false;
        } else if (GetAliveTime() > adjacent.GetAliveTime()) {
            return true;
        } else {
            return true;
        }
    }

    public char GetSymbol() {
        return Defines.FOX_SYMBOL;
    }

    public String GetName() {
        return "Fox";
    }

    public Color GetColor() {
        return Color.RED;
    }
}
