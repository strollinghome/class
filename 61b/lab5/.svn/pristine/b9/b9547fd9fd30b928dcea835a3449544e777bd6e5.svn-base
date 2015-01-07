package creatures;
import huglife.Creature;
import huglife.Direction;
import huglife.Action;
import huglife.Occupant;
import huglife.Lab5Utils;
import java.awt.Color;
import java.util.Map;
import java.util.List;

/** An implementation of the clorus creature.
 * @author Carlos Flores.*/
public class Clorus extends Creature {

    /** Red color.*/
    private int r;

    /** Green color.*/
    private int g;

    /** Blue color.*/
    private int b;

    /** Creates a clorus with energy equal to E.*/
    public Clorus(double e) {
        super("clorus");
        r = 0;
        g = 0;
        b = 0;
        energy = e;
    }

    /** Returns a color with red = 34, green = 0, and blue = 231.*/
    public Color color() {
        r = 34;
        g = 0;
        b = 231;
        return color(r, g, b);
    }
    
    /** When attacking, clorus gains its prey's(C) energy.*/
    public void attack(Creature c) {
        energy = energy + c.energy();
    }

    /** Clorus' lose 0.03 of energy when moving.*/
    public void move() {
        energy = energy - 0.03;
    }

    /** Clorus' lose 0.01 of energy when staying.*/
    public void stay() {
        energy = energy - 0.01;
    }

    /** Clorus gives half of its energy to its offspring.*/
    public Clorus replicate() {
        double babyEnergy = energy * repEnergyRetained;
        energy = energy * repEnergyGiven;
        return new Clorus(babyEnergy);
    }

    /** A clorus follows this rules when choosing an action:
     * 1. If there are no empty spaces then it will STAY.
     * 2. Otherwise, if Plips are seen it will attack one of them
     * randomly.
     * 3. Otherwise, if it has energy greater than or equal to 1, it
     * will REPLICATE to a random empty space.
     * 4. Otherwise, the Clorus will move*/
    public Action chooseAction(Map<Direction, Occupant> neighbors) {
        List<Direction> empties = getNeighborsOfType(neighbors, "empty");
        List<Direction> plips = getNeighborsOfType(neighbors, "plip");
        if (empties.size() == 0) {
            return new Action(Action.ActionType.STAY);
        } else if (plips.size() > 0) {
            Direction moveDir = Lab5Utils.randomEntry(plips);
            return new Action(Action.ActionType.ATTACK, moveDir);
        } else if (energy >= 1) {
            Direction moveDir = Lab5Utils.randomEntry(empties);
            return new Action(Action.ActionType.REPLICATE, moveDir);
        }
        Direction moveDir = Lab5Utils.randomEntry(empties);
        return new Action(Action.ActionType.MOVE, moveDir);
    }

    /** Fraction of energy given to an offspring*/
    private double repEnergyGiven = 0.5;

    /** Fraction of energy retained when replicating.*/
    private double repEnergyRetained = 0.5;
}
