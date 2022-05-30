import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Merge {
    public static void sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;

        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        assert isSorted(arr, lo, mid);
        assert isSorted(arr, mid + 1, hi);

        for (int k = lo; k <= hi; ++k) {
            aux[k] = arr[k];
        }
        int i = lo; int j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (less(arr[j], arr[i])) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }

        assert isSorted(arr, lo, hi);
    }

    private static boolean isSorted(Comparable[] arr, int lo, int hi) {
        for (int i = lo; i < hi; ++i) {
            if (!less(arr[i], arr[i + 1]))
                return false;
        }
        return true;
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void main(String[] args) {
        int N = 10;
        Double[] arr = new Double[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = StdRandom.uniform();
        }

        Merge.sort(arr);

        for (int i = 0; i < N; ++i) {
            StdOut.print(arr[i] + " ");
        }
    }
}