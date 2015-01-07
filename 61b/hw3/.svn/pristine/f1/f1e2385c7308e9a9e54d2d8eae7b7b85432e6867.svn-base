import org.junit.Test;
import static org.junit.Assert.*;

/** WeirdList class tests.
 *  @author Josh Hug
 */

public class WeirdListTest {
    /** Tests the WeirdList class
     */

    public static void main(String[] args) {
        WeirdList wl1 = new WeirdList(5, WeirdList.EMPTY);
        WeirdList wl2 = new WeirdList(6, wl1);
        WeirdList wl3 = new WeirdList(10, wl2);
        assertEquals(wl3.length(), 3);
        assertEquals(wl1.length(), 1);

        wl3.print();

    }
}
