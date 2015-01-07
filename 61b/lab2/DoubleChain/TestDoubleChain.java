import org.junit.Test;
import static org.junit.Assert.*;

/** Perform tests of the DoubleChain class
 */

public class TestDoubleChain {

    /** Tests the constructor of DoubleChain */
    @Test
    public void testConstructor() {
        DoubleChain d = new DoubleChain(5);
        assertEquals(d.val, 5, 1e-6);
        assertEquals(d.prev, null);
        assertEquals(d.next, null);
    }

    /** Tests the DoubleChainClass
     */

    @Test
    public void testDoubleChainClass() {
        DoubleChain a = new DoubleChain();
        a.insertFront(a, 2);
        a.insertFront(a, 1);
        a.insertBack(a, 3);
        DoubleChain frontElement = a.getFront(a);
        DoubleChain backElement = a.getBack(a);
        assertTrue(frontElement.val == 1);
        assertTrue(backElement.val == 3);
    }

    @Test
    public void testString() {
        DoubleChain a = new DoubleChain();
        a.insertFront(a, 3);
        a.insertFront(a, 2);
        a.insertFront(a, 1);
        assertEquals(a.toString(), "1.0, 2.0, 3.0");
    }

    public static void main(String[] args) {
        ucb.junit.textui.runClasses(TestDoubleChain.class);
    }
}
