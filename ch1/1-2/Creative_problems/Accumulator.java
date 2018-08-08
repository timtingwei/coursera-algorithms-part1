// Copyright [2018] <mituh>
// Accumulator.java

public class Accumulator {
  private double m;
  private double s;
  private int N;
  public void addDataValue(double x) {
    N++;
    s = s + 1.0 * (N-1) / N * (x - m) * (x - m);
    m = m + (x - m) / N;
  }
  public double mean()
  { return m;}
  public double var()
  {return s/(N-1); }
  public double stddev()
  { return Math.sqrt(this.var()); }
  public int count()
  { return N;}
  public String toString() {
    return "n = " + N + ", mean = " + mean() + ", stddev = " + stddev();
  }

  public static void main(String[] args) {
    Accumulator stats = new Accumulator();
    while (!StdIn.isEmpty()) {
      double x = StdIn.readDouble();
      stats.addDataValue(x);
    }
    
    StdOut.printf("n      = %d\n",   stats.count());
    StdOut.printf("mean   = %.5f\n", stats.mean());
    StdOut.printf("stddev = %.5f\n", stats.stddev());
    StdOut.printf("var    = %.5f\n", stats.var());
    StdOut.println(stats);
  }
}
