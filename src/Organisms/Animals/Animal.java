package Organisms.Animals;

import MVP.World;

import java.awt.*;

import Organisms.Organism;
import Tools.*;
import java.util.Random;


public abstract class Animal extends Organism {
    Point prevPosition;
    public Animal(World world, int x, int y) {
        super(world, x, y);
        this.prevPosition = new Point(x, y);
    }

    public Animal(World world) {
        super(world);
        this.prevPosition = new Point();
    }

    public void Action() {

        world.DeleteOrganismFromSlot(this);
        UpdatePrevPosition();
        aliveTime++;
        RandomMove();

        Collision();

        if(this.IsAlive()) {
            world.SetOrganismToSlot(this);
        }
    }

    public abstract Animal Breed();


    protected void Collision() {
        Organism defender = world.CheckForCollision(this);
        if (defender != null) {
            if (!defender.IsAlive()) return;

            if (defender.getClass() == this.getClass()) {
                GoBack();

                Animal newAnimal = Breed();
                if (newAnimal.SetChildsPosition(this.GetPosition(), defender.GetPosition())) {
                    world.PushNewLog(world.CreateBreedLog(newAnimal));
                    world.AddNewOrganism(newAnimal);
                }
            } else {
                int result = fightResult(defender);
                switch (result) {
                    case Defines.ATTACKER_WINS:
                        defender.Die();
                        world.DeleteOrganismFromSlot(defender);
                        world.PushNewLog(world.CreateLog(this, defender, Defines.ATTACKER_WINS));
                        break;
                    case Defines.DEFENDER_WINS:
                        this.Die();
                        world.PushNewLog(world.CreateLog(this, defender, Defines.DEFENDER_WINS));
                        break;
                    case Defines.ATTACKER_RETREATS:
                        GoBack();
                        world.PushNewLog(world.CreateLog(this, defender, Defines.ATTACKER_RETREATS));
                        break;
                    case Defines.DEFENDER_RUNS_AWAY:
                        world.PushNewLog(world.CreateLog(this, defender, Defines.DEFENDER_RUNS_AWAY));
                        break;
                    case Defines.BOTH_DIED:
                        this.Die();
                        defender.Die();
                        world.DeleteOrganismFromSlot(defender);
                        world.PushNewLog(world.CreateLog(this, defender, Defines.BOTH_DIED));
                        break;
                    case Defines.ATTACKER_EATS:
                        defender.Die();
                        world.DeleteOrganismFromSlot(defender);
                        world.PushNewLog(world.CreateLog(this, defender, Defines.ATTACKER_EATS));
                        break;
                }
            }
        }
    }

    private void RandomMove() {
        do {
            if (Math.random() < 0.5) {
                if (Math.random() < 0.5) {
                    MoveX(1);
                } else {
                    MoveX(-1);
                }
            } else {
                if (Math.random() < 0.5) {
                    MoveY(1);
                } else {
                    MoveY(-1);
                }
            }
        } while (GetPosition().equals(GetPrevPosition()));

    }

    private int fightResult(Organism victim) {
        return victim.DefenseResult(this);
    }

    public int DefenseResult(Organism attacker) {
        if (GetStrength() > attacker.GetStrength()) {
            return Defines.DEFENDER_WINS;
        } else if (GetStrength() < attacker.GetStrength()) {
            return Defines.ATTACKER_WINS;
        } else if (GetAliveTime() > attacker.GetAliveTime()) {
            return Defines.DEFENDER_WINS;
        } else if (GetAliveTime() < attacker.GetAliveTime()) {
            return Defines.ATTACKER_WINS;
        } else {
            return Defines.ATTACKER_WINS;
        }
    }

    public boolean SetChildsPosition(Point parent1Pos, Point parent2Pos) {
        Point[] adjacent = { new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1) };
        boolean[] tested = { false, false, false, false };
        int random;
        Point tmp;

        for (int i = 0; i < 4; i++) {
            while (true) {
                random = new Random().nextInt(4);
                if (!tested[random]) {
                    tested[random] = true;
                    tmp = new Point((int) Math.floor(parent1Pos.getX() + adjacent[random].getX()), (int) Math.floor(parent1Pos.getY() + adjacent[random].getY()));
                    if (InsideWorld((tmp)) && world.IsEmpty(tmp)) {
                        SetPosition(tmp);
                        UpdatePrevPosition();
                        return true;
                    }
                    break;
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            tested[i] = false;
        }

        for (int i = 0; i < 4; i++) {
            while (true) {
                random = new Random().nextInt(4);
                if (!tested[random]) {
                    tested[random] = true;
                    tmp = new Point((int) Math.floor(parent1Pos.getX() + adjacent[random].getX()), (int) Math.floor(parent1Pos.getY() + adjacent[random].getY()));
                    if (InsideWorld(tmp) && world.IsEmpty(tmp)) {
                        SetPosition(tmp);
                        UpdatePrevPosition();
                        return true;
                    }
                    break;
                }
            }
        }

        return false;
    }

    public Point GetPrevPosition() {
        return prevPosition;
    }

    protected void UpdatePrevPosition() {
        prevPosition = (Point) position.clone();
    }

    public void GoBack() {
        SetPosition(prevPosition);
        world.SetOrganismToSlot(this);
    }

    public void SetPrevPosition(int x, int y) {
        prevPosition.x = x;
        prevPosition.y = y;
    }
}
