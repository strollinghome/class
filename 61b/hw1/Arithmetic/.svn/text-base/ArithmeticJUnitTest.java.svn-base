import static org.junit.Assert.*;
import org.junit.Test;

public class ArithmeticJUnitTest {

    /** Performs a few arbitrary tests to see if the product method is correct */

    @Test 
    public void testProduct() {
        /* assertEquals for comparison of doubles takes three arguments:
        assertEquals(expected, actual, delta). 

        if Math.abs(expected - actual) < delta, then the assertion will be true.

        if it is false, then the assertion will be false, and this test will fail.

        See http://junit.sourceforge.net/javadoc/org/junit/Assert.html#assertEquals(double, double, double)
        for more. */
        double delta = 1e-15;

        assertEquals(30, Arithmetic.product(5, 6), delta);
        assertEquals(-30, Arithmetic.product(5, -6), delta);
        assertEquals(0, Arithmetic.product(0, -6), delta);      
    }

    /** Performs a few arbitrary tests to see if the sum method is correct */

    @Test 
    public void testSum() {
        double delta = 1e-15;

        assertEquals(11, Arithmetic.sum(5, 6), delta);
        assertEquals(-1, Arithmetic.sum(5, -6), delta);
        assertEquals(-6, Arithmetic.sum(0, -6), delta);
        assertEquals(0, Arithmetic.sum(6, -6), delta);
    }

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(ArithmeticJUnitTest.class));
    }       
}
