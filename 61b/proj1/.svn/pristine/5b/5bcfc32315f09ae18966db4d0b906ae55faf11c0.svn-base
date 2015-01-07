package db61b;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/** Tests the Condition class.
 * @author Carlos A. Flores-Arellano.*/

public class TestCondition {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /** Tests the method test.*/
    @Test
    public void testConditionTest() {
        String[] datum0 = new String[]{"SID", "Name", "Major"};
        String[] datum1 = new String[]{"2335446", "Carlos", "Computer Science"};
        String[] datum2 = new String[]{"1010101", "Bits", "EECS"};
        String[] datum3 = new String[]{"0000001", "Kerr", "English"};

        Table t0 = new Table(datum0);
        Row r0 = new Row(datum1);
        Row r1 = new Row(datum2);
        Row r2 = new Row(datum3);
        t0.add(r0);
        t0.add(r1);
        t0.add(r2);

        Column c0 = new Column("SID", t0);
        Column c1 = new Column("Name", t0);
        Column c2 = new Column("Major", t0);

        Condition cond0 = new Condition(c0, "=", "1010101");

        assertFalse(cond0.test(r0));
        assertTrue(cond0.test(r1));
        assertFalse(cond0.test(r2));

        cond0 = new Condition(c0, "!=", c2);

        assertTrue(cond0.test(r0));
        assertTrue(cond0.test(r1));
        assertTrue(cond0.test(r2));

        Condition cond1 = new Condition(c1, "<", "David");

        assertTrue(cond1.test(r0));
        assertTrue(cond1.test(r1));
        assertFalse(cond1.test(r2));

        cond1 = new Condition(c2, "<=", "EECS");

        assertTrue(cond1.test(r0));
        assertTrue(cond1.test(r1));
        assertFalse(cond1.test(r2));

        Condition cond2 = new Condition(c0, ">", "10101010");

        assertTrue(cond2.test(r0));
        assertFalse(cond2.test(r1));
        assertFalse(cond2.test(r2));

        cond2 = new Condition(c1, ">=", c2);

        assertFalse(cond2.test(r0));
        assertFalse(cond2.test(r1));
        assertTrue(cond2.test(r2));
    }

    /** Test the static test() method.*/
    @Test
    public void testListTest() {
        String[] datum0 = new String[]{"SID", "Name", "Major"};
        String[] datum1 = new String[]{"23354460", "Carlos", "CS"};
        String[] datum2 = new String[]{"10101010", "Bits", "EECS"};
        String[] datum3 = new String[]{"00000001", "Kerr", "English"};

        Table t0 = new Table(datum0);
        Row r0 = new Row(datum1);
        Row r1 = new Row(datum2);
        Row r2 = new Row(datum3);
        t0.add(r0);
        t0.add(r1);
        t0.add(r2);

        Column c0 = new Column("SID", t0);
        Column c1 = new Column("Name", t0);
        Column c2 = new Column("Major", t0);

        Condition cond0 = new Condition(c0, ">=", "1");
        Condition cond1 = new Condition(c1, ">", "Albert");
        Condition cond2 = new Condition(c2, "!=", "PACS");
        ArrayList<Condition> condList0 = new ArrayList<>();
        condList0.add(cond0);
        condList0.add(cond1);
        condList0.add(cond2);

        assertTrue(Condition.test(condList0, new Row[]{r0, r1, r2}));

        Condition cond3 = new Condition(c0, "=", "00000002");
        Condition cond4 = new Condition(c1, ">", "Zid");
        Condition cond5 = new Condition(c2, "<", "Alchemy");
        ArrayList<Condition> condList1 = new ArrayList<>();
        condList1.add(cond3);
        condList1.add(cond4);
        condList1.add(cond5);

        assertFalse(Condition.test(condList1, new Row[]{r0, r1, r2}));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(TestCondition.class));
    }
}
