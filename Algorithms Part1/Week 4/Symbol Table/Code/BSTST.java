import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class BSTST<Key extends Comparable<Key>, Value> {
    private class Node {
        Key key;
        Value value;
        private int count;

        Node left, right;

        public Node(Key key, Value val) {
            this.key = key;
            this.value = val;
            this.count = 1;
            this.left = this.right = null;
        }
    }

    private Node root;

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node node, Key key, Value val) {
        if (node == null)
            return new Node(key, val);

        int cmp = key.compareTo(node.key);
        // node should be inserted on the left subtree
        if (cmp < 0) node.left =  put(node.left, key, val);
        // node should be inserted on the right subtree
        else if (cmp > 0) node.right =  put(node.right, key, val);
        // node.key == key
        else node.value = val;

        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Value getval(Key key) {
        return getval(root, key);
    }

    private Value getval(Node node, Key key) {
        // key doesn't exist in the tree
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) return getval(node.left, key);
        else if (cmp > 0) return getval(node.right, key);
        return node.value;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp < 0) node.left = delete(node.left, key);
        else if (cmp > 0) node.right = delete(node.right, key);
        else {
            if (node.left == null) return node.right;
            if (node.right == null) return node.left;

            Node old_node = node;
            node = min_node(old_node.right);
            node.right = deleteMin(old_node.right);
            node.left = old_node.left;
        }
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    private Node min_node(Node node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node;
        return min_node(node.left);
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    private Node deleteMin(Node node) {
        if (node == null)
            return null;
        if (node.left == null)
            return node.right;

        node.left = deleteMin(node.left);
        node.count = 1 + size(node.left) + size(node.right);
        return node;
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        inorder_traverse(queue, root);
        return queue;
    }

    private void inorder_traverse(Queue<Key> queue, Node node) {
        if (node == null)
            return;
        inorder_traverse(queue, node.left);
        queue.enqueue(node.key);
        inorder_traverse(queue, node.right);
    }

    public int size() {
        return size(root);
    }

    private int size(Node node) {
        if (node == null)
            return 0;
        return 1 + size(node.left) + size(node.right);
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    private int rank(Node node, Key key) {
        if (node == null)
            throw new IllegalArgumentException();

        int cmp = key.compareTo(node.key);
        if (cmp < 0) return rank(node.left, key);
        else if (cmp > 0) return 1 + size(node.left) + rank(node.right, key);
        return size(node.left);
    }

    public Key floor(Key key) {
        return floor(root, key);
    }

    private Key floor(Node node, Key key) {
        if (node == null)
            return null;
        int cmp = key.compareTo(node.key);
        if (cmp == 0) return node.key;
        if (cmp < 0) return floor(node.left, key);

        Key temp = floor(node.right, key);
        if (temp != null)
            return temp;

        return node.key;
    }

    public static void main(String[] args) {
        BSTST<String, Integer> st = new BSTST<>();
        for (int i = 0; !StdIn.isEmpty(); ++i) {
            String key = StdIn.readString();
            st.put(key, i);
        }

        System.out.println(st.rank("Cici"));

        for (String s: st.keys())
            StdOut.println(s + " " + st.getval(s));
    }
}
