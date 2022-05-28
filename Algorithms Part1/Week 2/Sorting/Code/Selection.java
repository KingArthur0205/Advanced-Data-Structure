import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Selection {
    public static void sort(Comparable[] arr) {
        int N = arr.length;

        for (int i = 0; i < N; ++i) {
            int min = i;
            for (int j = i + 1; j < N; ++j) {
                if (less(arr[j], arr[min]))
                    min = j;
            }
            exch(arr, i, min);
        }
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

        Selection.sort(arr);

        for (int i = 0; i < N; ++i) {
            StdOut.print(arr[i] + " ");
        }
    }
}