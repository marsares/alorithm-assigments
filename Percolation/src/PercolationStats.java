/**
 * <p>
 * this program is to estimate the value of the percolation threshold viaMonte
 * Carlo simulation.
 * </p>
 * 
 * @author YuanBo
 * @version 1.0
 */
public class PercolationStats {
	private double mean;
	private double stddev;
	private double confidenceLo;
	private double confidenceHi;
	private double[] fractions;
	private static double CONFIDENCE_NUMBER = 1.96;

	/**
	 * <p>
	 * perform T independent computational experiments on an N-by-N grid
	 * </p>
	 * 
	 * @param N
	 * @param T
	 */
	public PercolationStats(int N, int T) {
		if (T <= 0) {
			String errMsg = "T is illegal.";
			IllegalArgumentException ex = new IllegalArgumentException(errMsg);
			throw ex;
		}
		fractions = new double[T];
		for (int t = 0; t < T; t++) {
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
			fractions[t] = count / amount;
		}
		mean = StdStats.mean(fractions);
		stddev = StdStats.stddev(fractions);
		confidenceLo = mean - CONFIDENCE_NUMBER * stddev / Math.sqrt(T);
		confidenceHi = mean + CONFIDENCE_NUMBER * stddev / Math.sqrt(T);
	}

	/**
	 * <p>
	 * sample mean of percolation threshold
	 * </p>
	 * 
	 * @return
	 */
	public double mean() {
		return mean;
	}

	/**
	 * <p>
	 * sample standard deviation of percolation threshold
	 * </p>
	 * 
	 * @return
	 */
	public double stddev() {
		return stddev;
	}

	/**
	 * <p>
	 * returns lower bound of the 95% confidence interval
	 * </p>
	 * 
	 * @return
	 */
	public double confidenceLo() {
		return confidenceLo;
	}

	/**
	 * <p>
	 * returns upper bound of the 95% confidence interval
	 * </p>
	 * 
	 * @return
	 */
	public double confidenceHi() {
		return confidenceHi;
	}

	public static void main(String[] args) {
		int N = Integer.parseInt(args[0]);
		int T = Integer.parseInt(args[1]);
		PercolationStats p = new PercolationStats(N, T);
		System.out.println("mean                    = " + p.mean());
		System.out.println("stddev                  = " + p.stddev());
		System.out.println("95% confidence interval = " + p.confidenceLo()
				+ "," + p.confidenceHi());
	}
}
