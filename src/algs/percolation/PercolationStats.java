package algs.percolation;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	private double[] countTrials;

	public PercolationStats(int n, int trials) {
		// perform trials independent experiments on an n-by-n grid
		// The constructor should throw a java.lang.IllegalArgumentException if
		// either n ≤ 0 or trials ≤ 0.

		if (n <= 0 || trials <= 0)
			throw new java.lang.IllegalArgumentException();
		countTrials = new double[trials];
		for (int i = 0; i < trials; i++) {
			int c = monteCarloSimulation(n);
			countTrials[i] = (double) c / (n * n);
		}
	}

	private int monteCarloSimulation(int n) {
		int c = 0;
		Percolation p = new Percolation(n);
		while (!p.percolates()) {
			int i = 1 + StdRandom.uniform(n);
			int j = 1 + StdRandom.uniform(n);
			if (!p.isOpen(i, j)) {
				c++;
				p.open(i, j);
			}
		}
		return c;
	}

	public double mean() {
		// sample mean of percolation threshold
		return StdStats.mean(countTrials);
	}

	public double stddev() {
		// sample standard deviation of percolation threshold
		return StdStats.stddev(countTrials);
	}

	public double confidenceLo() {
		// low endpoint of 95% confidence interval
		return mean() - ((1.96 * stddev()) / Math.sqrt(countTrials.length));
	}

	public double confidenceHi() {
		// high endpoint of 95% confidence interval
		return mean() + ((1.96 * stddev()) / Math.sqrt(countTrials.length));
	}

	public static void main(String[] args) {
		Integer n = Integer.parseInt(args[0]);
		Integer trials = Integer.parseInt(args[1]);
		PercolationStats ps;
		ps = new PercolationStats(n, trials);
		String confidence = ps.confidenceLo() + ", " + ps.confidenceHi();
		StdOut.println("mean                    = " + ps.mean());
		StdOut.println("stddev                  = " + ps.stddev());
		StdOut.println("95% confidence interval = " + confidence);
	}

}