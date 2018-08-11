// Copyright [2018] <mituh>
// PercolationStats.java


import java.util.Scanner;
public class PercolationStats {
  private double[] a;
  // private int n;
  public PercolationStats(int n, int trials) {    // perform trials independent experiments on an n-by-n grid
    a = new double[trials];
    for (int i = 0; i < trials; i++) {
      Percolation percolation = new Percolation(n);
      while (!percolation.percolates()) {
        int p = StdRandom.uniform(n);
        int q = StdRandom.uniform(n);
        percolation.open(p, q);
      }
      a[i] = (double)percolation.numberOfOpenSites() / (n*n);
    }
    
  }
  public double mean() {                          // sample mean of percolation threshold
    return StdStats.mean(a);
  }
  public double stddev() {                        // sample standard deviation of percolation threshold
    return StdStats.stddev(a);
  }
  public double confidenceLo() {                  // low  endpoint of 95% confidence interval
    return StdStats.mean(a);   // ??
  }
  public double confidenceHi() {                 // high endpoint of 95% confidence interval
    return StdStats.mean(a);
  }

  public static void main(String[] args) {        // test client (described below)
    Scanner read = new Scanner(System.in);
    int n = read.nextInt();
    int trials = read.nextInt();
    PercolationStats percolationStats = new PercolationStats(n, trials);
    System.out.println("mean                   = " + percolationStats.mean());
    System.out.println("stddev                 = " + percolationStats.stddev());
  }
}
