import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/** String translation.
 * @author Carlos Flores. */
public class Translate {
    /** Return the String S, but with all characters that occur in FROM
     *  changed to the corresponding characters in TO. FROM and TO must
     *  have the same length. */
    static String translate(String S, String from, String to) {
        Reader in = new StringReader(S);
        TrReader translation = new TrReader(in, from, to);
        char[] cbuf = new char[S.length()];
        while (true) {
            try {
                int c = translation.read();
                System.out.println((char) c);
                if (c == -1) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String();
    }

    public static void main(String[] args) {
        String s = args[0];
        s = translate(s, "acd", "ACD");
        System.out.println(s);
    }
}
