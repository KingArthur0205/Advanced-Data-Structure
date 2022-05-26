/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private Node first, last;
    private int size;
    private class Node {
        public Node(Item item) {
            this.item = item;
            prev = next = null;
        }

        Item item;
        Node prev, next;
    }

    private class DequeIterator implements Iterator<Item> {
        private Node current = first;
        private int currentIndex = 0;

        public boolean hasNext() {
            return current != null;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (!hasNext())
                throw new NoSuchElementException();

            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    // construct an empty deque
    public Deque() {
        size = 0;
        first = last = null;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    /*
     * 1. Record the old first
     * 2. Construct a new node and assign it to first
     * 3. Have the new first's next points to old first and prev to last
     * 4. If last is not empty, update last's next points to the new first
     * 5. Else assign last to first
     */
    public void addFirst(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldFirst = first;
        first = new Node(item);
        first.next = oldFirst;
        first.prev = null;

        if (isEmpty())
            last = first;
        else
            oldFirst.prev = first;

        size++;
    }

    // add the item to the back
    /*
     * 1. Record the old last
     * 2. Construct a new node and assign it to last
     * 3. Have the new last's next points to first and prev to the old last.
     * 4.If first is empty, assign last to first
     * 5. Else have first's next points to the new last
     */
    public void addLast(Item item) {
        if (item == null)
            throw new IllegalArgumentException();

        Node oldLast = last;
        last = new Node(item);
        last.next = null;
        last.prev = oldLast;

        if (isEmpty())
            first = last;
        else
            oldLast.next = last;

        size++;
    }

    // remove and return the item from the front
    /*
     * 1. Record first's item and return.
     * 2. Assign first to first's next
     * 3. If isEmpty(), assign last to null
     * 4. Otherwise, assign last's next to first and first's prev to last.
     */
    public Item removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();

        size--;
        Item item = first.item;
        first = first.next;

        if (isEmpty())
            last = null;
        else
            first.prev = null;

        return item;
    }

    // remove and return the item from the back
    /*
     * 1. Record last's  item and return
     * 2. Assign last to last's prev
     * 3. If isEmpty(), assign first to empty
     * 4. Otherwise, assign last's next to first and first's prev to last
     */
    public Item removeLast() {
        if (isEmpty())
            throw new NoSuchElementException();

        size--;
        Item item = last.item;
        last = last.prev;

        if (isEmpty())
            first = null;
        else
            last.next = null;

        return item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<String> stack = new Deque<>();

        while (!StdIn.isEmpty()) {
            String s = StdIn.readString();
            if (s.equals("-"))
                StdOut.println(stack.removeFirst());
            else
                stack.addLast(s);
        }

        Iterator<String> itr = stack.iterator();
        while(itr.hasNext()) {
            String str = itr.next();
            StdOut.println(str);
        }
    }
}
