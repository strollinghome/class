import java.util.Hashtable;

public class CountLetters {

    private Hashtable<Character, Integer> bindings;
    
    public CountLetters(String s) {
        bindings = new Hashtable<>();
        int k = 0;
        while (k < s.length()) {
            char c = s.charAt(k);
            if (bindings.containsKey(c)) {
                bindings.put(c, bindings.get(c) + 1);
            } else {
                bindings.put(c, 1);
            }
            k = k + 1;
        }
    }

    public int getFrequency(char c) {
        return bindings.get(c);
    }

    public static void main (String[] args) {
        CountLetters counter = new CountLetters(args[0]);
        System.out.println(counter.getFrequency(args[1].charAt(0)));
    }
}
