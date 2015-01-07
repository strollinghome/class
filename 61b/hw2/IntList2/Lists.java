/* NOTE: The file Utils.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2, Problem #1. */

/** List problem.
 *  @author Carlos Flores */

class Lists {
    /** Return the list of lists formed by breaking up L into "natural runs":
     *  that is, maximal ascending sublists, in the same order as
     *  the original.  For example, if L is (1, 3, 7, 5, 4, 6, 9, 10),
     *  then result is the three-item list ((1, 3, 7), (5), (4, 6, 9, 10)).
     *  Destructive: creates no new IntList items, and may modify the
     *  original list pointed to by L. */
    static IntList2 naturalRuns(IntList L) {
        IntList2 runsList = new IntList2();
        IntList2 runsHolder = runsList;
        IntList tempHolder = L;
        IntList tempL = L;
        while (tempHolder.tail != null) {
            if (tempHolder.head >= tempHolder.tail.head) {
                tempL = tempHolder.tail;
                tempHolder.tail = null;
                if (runsList.head == null) {
                    runsList.head = L;
                } else {
                    runsHolder.tail = new IntList2(L, null);
                    runsHolder = runsHolder.tail;
                }
                L = tempL;
                tempHolder = tempL;
            } else {
                tempHolder = tempHolder.tail;
                tempL = tempHolder;
            }
        }
        runsHolder.tail = new IntList2(L, null);
        return runsList;
    }
}
