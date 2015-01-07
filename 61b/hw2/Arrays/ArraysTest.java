import org.junit.Test;
import static org.junit.Assert.*;

/** Arrays test.
 * @author Carlos Flores */
public class ArraysTest {

    /** Tests Arrays.catenate. */
    @Test
    public void testCatenate() {
        int[] a = new int[] {1, 2, 3};
        int[] b = new int[] {4, 5, 6};
        int[] c = new int[] {1, 2, 3, 4, 5, 6};
        int[] d = Arrays.catenate(a, b);
        assertTrue(Utils.equals(c, 0, d, 0, c.length));
    }

    /** Tests Arrays.remove. */
    @Test
    public void testRemove() {
        int[] a = new int[] {1, 2, 5, 5, 5, 3};
        int[] b = Arrays.remove(a, 2, 3);
        int[] c = new int[] {1, 2, 3};
        assertTrue(Utils.equals(b, 0, c, 0, b.length));
        int[] d = Arrays.remove(c, 1, 2);
        int[] e = new int[] {1};
        assertTrue(Utils.equals(d, 0, e, 0, d.length));
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ArraysTest.class));
    }
}
