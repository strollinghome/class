/** Functions to increment and sum the elements of a WeirdList.
 * @author Carlos Flores. */
class WeirdListClient {

    /** Returns the result of adding N to each element of L. */
    static WeirdList add(WeirdList L, int n) {
        Add addition = new Add(n);
        return L.map(addition);
    }

    /** Class with a function apply that adds numbers.*/
    public static class Add implements IntUnaryFunction {

        /** Number to be added. */
        private int _toAdd;

        /** Constructor for Add, set the default adder value to TOADD. */
        public Add(int toAdd) {
            _toAdd = toAdd;
        }

        /** Applies and returns the addition of _toAdd to HEAD. */
        public int apply(int head) {
            return head + _toAdd;
        }
    }
}
