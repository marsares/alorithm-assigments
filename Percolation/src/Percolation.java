/**
 * <p>
 * this program is to estimate the value of the percolation threshold
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
public class Percolation {
	private int[] state;
	private WeightedQuickUnionUF wquf;
	private WeightedQuickUnionUF wquf1;
	private int count;

	/**
	 * <p>
	 * create N-by-N grid, with all sites blocked
	 * </p>
	 * 
	 * @param N
	 *            the size of the grid
	 */
	public Percolation(int N) {
		if (N <= 0) {
			String errMsg = "N is illegal.";
			IllegalArgumentException ex = new IllegalArgumentException(errMsg);
			throw ex;
		}
		wquf = new WeightedQuickUnionUF(N * N + 2);
		wquf1 = new WeightedQuickUnionUF(N * N + 2);
		state = new int[N * N];
		count = N;
		for (int i = 0; i < N * N; i++) {
			state[i] = 0;
		}
	}

	/**
	 * <p>
	 * open site (row i, column j) if it is not already
	 * </p>
	 * 
	 * @param i
	 *            row number
	 * @param j
	 *            column number
	 */
	public void open(int i, int j) {
		check(i, j);
		int location = xyTo1D(i, j);
		state[location] = 1;
		if (isexisted(i, j - 1)) {
			if (isOpen(i, j - 1)) {
				wquf.union(location, xyTo1D(i, j - 1));
				wquf1.union(location, xyTo1D(i, j - 1));
			}
		}
		if (isexisted(i, j + 1)) {
			if (isOpen(i, j + 1)) {
				wquf.union(location, xyTo1D(i, j + 1));
				wquf1.union(location, xyTo1D(i, j + 1));
			}
		}
		if (isexisted(i - 1, j)) {
			if (isOpen(i - 1, j)) {
				wquf.union(location, xyTo1D(i - 1, j));
				wquf1.union(location, xyTo1D(i - 1, j));
			}
		}
		if (isexisted(i + 1, j)) {
			if (isOpen(i + 1, j)) {
				wquf.union(location, xyTo1D(i + 1, j));
				wquf1.union(location, xyTo1D(i + 1, j));
			}
		}
		if (i == 1) {
			wquf.union(count * count, location);
			wquf1.union(count * count, location);
		}
		if (i == count) {
			wquf.union(count * count + 1, location);
		}
	}

	/**
	 * <p>
	 * check if site (row i, column j) is open
	 * </p>
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		check(i, j);
		if (state[xyTo1D(i, j)] == 1)
			return true;
		else
			return false;
	}

	/**
	 * <p>
	 * check if site (row i, column j) if full
	 * </p>
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) {
		check(i, j);
		int location = xyTo1D(i, j);
		if (isOpen(i, j)) {
			if (wquf1.connected(location, count * count))
				return true;
			else
				return false;
		} else
			return false;
	}

	/**
	 * <p>
	 * check if the system percolates
	 * </p>
	 * 
	 * @return
	 */
	public boolean percolates() {
		if (wquf.connected(count * count, count * count + 1))
			return true;
		else
			return false;
	}

	private int xyTo1D(int i, int j) {
		return (i - 1) * count + j - 1;
	}

	private void check(int i, int j) {
		if (i < 1 || i > count || j < 1 || j > count) {
			String errMsg = "i or j is outside this range.";
			IndexOutOfBoundsException  ex = new IndexOutOfBoundsException (errMsg);
			throw ex;
		}
	}

	private boolean isexisted(int i, int j) {
		if (i >= 1 && i <= count && j >= 1 && j <= count)
			return true;
		else
			return false;
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		Percolation p = new Percolation(N);
		while (!p.percolates()) {
			int i = StdRandom.uniform(N) + 1;
			int j = StdRandom.uniform(N) + 1;
			p.open(i, j);
		}
		int count = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (p.isOpen(i, j))
					count++;
			}
		}
		double amount = N * N;
		double fraction = count / amount;
		System.out.println("percolation threshold is " + fraction + ", the "
				+ count + "th site is opened");
	}

}
