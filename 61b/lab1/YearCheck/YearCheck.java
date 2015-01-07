/**
 * Checking for leap years.
 *
 * @author Carlos Flores
 */

public class YearCheck {

    /** comment. */
    public static final int FOUR_HUNDRED = 400;

    /** comment.*/
    public static final int ONE_HUNDRED = 100;

    /** For each year in ARGS, indicate its leap status. */
    public static void main(String... args) {
        for (String arg : args) {
            int year = Integer.parseInt(arg);
            if (isLeapYear(year)) {
                System.out.printf("%d is a leap year.%n", year);
            } else {
                System.out.printf("%d is not a leap year.%n", year);
            }
        }
    }

    /** Returns true iff YEAR is a leap year. */
    static boolean isLeapYear(int year) {
        if (year % FOUR_HUNDRED == 0) {
            return true;
        } else if (year % 4 == 0) {
            if (year % ONE_HUNDRED != 0) {
                return true;
            }
        }
        return false;
    }
}
