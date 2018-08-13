// Copyright [2018] <mituh>
// Shell.java
// 实现希尔排序代码

public class Shell {
  public static void sort(Comparable[] a) {
    int N = a.length;
    int h = 1;
    while (h < N/3) h = 3*h+1;     // 1, 4, 13, 40, 121, 364, 1093, ..
    while (h >= 1) {
      // 将数组变为h有序
      for (int i = h; i < N; i++) {
        // 将a[i]插入到a[i-h], a[i-2*h], a[i-3*h]之中
        for (int j = i; j >= h && less(a[j], a[j-h]); j -= h)
          exch(a, j, j-h);
      }
      h = h/3;
    }
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Comparable[] a, int i, int j) {
    Comparable tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }

  public static boolean isSorted(Comparable[] a) {
    // 测试数组元素是否有序
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i-1])) return false;
    }
    return true;
  }

  public static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.printf(a[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Integer[] a = {3, 5, 2, 9, 6, 1};
    sort(a);
    assert(isSorted(a));
    show(a);
  }

  
}
