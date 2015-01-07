import org.junit.Test;
import static org.junit.Assert.*;

/** Test the Nybbles class.
 * @author Carlos A. Flores-Arellano.*/
public class TestNybbles {

    @Test
    public void testNybbles() {
        Nybbles x = new Nybbles(5);
        x.set(0, 4);
        x.set(1, 3);
        x.set(2, -5);
        x.set(3, 1);
        x.set(4, 0);
        assertEquals(4, x.get(0));
        assertEquals(3, x.get(1));
        assertEquals(-5, x.get(2));
        assertEquals(1, x.get(3));
        assertEquals(0, x.get(4));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(TestNybbles.class));
    }
}
