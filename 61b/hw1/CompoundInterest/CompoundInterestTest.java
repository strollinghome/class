import static org.junit.Assert.*;
import org.junit.Test;

public class CompoundInterestTest {

    @Test
    public void testNumYears() {
    /** Example assert statement for comparing integers.

        assertEquals(0, 0); */
        CompoundInterest account = new CompoundInterest();
        int targetYear = 2064;
        assertEquals(account.numYears(targetYear), 50);
        assertEquals(account.numYears(targetYear) + account.THIS_YEAR,
                targetYear);
    }

    @Test
    public void testFutureValue() {
        double tolerance = 0.01;
        CompoundInterest account = new CompoundInterest();
        int targetYear = 2016;
        assertEquals(account.futureValue(10, 12, targetYear), 12.544,
                tolerance);
        assertEquals(account.futureValue(10, -12, targetYear), 7.744,
                tolerance);
        assertEquals(account.futureValueReal(10, 12, 2016, 3), 11.8026496,
                tolerance);
    }

    @Test
    public void testTotalSavings() {
        double tolerance = 0.01;
        CompoundInterest account = new CompoundInterest();
        int targetYear = 2016;
        assertEquals(account.totalSavings(5000, targetYear, 1.1), 16550,
                tolerance);
    }

    @Test
    public void testTotalSavingsReal() {
        double tolerance = 0.01;
        CompoundInterest account = new CompoundInterest();
        int targetYear = 2016;
        assertEquals(account.totalSavingsReal(5000, targetYear, 1.1, 3),
                15571.8949, tolerance);
    }


    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(CompoundInterestTest.class));
    }
}
