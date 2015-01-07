/** DoubleChain implements a doubly linked-list structure.
 * @author Carlos Flores */

public class DoubleChain {

    /** First element of the list. */
    public double val;

    /** Previous element in the list. */
    public DoubleChain prev;

    /** Next element in the list. */
    public DoubleChain next;

    /** Empty DoubleChain. */
    public DoubleChain() { }

    /** DoubleChain (head) constructor with VALUE. */
    public DoubleChain(double value) {
        this.val = value;
        this.prev = null;
        this.next = null;
    }

    /** DoubleChain constructor setting VALUE, PREVIOUS, and NEXT. */
    public DoubleChain(double value, DoubleChain previous, DoubleChain next0) {
        this.val = value;
        this.prev = previous;
        this.next = next0;
    }

    /** Returns the back of the DoubleChain D. */
    public static DoubleChain getBack(DoubleChain d) {
        if (d.next == null) {
            return d;
        }
        return getBack(d.next);
    }

    /** Returns the front of the DoubleChain D. */
    public static DoubleChain getFront(DoubleChain d) {
        if (d.prev == null) {
            return d;
        }
        return getFront(d.prev);
    }

    /** Inserts a value VAL at the back of the list D. */
    public static void insertBack(DoubleChain d, double val) {
        DoubleChain lastElement = getBack(d);
        DoubleChain backElement = new DoubleChain(val, lastElement, null);
        lastElement.next = backElement;
    }

    /** Inserts a value VAL at the front of the list D. */
    public static void insertFront(DoubleChain d, double val) {
        DoubleChain firstElement = getFront(d);
        DoubleChain frontElement = new DoubleChain(val, null, firstElement);
        firstElement.prev = frontElement;
        d = frontElement;
    }

    @Override
    public String toString() {
        DoubleChain list = this.getFront(this);
        String repr = "";
        repr += list.val;
        list = list.next;
        while (list.next != null) {
            repr += ", ";
            repr += list.val;
            list = list.next;
        }
        return repr;
    }
}
