// Copyright [2018] <mituh>
// SortCompare.java
// 实现多个排序算法之间的比较

public class SortCompare {
  public static double time(String alg, Double[] a) {
    Stopwatch timer = new Stopwatch();
    if (alg.equals("Insertion"))    Insertion.sort(a);
    if (alg.equals("Selection"))    Selection.sort(a);
    // if (alg.equals("Shell"))     Shell.sort(a);
    // if (alg.equals("Merge"))     Merge.sort(a);
    // if (alg.equals("Quick"))     Quick.sort(a);
    // if (alg.equals("Heap"))      Heap.sort(a);
    return timer.elapsedTime();
  }

  public static double timeRandomInput(String alg, int N, int T) {
    // 使用算法alg对T个长度为N的数组排序
    double total = 0.0;
    Double[] a = new Double[N];
    for (int t = 0; t < T; t++) {
      for (int j = 0; j < N; j++)
        a[j] = StdRandom.uniform();
      total += time(alg, a);
    }
    System.out.println(total);
    return total;
  }

  public static void main(String[] args) {
    String alg1 = args[0];
    String alg2 = args[1];
    int N = Integer.parseInt(args[2]);
    int T = Integer.parseInt(args[3]);
    double t1 = timeRandomInput(alg1, N, T);
    double t2 = timeRandomInput(alg2, N, T);
    System.out.printf("For %d random Double\n  %s is", N, alg1);
    System.out.printf(" %.1f times faster than %s\n", t2/t1, alg2);
  }
}
