import org.junit.Test; import static org.junit.Assert.*;
/** Tests for the Matrix Utils class.
 *  @author Carlos Flores.
 */

public class MatrixUtilsTest {

    /** Tests accumulateVertical. */
    @Test
    public void accumulateVerticalTest() {
        double[][] a = new double[][] {{1, 4, 3}, {5, 2, 3}, {2, 3, 4}};
        double[][] b = new double[][] {{1, 4, 3}, {6, 3, 6}, {5, 6, 7}};
        double[][] c = MatrixUtils.accumulateVertical(a);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                assertTrue(b[i][j] == c[i][j]);
            }
        }
    }

    /** Tests accumulate and reverseMatrix.*/
    @Test
    public void accumulateTest() {
        double[][] a = new double[][] {{1, 4, 3}, {5, 2, 3}, {2, 3, 4}};
        double[][] b = new double[][] {{1, 4, 3}, {6, 3, 6}, {5, 6, 7}};
        double[][] c = new double[][] {{1, 5, 2}, {5, 3, 5}, {6, 6, 7}};
        double[][] d = MatrixUtils.accumulate(a,
                MatrixUtils.Orientation.VERTICAL);
        double[][] e = MatrixUtils.accumulate(a,
                MatrixUtils.Orientation.HORIZONTAL);
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                assertTrue(b[i][j] == d[i][j]);
                assertTrue(c[i][j] == e[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(MatrixUtilsTest.class));
    }
}
