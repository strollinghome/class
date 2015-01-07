/** Compound interest generator.
 *
 * @author Carlos Flores */
public class CompoundInterest {

    /** Current year. */
    static final int THIS_YEAR = 2014;

    /** Divisor to calculate interest rate. */
    static final int DIVISOR = 100;

    /** Computes and returns the number of years between TARGETYEAR and
     * THIS_YEAR, e.g. if THIS_YEAR is 2014 and targetYear is 2015, the result
     * should be 1 */

    static int numYears(int targetYear) {
        return targetYear - THIS_YEAR;
    }

    /** Suppose we have an asset worth PRESENTVALUE that appreciates
      by RATE compounded annually. This method returns the value of that
      asset in the year given by TARGETYEAR.

      The rate is given as a percentage return. For example, if the
      presentValue is 10, the rate is 12, and the targetYear is 2016,
      then the futureValue will be 10*1.12*1.12 = 12.544. */

    static double futureValue(double presentValue, double rate,
            int targetYear) {
        double interestRate = rate / DIVISOR + 1;
        double compound = ((double) Math.pow(interestRate,
                    numYears(targetYear)));
        double value = presentValue * compound;
        return value;
    }

    /** Same as futureValue, except that it returns the final amount is
     * converted into THIS_YEAR dollars; assuming a simple model where inflation
     * compounds annually at a constant rate.

      For example, suppose the PRESENTVALUE is 10, the RATE is 12, the
      TARGETYEAR is 2016, and the INFLATIONRATE is 3.

      In this case, the nominal value is 12.544. If we convert this into
      2014 dollars, we get 12.544 * 0.97 * 0.97 = 11.8026496 dollars */

    static double futureValueReal(double presentValue, double rate,
            int targetYear,
            double inflationRate) {
        double nominalValue = futureValue(presentValue, rate, targetYear);
        double adjustmentRate = (DIVISOR - inflationRate) / DIVISOR;
        double value = nominalValue * ((double) Math.pow(adjustmentRate,
                    numYears(targetYear)));
        return value;

    }

    /** Suppose you invest PERYEAR dollars at the end of every year until
     TARGETYEAR, with growth compounded annually at RATE. This method
      returns the total value of your savings in targetYear.
      For example, if perYear is 5000, targetYear is 2016, and rate is 1.1,
      then the result will be 5000*1.1*1.1 + 5000*1.1 + 5000 = 16550.*/

    static double totalSavings(double perYear, int targetYear, double rate) {
        double value = 0;
        int accruedYears = numYears(targetYear);
        while (accruedYears >= 0) {
            value += Math.pow(rate, accruedYears) * perYear;
            accruedYears--;
        }
        return value;
    }

    /** Same as totalSavings, except returns THIS_YEAR dollars. This method
     * return the real savings for investing PERYEAR dollars at a ceratin RATE
     * with the specified INFLATIONRATE for a period of time specified by
     * TARGETYEAR */

    static double totalSavingsReal(double perYear, int targetYear, double rate,
            double inflationRate) {
        double adjustmentRate = (DIVISOR - inflationRate) / DIVISOR;
        double nominalValue = totalSavings(perYear, targetYear, rate);
        double value = nominalValue * ((double) Math.pow(adjustmentRate,
                    numYears(targetYear)));
        return value;

    }

    /** Prints out the future value of a dollar in a nice format, assuming
     * yearly (TARGETYEAR) compounded interest at a rate equal to RETURNRATE
     * and accounting for INFLATIONRATE. */

    static void printDollarFV(int targetYear, double returnRate,
            double inflationRate) {

        double nominalDollarValue = futureValue(1, returnRate, targetYear);
        double realDollarValue = futureValueReal(1, returnRate, targetYear,
                inflationRate);

        String dollarSummary = String.format("Assuming a %.2f%% rate of return,"
                + " a dollar saved today would be worth"
                + " %.2f dollars in the year %d, or %.2f dollars"
                + " adjusted for inflation.", returnRate, nominalDollarValue,
                targetYear, realDollarValue);

        System.out.println(dollarSummary);
    }

    /** Prints out the future value of your total savings in a nice format,
     * assuming yearly compounded interest at a rate equal to RETURNRATE,
     * and an investment of PERYEAR dollars each year until TARGETYEAR, while
     * adjusting for INFLATIONRATE. */

    static void printSavingsFV(int targetYear, double returnRate,
            double inflationRate, double perYear) {

        double nominalSavings = totalSavings(perYear, targetYear, returnRate);
        double realSavings = totalSavingsReal(perYear, targetYear, returnRate,
                inflationRate);

        String savingsSummary = String.format(
                "Assuming a %.2f%% rate of return,"
                + " in the year %d, after saving %.2f"
                + " dollars per year, you'll have %.2f dollars or"
                + " %.2f dollars adjusted for inflation.", returnRate,
                targetYear, perYear, nominalSavings, realSavings);

        System.out.println(savingsSummary);
    }

    /** Target year. */
    static final int TARGET_YEAR = 2054;

    /** Per year value. */
    static final double PER_YEAR = 10000;

    /** Main method, takes in ARGS. */

    public static void main(String[] args) {
        /* Parameters for the analysis.

         * targetYear is the year of interest.
         * returnRate is the percentage rate that you expect to earn on
         average until targetYear.
         * inflationRate is the average inflation rate until targetYear
         * perYear is how much money you will save per year until targetYear */

        double returnRate = 10;
        double inflationRate = 3;

        printDollarFV(TARGET_YEAR, returnRate, inflationRate);
        System.out.println();
        printSavingsFV(TARGET_YEAR, returnRate, inflationRate, PER_YEAR);
    }
}
