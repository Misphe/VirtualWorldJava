package Plants;

import MVVM.World;
import Organisms.Animals.Animal;
import Organisms.Organism;
import Tools.Defines;

import java.awt.*;

public class PineBorscht extends Plant {

    public PineBorscht(World ref_world) {
        super(ref_world);
        SetStrength(Defines.PINEBORSCHT_STRENGTH);
    }

    public PineBorscht(World ref_world, int set_x, int set_y) {
        super(ref_world, set_x, set_y);
        SetStrength(Defines.PINEBORSCHT_STRENGTH);
    }

    @Override
    protected PineBorscht Spread() {
        return new PineBorscht(world, GetX(), GetY());
    }

    @Override
    public void Action() {
        KillAdjacent();
        super.Action();
    }

    @Override
    public int DefenseResult(Organism attacker) {
        return Defines.BOTH_DIED;
    }

    @Override
    public String GetName() {
        return "Pine Borscht";
    }

    @Override
    public char GetSymbol() {
        return Defines.PINEBORSCHT_SYMBOL;
    }

    @Override
    public Color GetColor() {
        return new Color(139,60,0);
    }

    private void KillAdjacent() {
        int x = GetX();
        int y = GetY();
        Point[] variations = { new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1) };
        for (Point slot : variations) {
            Point adjacent = new Point(x + slot.x, y + slot.y);
            if (!InsideWorld(adjacent)) {
                continue;
            }

            Organism victim = world.GetOrganismAtPos(adjacent);
            if (!(victim instanceof Animal)) {
                continue;
            }

            victim.Die();
            world.DeleteOrganismFromSlot(victim);
            world.PushNewLog(world.CreateLog(this, victim, Defines.ATTACKER_WINS));
        }
    }
}

