import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Quick {
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void sort(Comparable[] arr) {
        StdRandom.shuffle(arr);
        Quick.sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int j = partition(arr, lo, hi);
        sort(arr, lo, j - 1);
        sort(arr, j + 1, hi);
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo; int j = hi + 1;

        while (true) {
            while (less(arr[++i], arr[lo]))
                if (i == hi)
                    break;

            while (less(arr[lo], arr[--j]))
                if (j == lo)
                    break;

            if (j <= i)
                break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
    }

    public static void main(String[] args) {
        Double[] arr = new Double[10];
        for(int i = 0; i < 10; ++i) {
            arr[i] = StdRandom.uniform();
        }

        Quick.sort(arr);

        for(double d : arr)
            StdOut.print(d + " ");
    }
}