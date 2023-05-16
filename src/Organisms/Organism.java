package Organisms;

import MVP.World;
import Tools.Defines;

import java.awt.*;
import java.util.Random;

public abstract class Organism {
    protected final World world;
    protected Point position;
    protected int strength;
    protected int initiative;
    protected int aliveTime;
    protected boolean alive;

    public Organism(World ref_world) {
        world = ref_world;
        aliveTime = 1;
        position = new Point();
        int set_x = new Random().nextInt(world.GetColumns());
        int set_y = new Random().nextInt(world.GetRows());
        while (!world.IsEmpty(new Point(set_x, set_y))) {
            set_x = new Random().nextInt(world.GetColumns());
            set_y = new Random().nextInt(world.GetRows());
        }
        SetX(set_x);
        SetY(set_y);
        alive = true;
    }

    public Organism(World ref_world, int set_x, int set_y) {
        world = ref_world;
        aliveTime = 1;
        position = new Point(set_x, set_y);
        alive = true;
    }

    public abstract void Action();
    public abstract int DefenseResult(Organism attacker);
    public abstract char GetSymbol();
    public abstract Color GetColor();
    public abstract String GetName();

    public void SetX(int new_x) {
        if (new_x < world.GetColumns() && new_x >= 0) {
            position.x = new_x;
        }
    }

    public void SetY(int new_y) {
        if (new_y < world.GetRows() && new_y >= 0) {
            position.y = new_y;
        }
    }

    public void SetStrength(int new_strength) {
        strength = new_strength;
    }

    public void SetInitiative(int new_initiative) {
        initiative = new_initiative;
    }

    public void SetAliveTime(int time) {
        aliveTime = time;
    }

    public void SetPosition(Point new_position) {
        position = (Point) new_position.clone();
    }

    public void MoveX(int change) {
        SetX(GetX() + change);
    }

    public void MoveY(int change) {
        SetY(GetY() + change);
    }

    public int GetX() {
        return position.x;
    }

    public int GetY() {
        return position.y;
    }

    public int GetStrength() {
        return strength;
    }

    public int GetInitiative() {
        return initiative;
    }

    public int GetAliveTime() {
        return aliveTime;
    }

    public Point GetPosition() {
        return position;
    }

    public Point GetEmptyAdjacent() {
        int x = GetX();
        int y = GetY();
        Point[] adjacent = { new Point(1, 0), new Point(-1, 0), new Point(0, 1), new Point(0, -1) };
        boolean[] tested = { false, false, false, false };
        int random;
        Point tmp;
        for (int i = 0; i < 4; i++) {
            while (true) {
                random = new Random().nextInt(4);
                if (!tested[random]) {
                    tested[random] = true;
                    tmp = new Point(x + adjacent[random].x, y + adjacent[random].y);
                    if (InsideWorld(tmp) && world.IsEmpty(tmp)) {
                        return tmp;
                    }
                    break;
                }
            }
        }
        return new Point(Defines.NOT_IN_PLAY, Defines.NOT_IN_PLAY);
    }

    public void Die() {
        alive = false;
    }

    public boolean IsAlive() {
        return alive;
    }

    public boolean InsideWorld(Point point) {
        return (point.getX() >= 0 && point.getX() < world.GetColumns()) && (point.getY() >= 0 && point.getY() < world.GetRows());
    }
}
