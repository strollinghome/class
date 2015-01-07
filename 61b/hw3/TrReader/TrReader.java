import java.io.Reader;
import java.io.IOException;

/** Translating Reader: a stream that is a translation of an
 *  existing reader.
 *  @author Carlos Flores */
public class TrReader extends Reader {
    /** Stores the Reader source. */
    private Reader _source;

    /** Characters in this string will be translated. */
    private String _from;

    /** Characters will be translated to characters in this string. */
    private String _to;

    /** Number of read characters. */
    private int _read = 0;

    /** A new TrReader that produces the stream of characters produced
     *  by STR, converting all characters that occur in FROM to the
     *  corresponding characters in TO. That is, change occurrences of
     *  FROM.charAt(0) to TO.charAt(0), etc., leaving other characters
     *  unchanged.  FROM and TO must have the same length. */
    public TrReader(Reader str, String from, String to) {
        _source = str;
        _from = from;
        _to = to;
    }

    /** Reads characters into a portion of an array CBUF, starting at OFF, and
     * going up to LEN. It returns the number of characters read. */
    public int read(char[] cbuf, int off, int len) throws IOException {
        int toAdd = 0;
        while (true) {
            try {
                int c = _source.read();
                if (c == -1) {
                    return -1;
                }
                if (toAdd == len) {
                    return _read;
                }
                for (int i = 0; i < _from.length(); i++) {
                    if ((char) c == _from.charAt(i)) {
                        cbuf[off] = _to.charAt(i);
                        off++;
                        toAdd++;
                        _read++;
                        continue;
                    }
                }
                cbuf[off] = (char) c;
                off++;
                toAdd++;
                _read++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /** Closes the input stream (from Reader). */
    public void close() {
        try {
            _source.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
