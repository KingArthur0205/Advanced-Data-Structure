import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class QuickThreeWay {
    public static void sort(Comparable[] arr) {
        StdRandom.shuffle(arr);
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(Comparable[] arr, int lo, int hi) {
        if (hi <= lo) return;
        int i = lo, gt = hi, lt = lo;

        Comparable v = arr[lo];
        while (i <= gt) {
            int cmp = arr[i].compareTo(v);
            if (cmp < 0) exch(arr, lt++, i++);
            else if (cmp > 0) exch(arr, gt--, i);
            else i++;
        }
        sort(arr, lo, lt - 1);
        sort(arr, gt + 1, hi);
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

        QuickThreeWay.sort(arr);

        for (int i = 0; i < N; ++i) {
            StdOut.print(arr[i] + " ");
        }
    }

}