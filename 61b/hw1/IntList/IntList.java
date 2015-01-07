import java.util.Formatter;
import java.util.Arrays;

/** Scheme-like pairs that can be used to form a list of integers.
 *  @author P. N. Hilfinger, with some modifications by Josh Hug
 *  [Do not modify this file.]
 *  */
public class IntList {

    public static void main (String[] args) {
        IntList[] R = new IntList[3];
        R[0] = IntList.list(1, 2);
        R[1] = IntList.list(3, 4, 5);
        R[2] = new IntList();
        R = distribute(IntList.list(6, 7, 8, 9), R);
        System.out.println(Arrays.deepToString(R));
    }

    /** First element of list. */
    public int head;

    /** Remaining elements of list. */
    public IntList tail;

    /** A List with head HEAD0 and tail TAIL0. */
    public IntList(int head0, IntList tail0) {
        head = head0;
        tail = tail0;
    }

    /** A List with null tail, and head = 0. */
    public IntList() {
        /* NOTE: public IntList () { }  would also work. */
        this (0, null);
    }

    /* YOU DO NOT NEED TO LOOK AT ANY CODE BELOW THIS LINE UNTIL
       YOU GET TO THE PROBLEMS YOU NEED TO SOLVE. Search for '2a'
       and you'll be where you need to go. */


    /** Returns a new IntList containing the ints in ARGS. */
    public static IntList list(Integer ... args) {
        IntList result, p;

        if (args.length > 0) {
            result = new IntList(args[0], null);
        } else {
            return null;
        }

        int k;
        for (k = 1, p = result; k < args.length; k += 1, p = p.tail) {
            p.tail = new IntList(args[k], null);
        }
        return result;
    }

    /** Returns true iff X is an IntList containing the same sequence of ints
     *  as THIS. Cannot handle IntLists with cycles. */
    public boolean equals(Object x) {
        if (!(x instanceof IntList)) {
            return false;
        }
        IntList L = (IntList) x;
        IntList p;

        for (p = this; p != null && L != null; p = p.tail, L = L.tail) {
            if (p.head != L.head) {
                return false;
            }
        }
        if (p != null || L != null) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        return head;
    }


    /** If a cycle exists in the IntList (A), this method
     *  returns an integer equal to the item number of the
     *  location where the cycle is detected.
     *
     *  If there is no cycle, the number 0 is returned instead.
     */

    private int detectCycles(IntList A) {
        IntList tortoise = A;
        IntList hare = A;

        if (A == null) {
            return 0;
        }
        int cnt = 0;
        while (true) {
            cnt++;
            if (hare.tail != null) {
                hare = hare.tail.tail;
            } else {
                return 0;
            }
            tortoise = tortoise.tail;
            if (tortoise == null || hare == null) {
                return 0;
            }
            if (hare == tortoise) {
                return cnt;
            }
        }
    }

    @Override
    public String toString() {
        Formatter out = new Formatter();
        String sep;
        sep = "(";
        int cycleLocation = detectCycles(this);
        int cnt = 0;

        for (IntList p = this; p != null; p = p.tail) {
            out.format("%s%d", sep, p.head);
            sep = ", ";

            cnt++;
            if ((cnt > cycleLocation) && (cycleLocation > 0)) {
                out.format("... (cycle exists) ...");
                break;
            }
        }
        out.format(")");
        return out.toString();
    }



    /** DO NOT MODIFY ANYTHING ABOVE THIS LINE! */


    /* 2a. */
    /** Returns a list consisting of the elements of A followed by the
     *  elements of B.  May modify items of A. Don't use 'new'. */

    static IntList dcatenate(IntList A, IntList B) {
        if (A == null) {
            return B;
        }
        IntList tempList = A;
        while (tempList.tail != null) {
            tempList = tempList.tail;
        }
        tempList.tail = B;
        return A;
    }

    /* 2b. */
    /** Returns a list consisting of the elements of L starting from
     * position START, and going all the way to the end. The head of the
     * list L is the 0th element of the list.
     *
     * This method should NOT modify L. */

    static IntList subTail(IntList L, int start) {
        IntList tempList = null;
        IntList tempHolder = null;
        int index = 0;
        while (L != null) {
            if (index >= start) {
                if (tempHolder == null) {
                    tempHolder = new IntList(L.head, null);
                    tempList = tempHolder;
                } else {
                    tempHolder.tail = new IntList(L.head, null);
                    tempHolder = tempHolder.tail;
                }
            }
            L = L.tail;
            index++;
        }
        return tempList;
    }

    /* 2c. */
    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item #START (where the first item is #0).
     *  Does not modify the original list elements.
     *  If the desired items don't exist, or the program
     *  receives negative start or len parameters, the behavior
     *  of this function is undefined, i.e. you can assume
     *  that start and len are always >= 0.
     */

    static IntList sublist(IntList L, int start, int len) {
        IntList tempList = L.subTail(L, start);
        IntList tempHolder = tempList;
        int index = 1;
        while (index != len) {
            tempHolder = tempHolder.tail;
            index++;
        }
        tempHolder.tail = null;
        return tempList;
    }

    /* 2d. */
    /** Returns the sublist consisting of LEN items from list L,
     *  beginning with item #START (where the first item is #0).
     *  May modify the original list elements. Don't use 'new'
     or the sublist method.

     *  As with sublist, you can assume the items requested
     *  exist, and that start and len are >= 0. */

    static IntList dsublist(IntList L, int start, int len) {
        IntList tempList = null;
        int index = 0;
        boolean set = false;
        while (index != len) {
            if (index == start && !set) {
                tempList = L;
                set = true;
                index = 0;
            } else {
                L = L.tail;
            }
            index++;
        }
        L.tail = null;
        return tempList;
    }

    static IntList[] distribute(IntList L, IntList[] R) {
        int i = 0;
        while (L != null) {
            IntList p = L;
            int k = i % R.length;
            L = L.tail;
            R[k] = attach(R[k], p);
            i = i + 1;
        }
        return R;
    }

    static IntList attach(IntList X, IntList Y) {
        Y.tail = null;
        if (X == null) {
            return Y;
        } else {
            IntList p = X;
            while (p.tail != null) {
                p = p.tail;
            }
            p.tail = Y;
            return X;
        }
    }
}
