import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class KnuthShuffle {
    public static void shuffle(int[] arr) {
        int N = arr.length;

        for(int i = 0; i < N; ++i) {
            int r = StdRandom.uniform(i + 1);
            exch(arr, i, r);
        }
    }

    private static void exch(int[] arr, int i, int j) {
        int swap = arr[i];
        arr[i] = arr[j];
        arr[j] = swap;
    }

    public static void main(String[] args) {
        int N = 10;
        int[] arr = new int[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = i;
        }
        KnuthShuffle.shuffle(arr);
        for(int elem : arr)
            StdOut.print(elem + " ");
    }
}
