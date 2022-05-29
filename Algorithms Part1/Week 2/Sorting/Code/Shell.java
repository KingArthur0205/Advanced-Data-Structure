import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Shell {
    public static void sort(Comparable[] arr) {
        int N = arr.length;
        int h = 1;
        while (h < N / 3)
            h = 3 * h + 1;

        // Execute log3(n) times
        while (h >= 1) {
            for(int i = h; i < N; ++i) {
                // Stop when the h-subarray is already sorted
                for (int j = i; j >= h && less(arr[j], arr[j - h]); j -= h)
                    exch(arr, j, j - h);
            }
            // Move to next increment
            h /= 3;
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

        Shell.sort(arr);

        for (int i = 0; i < N; ++i) {
            StdOut.print(arr[i] + " ");
        }
    }
}
