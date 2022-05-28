import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Insertion {
    public static void sort(Comparable[] arr) {
        int N = arr.length;

        for (int i = 0; i < N; ++i) {
            for (int j = i; j > 0; --j) {
                if (less(arr[j], arr[j - 1]))
                    exch(arr, j, j - 1);
                else
                    break;
            }
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

        Insertion.sort(arr);

        for (int i = 0; i < N; ++i) {
            StdOut.print(arr[i] + " ");
        }
    }
}
