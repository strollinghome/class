import java.util.List;

public abstract class AbstractList<Item> implements List<Item> {
   
    public boolean contains(Object x) {
        for (int i = 0; i < size(); i += 1) {
            if ((x == null && get(i) == null) ||
                (x != null && x.equals(get(i)))) {
                return true;
            }
        }
        return false;
    }

    public Iterator<Item> iterator() {
        return listIterator();
    }

    public ListIterator<Item> listIterator() {
        return new AListIterator(this);
    }

    private static class AListIterator implements ListIterator<Item> {

        AbstractList<Item> myList;

        int where = 0;

        AListIterator(AbstractList<Item> L) {
            myList = L;
        }

        public boolean hasNext() {
            return where < myList.size();
        }

        public Item next() {
            where += 1;
            return myList.get(where - 1);
        }

        public void add(Item x) {
            myList.add(where, x);
            where += 1;
        }
    }
}
