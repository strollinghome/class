public class ArithmeticTest {

    /** Converts output of test to human readable string  */

    private static void report(String name, boolean isOK) {
        if (isOK) {
            System.out.printf("%s OK.%n", name);
        } else {
            System.out.printf("%s FAILS.%n", name);
        }
    }

    private static int errorCount;

    /** Returns true if double values are sufficiently close to each other (within
        delta difference) */

    private static boolean approxEqual(double x, double y) {
        double delta = 1e-15;
        return Math.abs(x - y) < delta;
    }

    /** Compare doubles approximately due to avoid false positives due to
        rounding errors */

    private static void check(double correct, double expected) {
        if (!approxEqual(correct, expected))
            errorCount++;       
    }

    /** Performs a few arbitrary tests to see if the product method is correct */

    public static boolean testProduct() {
        int startingErrors = errorCount;        
        check(30, Arithmetic.product(5, 6));
        check(-30, Arithmetic.product(5, -6));
        check(0, Arithmetic.product(0, -6));
        return startingErrors == errorCount;
    }

    /** Performs a few arbitrary tests to see if the sum method is correct */

    public static boolean testSum() {
        int startingErrors = errorCount;        
        check(11, Arithmetic.sum(5, 6));
        check(-1, Arithmetic.sum(5, -6));
        check(-6, Arithmetic.sum(0, -6));
        check(0, Arithmetic.sum(6, -6));
        return startingErrors == errorCount;
    }

    public static void main(String[] args) {
        report("product", testProduct());
        report("sum", testSum());
    }   
}