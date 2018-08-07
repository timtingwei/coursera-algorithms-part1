// Copyright [2018] <mituh>
// Accumulator.java
// 实现一个累加器

public class Accumulator {
  private double val;
  private int n;
  public Accumulator() {}    // 创建一个累加器
  public void addDataValue(double val) {   // 添加一个新的数据值
    n++;
    this.val += val;
  }

  public double mean() { return val / n;}

  public String toString() {
    // return "Mean (" + n + " values): " + mean();
    return "Mean (" + n + " values): "
        + String.format("%7.5f", mean());
  }

  public static void main(String[] args) {
    int T = Integer.parseInt(args[0]);
    Accumulator a = new Accumulator();
    for (int i = 0; i < T; i++) {
      a.addDataValue(Math.random());
    }
    System.out.println(a);
  }
}
