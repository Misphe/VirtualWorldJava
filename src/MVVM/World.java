package MVVM;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.util.List;

import Organisms.*;
import Organisms.Animals.*;
import Plants.*;
import Tools.Defines;
import javafx.util.Pair;

public class World implements IWorld {
    private int rows;
    private int columns;
    private List<Organism> organisms;
    private Organism[][] organisms_slots;
    // private Human player;
    private String[] logs;

    private Human player;

    public World(int columns, int rows) {
        this.columns = columns;
        this.rows = rows;
        this.logs = new String[Defines.LOG_LENGTH];
        organisms_slots = new Organism[columns][rows];
        organisms = new ArrayList<>();
        AddNewOrganism(new Human(this));
        if(organisms.get(0) instanceof Human) {
            player = (Human) organisms.get(0);
        }


        AddNewOrganism(new Grass(this));
        AddNewOrganism(new Grass(this));

        AddNewOrganism(new Dandelion(this));
        AddNewOrganism(new Dandelion(this));

        AddNewOrganism(new Guarana(this));
        AddNewOrganism(new Guarana(this));

        AddNewOrganism(new PineBorscht(this));
        AddNewOrganism(new PineBorscht(this));

        AddNewOrganism(new WolfBerries(this));
        AddNewOrganism(new WolfBerries(this));

        AddNewOrganism(new Sheep(this));
        AddNewOrganism(new Sheep(this));


        AddNewOrganism(new Antelope(this));
        AddNewOrganism(new Antelope(this));

        AddNewOrganism(new Fox(this));
        AddNewOrganism(new Fox(this));

        AddNewOrganism(new Turtle(this));
        AddNewOrganism(new Turtle(this));

        AddNewOrganism(new Wolf(this));
        AddNewOrganism(new Wolf(this));

    }

    public void AddNewOrganism(Organism organism) {
        organisms.add(organism);
        organisms_slots[organism.GetX()][organism.GetY()] = organism;
        PushNewLog(CreateBreedLog(organism));
    }

    @Override
    public Pair<Integer, Integer> GetSize() {
        return new Pair(columns, rows);
    }

    @Override
    public Organism[][] GetOrganismsTable() {
        return organisms_slots;
    }

    @Override
    public void SetPlayerMove(int pressedKey) {
        if(player != null) {
            player.SetMove(pressedKey);
        }
    }

    private void SortOrganisms() {
        Collections.sort(organisms, (first, second) -> {
            if (first.GetInitiative() != second.GetInitiative()) {
                return Integer.compare(second.GetInitiative(), first.GetInitiative());
            } else {
                return Integer.compare(second.GetAliveTime(), first.GetAliveTime());
            }
        });
    }

    @Override
    public void ExecuteTurn() {
        int size = organisms.size();
        for(int i = 0; i < size; i++) {
            if(organisms.get(i).IsAlive()) {
                organisms.get(i).Action();
            }
        }
        DeleteDead();

        for(Organism organism : organisms){
            if (organism == null){
                System.out.println("jest null");
            }
        }
        SortOrganisms();
    }

    private void DeleteDead() {
        Iterator<Organism> iterator = organisms.iterator();
        while (iterator.hasNext()) {
            Organism organism = iterator.next();
            if (!organism.IsAlive()) {
                iterator.remove();
            }
        }
    }


    private void SetPlayer() {
        for (Organism organism : organisms) {
            if (organism instanceof Human) {
                player = (Human) organism;
                return;
            }
        }
        player = null;
    }


    private void Reset() {
        AbortPlayer();
    }

    public void SaveState() {
        try (PrintWriter file = new PrintWriter("save.txt")) {
            file.println("size_x: " + GetColumns());
            file.println("size_y: " + GetRows());
            file.println("organisms_size: " + organisms.size());
            file.println("player_alive: " + (player != null ? 1 : 0));
            if (player != null) {
                file.println("cooldown: " + player.GetCooldown());
                file.println("power: " + (player.PowerActivated() ? 1 : 0));
            }
            for (Organism organism : organisms) {
                file.print(organism.GetSymbol() + " ");
                if (organism instanceof Animal) {
                    file.print("Animal ");
                    file.print(((Animal) organism).GetPrevPosition().x + " ");
                    file.print(((Animal) organism).GetPrevPosition().y + " ");
                } else {
                    file.print("Plant ");
                }
                file.print(organism.GetStrength() + " ");
                file.print(organism.GetInitiative() + " ");
                file.print(organism.GetAliveTime() + " ");
                file.print(organism.GetX() + " ");
                file.println(organism.GetY());
            }

            for (String log : logs) {
                if (log.isEmpty()) break;
                file.println(log);
            }
            file.println('/');
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void LoadState() {
        try (Scanner file = new Scanner(new File("save.txt"))) {
            Reset();

            int size_x, size_y, organisms_size, cooldown = 0, power = 0;
            boolean player_alive = false;
            char symbol;
            String line, title, organism_type;

            title = file.next();
            size_x = file.nextInt();
            title = file.next();
            size_y = file.nextInt();
            title = file.next();
            organisms_size = file.nextInt();
            title = file.next();
            player_alive = (file.nextInt() == 1);
            if (player_alive) {
                title = file.next();
                cooldown = file.nextInt();
                title = file.next();
                power = file.nextInt();
            }

            SetSize(size_x, size_y);

            int prev_x, prev_y, strength, initiative, alive_time, x, y;

            for (int i = 0; i < organisms_size; i++) {
                symbol = file.next().charAt(0);
                organism_type = file.next();
                if (organism_type.equals("Animal")) {
                    prev_x = file.nextInt();
                    prev_y = file.nextInt();
                } else {
                    prev_x = prev_y = 0;
                }

                strength = file.nextInt();
                initiative = file.nextInt();
                alive_time = file.nextInt();
                x = file.nextInt();
                y = file.nextInt();

                Organism organism = CreateOrganismFromFile(symbol, x, y);
                organism.SetStrength(strength);
                organism.SetAliveTime(alive_time);

                if (organism instanceof Animal) {
                    Animal animal = (Animal) organism;
                    animal.SetPrevPosition(prev_x, prev_y);
                }

                AddNewOrganism(organism);
            }

            for (int i = 0; i < Defines.LOG_LENGTH; i++) {
                line = file.nextLine().trim();
                if (line.isEmpty()) {
                    i--;
                    continue;
                }
                if (!line.equals("/")) {
                    SetLog(i, line);
                }
            }

            SetPlayer();
            if (player != null) {
                player.SetCooldown(cooldown);
                player.SetPowerState((power == 1));
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void SetSize(int sizeX, int sizeY) {
        this.columns = sizeX;
        this.rows = sizeY;
        organisms_slots = new Organism[columns][rows];
        logs = new String[Defines.LOG_LENGTH];
        organisms = new ArrayList<>();
    }


    public void AbortPlayer() {
        player = null;
    }

    public void ActivatePlayerPower() {
        if(player.GetCooldown() == 0) {
            player.SetCooldown(Defines.MAX_COOLDOWN);
            player.FlickPowerState();
        }
    }

    public int GetColumns() {
        return columns;
    }

    public int GetRows() {
        return rows;
    }

    public boolean IsEmpty(Point position) {
        if(organisms_slots[position.x][position.y] == null) {
            return true;
        }
        return false;
    }

    public Organism CheckForCollision(Organism current) {
        return organisms_slots[current.GetX()][current.GetY()];
    }

    public Organism GetOrganismAtPos(Point spot) {
        return organisms_slots[spot.x][spot.y];
    }

    public void SetOrganismToSlot(Organism organism) {
        organisms_slots[organism.GetX()][organism.GetY()] = organism;
    }

    public void DeleteOrganismFromSlot(Organism organism) {
        organisms_slots[organism.GetX()][organism.GetY()] = null;
    }

    public void UpdateAnimalSlot(Animal organism) {
        organisms_slots[organism.GetPrevPosition().x][organism.GetPrevPosition().y] = null;
        if(organism.IsAlive()) {
            organisms_slots[organism.GetX()][organism.GetY()] = organism;
        }
    }


    public void PushNewLog(String new_log) {
        for (int i = logs.length - 1; i > 0; i--) {
            logs[i] = logs[i - 1];
        }

        logs[0] = new_log;
    }

    public String CreateLog(Organism attacker, Organism defender, int message) {
        String attackerInfo = attacker.GetName() + "(" + attacker.GetX() + "," + attacker.GetY() + ")";
        String defenderInfo = defender.GetName() + "(" + defender.GetX() + "," + defender.GetY() + ")";
        switch (message) {
            case Defines.ATTACKER_WINS:
                return attackerInfo + " killed " + defenderInfo;
            case Defines.DEFENDER_WINS:
                return defenderInfo + " killed " + attackerInfo;
            case Defines.DEFENDER_RUNS_AWAY:
                return defenderInfo + " ran away from " + attackerInfo;
            case Defines.ATTACKER_RETREATS:
                return attackerInfo + " stepped back from " + defenderInfo;
            case Defines.BOTH_DIED:
                return attackerInfo + " and " + defenderInfo + " died";
            case Defines.ATTACKER_EATS:
                return attackerInfo + " eats " + defenderInfo;
            default:
                return "";
        }
    }


    public String CreateBreedLog(Organism new_organism) {
        return "New " + new_organism.GetName() + " was born at (" + new_organism.GetX() + "," + new_organism.GetY() + ")";
    }

    public void SetLog(int i, String set_log) {
        logs[i] = set_log;
    }

    public Organism CreateOrganismFromFile(char symbol, int x, int y) {
        switch (symbol) {
            case Defines.HUMAN_SYMBOL:
                return new Human(this, x, y);
            case Defines.WOLF_SYMBOL:
                return new Wolf(this, x, y);
            case Defines.SHEEP_SYMBOL:
                return new Sheep(this, x, y);
            case Defines.FOX_SYMBOL:
                return new Fox(this, x, y);
            case Defines.ANTELOPE_SYMBOL:
                return new Antelope(this, x, y);
            case Defines.TURTLE_SYMBOL:
                return new Turtle(this, x, y);
            case Defines.GRASS_SYMBOL:
                return new Grass(this, x, y);
            case Defines.DANDELION_SYMBOL:
                return new Dandelion(this, x, y);
            case Defines.GUARANA_SYMBOL:
                return new Guarana(this, x, y);
            case Defines.WOLFBERRIES_SYMBOL:
                return new WolfBerries(this, x, y);
            case Defines.PINEBORSCHT_SYMBOL:
                return new PineBorscht(this, x, y);
            default:
                return null;
        }
    }


    public String[] GetEvents() {
        return logs;
    }
}

