package creatures;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.HashMap;
import java.awt.Color;
import huglife.Direction;
import huglife.Action;
import huglife.Action.ActionType;
import huglife.Occupant;
import huglife.Impassible;
import huglife.Empty;

/** Tests the clorus class.
 * @author Carlos Flores.*/

public class TestClorus { 

    @Test
    public void testBasics() {
        Clorus c = new Clorus(0.5);
        assertEquals(0.5, c.energy(), 0.01);
        assertEquals(new Color(34, 0, 231), c.color());
        c.move();
        assertEquals(0.47, c.energy(), 0.01);
        c.stay();
        assertEquals(0.46, c.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Clorus c = new Clorus(2);
        Clorus babyC = c.replicate();
        assertEquals(1, c.energy(), 0.01);
        assertEquals(1, babyC.energy(), 0.01);
    }

    @Test
    public void testChoose() {
        Clorus c = new Clorus(1);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Plip());

        Action actual0 = c.chooseAction(surrounded);
        Action expected0 = new Action(Action.ActionType.STAY);
        assertEquals(expected0, actual0);
        
        HashMap<Direction, Occupant> somePlips = new HashMap<>();
        somePlips.put(Direction.TOP, new Empty());
        somePlips.put(Direction.BOTTOM, new Empty());
        somePlips.put(Direction.LEFT, new Plip());
        somePlips.put(Direction.RIGHT, new Plip());

        Action action0 = c.chooseAction(somePlips);
        ActionType expected1 = Action.ActionType.ATTACK;
        ActionType actual1 = action0.type;
        assertEquals(actual1, expected1);
    }

    public static void main (String[] args) {
        System.exit(ucb.junit.textui.runClasses(TestClorus.class));
    }
}
