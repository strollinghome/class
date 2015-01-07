import java.util.Iterator;
import utils.Filter;

/** A kind of Filter that lets through every other VALUE element of
 *  its input sequence, starting with the first.
 *  @author Carlos A. Flores-Arellano.*/

class AlternatingFilter<Value> extends Filter<Value> {

    /** A filter of values from INPUT that lets through every other
     *  value. */
    AlternatingFilter(Iterator<Value> input) {
        super(input);
        _k = 0;
    }

    @Override
    protected boolean keep() {
        if ((_k % 2 == 0)) {
            _k = _k + 1;
            return true;
        }
        _k = _k + 1;
        return false;
    }

    /** Current index in value.*/
    private int _k;
}
