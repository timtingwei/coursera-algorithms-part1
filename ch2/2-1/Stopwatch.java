// Copyright[2018] <mituh>
// Stopwatch
// 实现一个计时器类


public class Stopwatch {
  private long start;
  public Stopwatch() {
    start = System.currentTimeMillis();
  }
  public double elapsedTime() {
    long now = System.currentTimeMillis();
    return (now - start) / 1000.0;
  }

  /*
  public static void main(String[] args) {
    double total = 0.0;
    Stopwatch timer = new Stopwatch();
    int N = 100000;
    Double[] a = new Double[N];
    for (int i = 0; i < N; i++) {
      a[i] = StdRandom.uniform();
    }
    Insertion.sort(a);
    total += timer.elapsedTime();
    System.out.println("total time = " + total);    // total time = 19.729
  }
  */
}
