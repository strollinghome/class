package db61b;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

import java.util.ArrayList;

/** Tests the Row class.
 * @author Carlos A. Flores-Arellano.*/

public class TestRow {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /** Tests basic functionalities, size and get.*/
    @Test
    public void testBasics() {
        String[] datum0 = new String[]{"c0", "c1", "c2", "c3", "c4", "c5"};
        Row r = new Row(datum0);
        assertEquals(6, r.size());
        assertEquals(r.get(4), "c4");
        assertEquals(r.get(0), "c0");
    }

    /** Test the .equals() method.*/
    @Test
    public void testEquals() {
        String[] datum0 = new String[]{"c0", "c1", "c2"};
        String[] datum1 = new String[]{"c0", "c1", "c2"};

        String[] datum2 = new String[]{"c0"};

        Row r0 = new Row(datum0);
        Row r1 = new Row(datum1);
        Row r2 = new Row(datum2);
        assertTrue(r0.equals(r1));
        assertFalse(r0.equals(r2));
    }

    /** Test correct input goes into .equals().*/
    @Test
    public void testExpectsDBException() {
        String[] datum0 = new String[0];
        Row r0 = new Row(datum0);
        thrown.expect(DBException.class);
        r0.equals(new String[9]);
    }

    /** Tests the toString method.*/
    @Test
    public void testToString() {
        String[] datum0 = new String[]{"c0", "c1", "c2"};

        Row row0 = new Row(datum0);

        assertEquals(row0.toString(), "c0,c1,c2");

        Row row1 = new Row(new String[0]);
        assertEquals(row1.toString(), "");
    }

    /** Tests constructor with column list and rows as argument.*/
    @Test
    public void testRowConstructor() {
        String[] datum0 = new String[]{"SID", "Name", "Major"};
        String[] datum1 = new String[]{"23354460", "Carlos", "CS"};
        String[] datum2 = new String[]{"23354460", "CS"};
        Table t0 = new Table(datum0);
        Row r0 = new Row(datum1);
        t0.add(r0);
        Column c0 = new Column("SID", t0);
        Column c1 = new Column("Major", t0);
        ArrayList<Column> columnList = new ArrayList<>();
        columnList.add(c0);
        columnList.add(c1);

        Row r1 = new Row(columnList, r0);
        Row r2 = new Row(datum2);
        assertEquals(r1, r2);

    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(TestRow.class));
    }
}
