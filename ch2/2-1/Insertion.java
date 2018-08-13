// Copyright [2018] <mituh>
// Insertion.java
// 实现插入排序

public class Insertion {
  /* // 我的尝试
  public static void sort(Comparable[] a) {
    int p = 1;
    while (p < a.length) {
      if (less(a[p], a[p-1])) {
        for (int i = 0; i < p; i++) {
          if (less(a[p], a[i])) {
            Comparable tmp = a[p];
            for (int j = p; j > i; j--) {
              a[j] = a[j-1];
            }
            a[i] = tmp;
            break;
          }
        }
        show(a);
      }
      p++;
    }
  }
  */
  public static void sort(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
        exch(a, j, j-1);
      }
    }
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(Comparable[] a, int i, int j) {
    Comparable tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }

  public static boolean isSorted(Comparable[] a) {
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
    String[] s = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
    // Integer[] s = {1, 2, 3, 4, 7, 6};
    sort(s);
    assert(isSorted(s));
    show(s);
  }
}
