/** Implementation of a String Set with a binary search tree.
 * @author Carlos A. Flores-Arellano.*/

public class BSTStringSet implements StringSet  {

    /** The item in this node.*/
    private String _label;

    /** Left child of the tree.*/
    protected BSTStringSet _left;

    /** Right child of the tree.*/
    protected BSTStringSet _right;

    /** Constructor initialized with a LABEL.*/
    public BSTStringSet(String label) {
        _label = label;
        _left = null;
        _right = null;
    }

    /** Constructor with no inputs.*/
    public BSTStringSet() {
        this("dummy");
    }

    /** Adds the string S to this BSTStringSet.*/
    public void put(String s) {
        if (_label.compareTo(s) > 0) {
            if (_left == null) {
                _left = new BSTStringSet(s);
            } else {
                _left.put(s);
            }
        } else if (_label.compareTo(s) < 0) {
            if (_right == null) {
                _right = new BSTStringSet(s);
            } else {
                _right.put(s);
            }
        }
    }

    /** Returns true if S is in this BSTStringSet, and false otherwise.*/
    public boolean contains(String s) {
        if (_label.compareTo(s) > 0) {
            if (_left == null) {
                return false;
            } else {
                return _left.contains(s);
            }
        } else if (_label.compareTo(s) < 0) {
            if (_right == null) {
                return false;
            } else {
                return _right.contains(s);
            }
        }
        return true;
    }

    /** Prints this BSTStringSet in an inorder fashion.*/
    public void printInOrder() {
        if (_left != null) {
            _left.printInOrder();
        }
        System.out.print(_label + " ");
        if (_right != null) {
            _right.printInOrder();
        }
    }
}
