import edu.princeton.cs.algs4.StdRandom;

public class QuickSelect {
    public static Comparable select(Comparable[] arr, int k) {
        StdRandom.shuffle(arr);
        int lo = 0, hi = arr.length - 1;

        while (lo < hi) {
            int j = partition(arr, lo, hi);
            if (j > k) hi = j - 1;
            else if (j < k) lo = j + 1;
            else return arr[k];
        }
        return arr[k];
    }

    private static int partition(Comparable[] arr, int lo, int hi) {
        int i = lo, j = hi + 1;

        while (true) {
            while (less(arr[++i], arr[lo]))
                if (i == hi) break;

            while (less(arr[lo], arr[--j]))
                if (j == lo)
                    break;

            if (i >= j) break;
            exch(arr, i, j);
        }
        exch(arr, lo, j);
        return j;
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
        Double[] arr = new Double[10];
        for (int i = 0; i < arr.length; ++i) {
            arr[i] = StdRandom.uniform();
        }

        for (double d : arr)
            System.out.print(d + " ");
        System.out.println();

        System.out.println(QuickSelect.select(arr,3));


    }
}
