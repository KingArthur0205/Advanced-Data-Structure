/* *****************************************************************************
 *  Name:              Alan Turing
 *  Coursera User ID:  123456
 *  Last modified:     1/1/2019
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // Record the number of sites opened when percolates
    private final double[] sitesOpened;
    private final int numTrials;
    private static final double CONFIDENCE_95 = 1.96;
    /*
     * 1. Initialize sitesOpened to int[trials]
     * 2. Loop trials times
     * 3. In each iteration:
     *      a. Create a new curTest = Percolation(n)
     *      b. While curTest doesn't percolates, generate a random row and col within 0 and n using StdRandom.
     *      c. If the generated row and col is open, generate a new set of row and col.
     *      d. Open the generated row and col.
     *      e. Add the number of open sites to sitesOpened
     */
    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials) {
        if (n <= 0 || trials <= 0)
            throw new IllegalArgumentException();

        numTrials = trials;
        sitesOpened = new double[trials];
        Percolation curTest;

        for (int i = 0; i < trials; ++i) {
            curTest = new Percolation(n);
            while (!curTest.percolates()) {
                int row, col;
                do {
                    row = StdRandom.uniform(n) + 1;
                    col = StdRandom.uniform(n) + 1;
                } while (curTest.isOpen(row, col));
                curTest.open(row, col);
            }
            sitesOpened[i] = (double) curTest.numberOfOpenSites() / (n * n);
        }
    }

    /*
     * 1. Caculate the sum of all values in sitesOpened
     * 2. Divide by numTrials
     */
    // sample mean of percolation threshold
    public double mean() {
       return StdStats.mean(sitesOpened);
    }

    /*
     * 1. Get the mean
     * 2. Iterate through the array, in each iteration, calculate sqrt(sitesOpened[i] - mean) and add to the sum
     * 3. Divid the sum by (numTrials - 1)
     */
    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(sitesOpened);
    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - CONFIDENCE_95 * stddev() / Math.sqrt(numTrials);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + CONFIDENCE_95 * stddev() / Math.sqrt(numTrials);
    }

    /**
     * Purposely left empty
     * @param args
     */
    public static void main(String[] args) {
        // Empty
    }
}