/* NOTE: The file ArrayUtil.java contains some functions that may be useful
 * in testing your answers. */

/** HW #2, Problem #2. */

/** Array utilities.
 *  @author Carlos Flores
 */
class Arrays {
    /* 2a. */
    /** Returns a new array consisting of the elements of A followed by the
     *  the elements of B. */
    static int[] catenate(int[] A, int[] B) {
        int[] catenatedArray = new int[A.length + B.length];
        System.arraycopy(A, 0, catenatedArray, 0, A.length);
        System.arraycopy(B, 0, catenatedArray, A.length,
                B.length);
        return catenatedArray;
    }

    /* 2b. */
    /** Returns the array formed by removing LEN items from A,
     *  beginning with item #START. */
    static int[] remove(int[] A, int start, int len) {
        int[] toRtn = new int[A.length - len];
        System.arraycopy(A, 0, toRtn, 0, start);
        System.arraycopy(A, start + len, toRtn, start,
                A.length - (len + start));
        return toRtn;
    }
}
