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

/** Tests the plip class   
 *  @author Carlos Flores.
 */

public class TestPlip {

    @Test
    public void testBasics() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        assertEquals(new Color(99, 255, 76), p.color());
        p.move();
        assertEquals(1.85, p.energy(), 0.01);
        p.move();
        assertEquals(1.70, p.energy(), 0.01);
        p.stay();
        assertEquals(1.90, p.energy(), 0.01);
        p.stay();
        assertEquals(2.00, p.energy(), 0.01);
    }

    @Test
    public void testReplicate() {
        Plip p = new Plip(2);
        assertEquals(2, p.energy(), 0.01);
        Plip babyP = p.replicate();
        assertEquals(p.energy() * 0.5, babyP.energy(), 0.01);
        assertEquals(2, p.energy(), 0.01); 
        assertNotSame(babyP, p);
    }

    @Test
    public void testChoose() {
        Plip p = new Plip(1.2);
        HashMap<Direction, Occupant> surrounded = new HashMap<>();
        surrounded.put(Direction.TOP, new Impassible());
        surrounded.put(Direction.BOTTOM, new Impassible());
        surrounded.put(Direction.LEFT, new Impassible());
        surrounded.put(Direction.RIGHT, new Impassible());

        //You can create new empties with new Empty();
        //Despite what the spec says, you cannot test for Cloruses nearby yet.
        //Sorry!  

        Action actual = p.chooseAction(surrounded);
        Action expected = new Action(Action.ActionType.STAY);

        assertEquals(expected, actual);

        Plip p0 = new Plip(2);
        HashMap<Direction, Occupant> around = new HashMap<>();
        around.put(Direction.TOP, new Impassible());
        around.put(Direction.BOTTOM, new Empty());
        around.put(Direction.LEFT, new Empty());
        around.put(Direction.RIGHT, new Empty());

        Action action0 = p0.chooseAction(around);
        ActionType expected0 = Action.ActionType.REPLICATE;
        ActionType actual0 = action0.type;
        
        assertEquals(actual0, expected0);
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(TestPlip.class));
    }
} 
