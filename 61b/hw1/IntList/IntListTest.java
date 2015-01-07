import static org.junit.Assert.*;
import org.junit.Test;

public class IntListTest {

    /** Example test that verifies correctness of the IntList.list static
     *  method. The main point of this is to convince you that
     *  assertEquals knows how to handle IntLists just fine.
     */

    @Test
    public void testList() {
        IntList one = new IntList(1, null);
        IntList twoOne = new IntList(2, one);
        IntList threeTwoOne = new IntList(3, twoOne);

        IntList x = IntList.list(3, 2, 1);
        assertEquals(threeTwoOne, x);
        IntList y = IntList.list(1, 2, 3);
        IntList z = IntList.list(5, 6, 7);
        z.dcatenate(y, z);
    }

    /** Do not use the new keyword in your tests. You can create
     *  lists using the handy IntList.list method.
     *
     *  Make sure to include test cases involving lists of various sizes
     *  on both sides of the operation. That includes the empty list, which
     *  can be instantiated, for example, with
     *  IntList empty = IntList.list().
     *
     *  Keep in mind that dcatenate(A, B) is NOT required to leave A untouched.
     *  Anything can happen to A.
     */

    @Test
    public void testDcatenate() {
        IntList testList = IntList.list(1, 2, 3, 4, 5, 6);
        IntList listOne = IntList.list(1, 2, 3, 4);
        IntList listTwo = IntList.list(5, 6);
        IntList emptyList = IntList.list();
        assertTrue(testList.equals(listOne.dcatenate(listOne, listTwo)));
        assertTrue(testList.equals(listOne));
        assertTrue(listOne.equals(listOne.dcatenate(listOne, emptyList)));
    }

    /** Tests that subtail works properly. Again, don't use new.
     *
     *  Make sure to test that subtail does not modify the list.
     */

    @Test
    public void testSubtail() {
        IntList testList = IntList.list(1, 2, 3, 4, 5);
        IntList testListCheck = IntList.list(1, 2, 3, 4, 5);
        IntList listOne = testList.subTail(testList, 3);
        assertEquals(listOne, IntList.list(4, 5));
        assertEquals(testList, testListCheck);
    }

    /** Tests that sublist works properly. Again, don't use new.
     *
     *  Make sure to test that sublist does not modify the list.
     */

    @Test
    public void testSublist() {
        IntList testList = IntList.list(0, 0, 1, 2, 3, 4, 5, 0, 0);
        IntList testListCheck = IntList.list(0, 0, 1, 2, 3, 4, 5, 0, 0);
        IntList listOne = testList.sublist(testList, 2, 5);
        assertEquals(listOne, IntList.list(1, 2, 3, 4, 5));
        assertEquals(testList, testListCheck);
    }

    /** Tests that dSublist works properly. Again, don't use new.
     *
     *  As with testDcatenate, it is not safe to assume that list passed
     *  to dSublist is the same after any call to dSublist
     */

    @Test
    public void testDsublist() {
        IntList testList = IntList.list(0, 0, 1, 2, 3, 4, 5, 0, 0);
        IntList testListCheck = IntList.list(0, 0, 1, 2, 3, 4, 5, 0, 0);
        IntList listOne = IntList.dsublist(testList, 2, 5);
        assertEquals(listOne, IntList.list(1, 2, 3, 4, 5));
        assertFalse(testList.equals(testListCheck));
    }

    /* Run the unit tests in this file. */
    public static void main(String... args) {
        System.exit(ucb.junit.textui.runClasses(IntListTest.class));
    }
}

