import org.junit.Test;
import static org.junit.Assert.*;

/** Tests the BSTStringSet class.
 * @author Carlos A. Flores-Arellano.*/
public class TestBSTStringSet {

    @Test
    public void testConstructor() {
        BSTStringSet tree = new BSTStringSet("d");
        tree.put("b");
        tree.put("f");
        tree.put("z");
        tree.put("a");
        tree.put("a");
        assertTrue(tree.contains("b"));
        assertFalse(tree.contains("x"));
        assertTrue(tree.contains("b"));
        assertTrue(tree.contains("a"));
        tree.printInOrder();
    }

    public static void main(String[] args) {
        System.exit(ucb.junit.textui.runClasses(TestBSTStringSet.class));
    }

}
