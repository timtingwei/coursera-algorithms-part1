// Copyright [2018] <mituh>
// VisualAccumulator.java
// 可视化累加器

public class VisualAccumulator {
  private double total;
  private int n;
  public VisualAccumulator(int trials, double max) {
    StdDraw.setXscale(0, trials);        // setXscale(0, x_max);
    StdDraw.setYscale(0, max);
    StdDraw.setPenRadius(.005);
  }

  public void addDataValue(double val) {
    total += val;
    n++;
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.point(n, val);
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.point(n, total / n);
  }

  public double mean() {
    return total/n;
  }

  public String toString() {
    return "Mean (" + n + " values): "
        +  String.format("%7.5f", mean());
  }

  public static void main(String[] args) {
    int T = Integer.parseInt(args[0]);
    VisualAccumulator a = new VisualAccumulator(T, 1.0);
    for (int i = 0; i < T; i++) {
      a.addDataValue(Math.random());
    }
    System.out.println(a);
  }
}
