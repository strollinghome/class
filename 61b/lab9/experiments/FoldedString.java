/** A wrapper class for String whose .equals and .compareTo methods ignore
 *  the difference between upper and lower case letters.
 *  @author Carlos A. Flores-Arellano.
 */
class FoldedString implements Comparable<FoldedString> {

    /** A new FoldedString that represents the string S. */
    public FoldedString(String s) {
        rep = s.toUpperCase();
    }

    @Override
    public String toString() {
        return rep;
    }

    @Override
    public int hashCode() {
        int h = rep.hashCode();
        return h;
    }

    @Override
    public boolean equals(Object obj) {
        try {
            return rep.equalsIgnoreCase(((FoldedString) obj).rep);
        } catch (ClassCastException e) {
            return false;
        }
    }

    @Override
    public int compareTo(FoldedString anotherString) {
        return rep.compareToIgnoreCase(anotherString.rep);
    }

    /** The string given to the constructor. */
    private String rep;

}
