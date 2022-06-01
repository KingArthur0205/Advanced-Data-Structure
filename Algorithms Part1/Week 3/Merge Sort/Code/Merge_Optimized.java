import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Merge_Optimize {
    private final static int INTERVAL = 4;
    public static void sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        Merge_Optimize.sort(arr, aux, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int lo, int hi) {
        if (lo >= hi) return;
        if (hi - lo <= INTERVAL) {
            insertion_sort(arr, lo, hi);
            return;
        }

        int mid = lo + (hi - lo) / 2;
        sort(arr, aux, lo, mid);
        sort(arr, aux, mid + 1, hi);
        merge(arr, aux, lo, mid, hi);
    }

    private static void insertion_sort(Comparable[] arr, int lo, int hi) {
        for (int i = lo; i <= hi; ++i) {
            for (int j = i; j > lo; --j) {
                if (less(arr[j], arr[j - 1]))
                    exch(arr, j, j - 1);
                else
                    break;
            }
        }
    }

    private static void exch(Comparable[] arr, int i, int j) {
        Comparable swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        for (int i = lo; i <= hi; ++i) {
            aux[i] = arr[i];
        }

        int i = lo; int j = mid + 1;
        for (int k = lo; k <= hi; ++k) {
            if (i > mid) arr[k] = aux[j++];
            else if (j > hi) arr[k] = aux[i++];
            else if (less(aux[j], aux[i])) arr[k] = aux[j++];
            else arr[k] = aux[i++];
        }
    }

    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        Double[] arr = new Double[10];
        for(int i = 0; i < 10; ++i) {
            arr[i] = StdRandom.uniform();
        }

        Merge_Optimize.sort(arr);

        for(double d : arr)
            StdOut.print(d + " ");
    }
}