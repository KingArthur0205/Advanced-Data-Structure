/* *****************************************************************************
 *  Name:
 *  Date:
 *  Description:
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] queue;
    private int capacity;
    private int last;
    // construct an empty randomized queue
    public RandomizedQueue() {
        capacity = 1;
        last = 0;
        queue = (Item[])new Object[capacity];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return last == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return last;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null)
            throw new NoSuchElementException();

        queue[last++] = item;

        if (last == capacity)
            resizing(capacity * 2);
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        int index = StdRandom.uniform(last);
        Item item = queue[index];

        int lastIndex = last - 1;
        if(lastIndex != index) {
            queue[index] = queue[lastIndex];
        }
        last--;

        if (last == capacity / 4) {
           resizing(capacity / 2);
        }
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty())
            throw new NoSuchElementException();
        int index = StdRandom.uniform(last);

        return queue[index];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new RQueueIterator();
    }

    private class RQueueIterator implements Iterator<Item> {
        private int index = 0;
        private int[] orders;

        public RQueueIterator() {
            orders = new int[last];
            for(int i = 0; i < last; ++i) {
                orders[i] = i;
            }
            StdRandom.shuffle(orders);
        }
        public boolean hasNext() {
            return index < last;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Item next() {
            if (isEmpty())
                throw new NoSuchElementException();

            Item item = queue[orders[index++]];
            return item;
        }
    }

    private void resizing(int capacity) {
        Item[] copies = (Item[])new Object[capacity];

        for(int i = 0; i < last; ++i) {
            copies[i] = queue[i];
        }

        queue = copies;
        this.capacity = capacity;
    }

    // unit testing (required)
    public static void main(String[] args) {

    }
}
