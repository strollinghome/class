package db61b;

import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Rule;
import org.junit.rules.ExpectedException;

/** Tests the Database class.
 * @author Carlos A. Flores-Arellano.*/

public class TestDatabase {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

    /** Tests all functionalities.*/
    @Test
    public void testAllMethods() {
        String[] datum0 = new String[]{
            "SID", "Lastname", "Firstname", "SemEnter", "YearEnter", "Major"};
        String[] datum1 = new String[]{
            "23354460", "Flores", "Carlos", "Spring", "2013", "CS"};

        Row row0 = new Row(datum1);
        Table t0 = new Table(datum0);
        t0.add(row0);
        Database db0 = new Database();

        assertNull(db0.get("something"));

        db0.put("BasicInfo", t0);
        Table t1 = db0.get("BasicInfo");

        int k = 0;
        while (k < t1.size()) {
            assertEquals(t0.getTitle(k), t1.getTitle(k));
            k = k + 1;
        }
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(TestDatabase.class));
    }
}
