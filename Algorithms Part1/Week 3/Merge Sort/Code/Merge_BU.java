import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Merge_BU {
    private static void merge(Comparable[] arr, Comparable[] aux, int lo, int mid, int hi) {
        for (int k = lo; k <= hi; ++k) {
            aux[k] = arr[k];
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

    public static void sort(Comparable[] arr) {
        int N = arr.length;
        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int lo = 0; lo < N - sz; lo += (sz + sz)) {
                merge(arr, aux, lo, lo + sz - 1, Math.min(lo + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        Double[] arr = new Double[10];
        for(int i = 0; i < 10; ++i) {
            arr[i] = StdRandom.uniform();
        }

        Merge_BU.sort(arr);

        for(double d : arr)
            StdOut.print(d + " ");
    }
}
