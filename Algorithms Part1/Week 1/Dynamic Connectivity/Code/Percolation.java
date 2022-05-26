import edu.princeton.cs.algs4.WeightedQuickUnionUF;
public class Percolation {
    // Sites should be initialized to all false
    private boolean[][] sites;
    private int openNum;
    /*
     * uf.parent[0] is the top virtual site
     * uf.parent[n * n + 1] is the bottom virtual site
     * The top virtual site should be connected to sites from 1 to n
     * The bottom virtual site should be connected to sites from n * (n - 1) to n * n
     */
    private final WeightedQuickUnionUF uf;
    private final int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if(n <= 0)
            throw new IllegalArgumentException();

        this.n = n;
        sites = new boolean[n][n];
        openNum = 0;
        uf = new WeightedQuickUnionUF(n * n + 2);

        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < n; ++j)
                sites[i][j] = false;
        }
    }

    /*
     * 1. Check if site[siteRow][siteCol] is already open. If yes, return
     * 2. Set sites[siteRow][siteCol] to true. Increment openNum by 1.
     * 3. Calculate the index of curSite = uf[(siteRow - 1) * n + siteCol + 1]
     * 4. Union with top: If siteRow is greater than 0, and sites[siteRow - 1][siteCol] is open. Union uf[siteRow * n + siteCol + 1] with curSite
     * 5. Union with left: If siteCol is greater than 0, and sites[siteRow][siteCol - 1] is open. Union uf[siteRow * n + (siteCol - 1) + 1]
     * 6. Union with right: If siteCol is less than n - 1, and sites[siteRow][siteCol + 1] is open. Union uf[siteRow * n + (siteCol + 1) + 1]
     * 7. Union with bottom: If siteRow is less than n - 1, and sites[siteRow - 1][siteCol] is open. Union uf[(siteRow + 1) * n + siteCol + 1]
     */
    public void open(int row, int col) {
        if (!checkRange(row, 1, n) || !checkRange(col, 1, n))
            throw new IllegalArgumentException();

        int siteRow = row - 1;
        int siteCol = col - 1;
        if (sites[siteRow][siteCol])
            return;

        sites[siteRow][siteCol] = true;
        openNum++;

        if(siteRow == 0)
            uf.union(0, siteRow * n + siteCol + 1);
        if(siteRow == n -1)
            uf.union(n * n + 1, siteRow * n + siteCol + 1);

        int curSite = siteRow * n + siteCol + 1;
        // Union with top
        if (siteRow > 0 && sites[siteRow - 1][siteCol]) {
            int topSite = (siteRow - 1) * n + siteCol + 1;
            uf.union(curSite, topSite);
        }
        // Union with left
        if (siteCol > 0 && sites[siteRow][siteCol - 1]) {
            int leftSite = (siteRow) * n + (siteCol - 1) + 1;
            uf.union(curSite, leftSite);
        }
        // Union with right
        if (siteCol < n - 1 && sites[siteRow][siteCol + 1]) {
            int rightSite = siteRow * n + (siteCol + 1) + 1;
            uf.union(curSite, rightSite);
        }
        // Union with bottom
        if (siteRow < n - 1 && sites[siteRow + 1][siteCol]) {
            int bottomSite = (siteRow + 1) * n + siteCol + 1;
            uf.union(curSite, bottomSite);
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        if (!checkRange(row, 1, n) || !checkRange(col, 1, n))
            throw new IllegalArgumentException();

        return sites[row - 1][col - 1];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!checkRange(row, 1, n) || !checkRange(col, 1, n))
            throw new IllegalArgumentException();

        int siteRow = row - 1;
        int siteCol = col - 1;
        return isOpen(row, col) && uf.find(0) == uf.find(siteRow * n + siteCol + 1);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return openNum;
    }

    // does the system percolate?
    public boolean percolates() {
        if (n == 1)
            return isOpen(1, 1);
        return uf.find(0) == uf.find(n * n + 1);
    }

    private boolean checkRange(int index, int left, int right) {
        return index >= left && index <= right;
    }

}
