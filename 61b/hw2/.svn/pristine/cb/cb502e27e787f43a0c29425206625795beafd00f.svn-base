import org.junit.Test;
import static org.junit.Assert.*;

/** Test for the Lists class.
 * @author Carlos Flores. */
public class ListsTest {

    /** Test natural runs. */
    @Test
    public void testClass() {
        IntList listOne = IntList.list(1, 3, 7, 5, 4, 6, 9, 10);
        IntList listTwo = IntList.list(1, 3, 7);
        IntList listThree = IntList.list(5);
        IntList listFour = IntList.list(4, 6, 9, 10);
        IntList2 nRuns = Lists.naturalRuns(listOne);
        assertTrue(nRuns.equals(IntList2.list(listTwo, listThree, listFour)));
    }

    /** Tests edge cases. */
    @Test
    public void testEdge() {
        IntList listOne = IntList.list(1, 1, 1);
        IntList listTwo = IntList.list(1);
        IntList2 nRuns = Lists.naturalRuns(listOne);
        IntList2 listOneRuns = IntList2.list(listTwo, listTwo, listTwo);
        assertEquals(nRuns, listOneRuns);
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(ListsTest.class));
    }
}
