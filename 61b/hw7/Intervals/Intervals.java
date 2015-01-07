import org.junit.Test;
import static org.junit.Assert.*;
import java.util.Collections;
import java.util.ArrayList;
import java.util.List;

/** HW #8, Problem 3.
 *  @author
  */
public class Intervals {
    /** Assuming that INTERVALS contains two-element arrays of integers,
     *  <x,y> with x <= y, representing intervals of ints, this returns a
     *  count of the number of intervals that X stabs (i.e. that x belongs to.
     */
    public static int stabbingCount(List<int[]> intervals, int x) {
        int count;
        count = 0;
        for (int[] interval : intervals) {
            if (x >= interval[0] && x <= interval[1]){
                count = count + 1;
            }
        }
        return count;
    }

    /** Performs a basic functionality test on the stabbingCount method. */
    @Test
    public void basicTest() {
        int[][] rangeMatrix = {{3, 10}, {4, 5}, {6, 12}, {8, 15}, {19, 30}};
        List<int[]> intervals = new ArrayList<int[]>();
        for (int i = 0; i < rangeMatrix.length; i += 1) {
            intervals.add(rangeMatrix[i]);
        }
        assertEquals(3, stabbingCount(intervals, 9));
        assertEquals(0, stabbingCount(intervals, 17));
    }

    /** Runs provided JUnit test. ARGS is ignored. */
    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(Intervals.class));
    }

}
