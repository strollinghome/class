/** Represents an array of integers each in the range -8..7.
 *  Such integers may be represented in 4 bits (called nybbles).
 *  @author Carlos A. Flores-Arellano.*/

public class Nybbles {
    /** Return an array of size N. */
    public Nybbles(int N) {
        _data = new int[(N + 7) / 8];
        _n = N;
    }

    /** Return the size of THIS. */
    public int size() {
        return _n;
    }

    /** Return the Kth integer in THIS array, numbering from 0.
     *  Assumes 0 <= K < N.*/
    public int get(int k) {
        if (k < 0 || k >= _n) {
            throw new IndexOutOfBoundsException();
        } else {
            int result = 0;
            int index = 0;
            int negResult = 0;
            k = k % 8;
            index = k / 8;
            result = _data[index];
            if (k != 0) {
                result = result << (k * 4);
            }
            result = result >>> rightBits;
            if (result > 7) {
                result = result - 8;
                return result * -1;
            }
            return result;
        }
    }

    /** Set the Kth integer in THIS array to VAL.  Assumes
     *  0 <= K < N and -8 <= VAL < 8.*/
    public void set(int k, int val) {
        if (k < 0 || k >= _n) {
            throw new IndexOutOfBoundsException();
        } else if (val < -8 || val >= 8) {
            throw new IllegalArgumentException();
        } else {
            int index = 0;
            k = k % 8;
            index = k / 8;
            int bits = _data[index];
            int bitsCopy = _data[index];
            if (val < 0) {
                val = (val * -1) + 8;
            }
            val = val << (rightBits - (k * 4));
            bitsCopy = bitsCopy >> (intBits - (k * 4)) << (intBits - (k * 4));
            bits = bits << ((k + 1) * 4) >>> ((k + 1) * 4);
            bits = bits | val;
            bits = bits | bitsCopy;
            _data[index] = bits;
        }
    }

    /** Size of current array (in nybbles). */
    private int _n;
    /** The array data, packed 8 nybbles to an int. */
    private int[] _data;

    /** Shift 32.*/
    private final int intBits = 32;

    /** Shift 28 bits.*/
    private final int rightBits = 28;
}
