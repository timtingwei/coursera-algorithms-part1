// Copyright [2018] <mituh>
// Insertion.java
// 实现插入排序

public class Insertion {
  public static void sort(Comparable[] a) {
    int N = a.length;
    for (int i = 1; i < N; i++) {
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
      System.out.println(a[i]);
    }
    System.out.println();
  }


  public static void main(String[] args) {
    int N = 5;
    Transaction[] s = new Transaction[5];
    Transaction t1 = new Transaction("mituh", new Date("1996/12/03"), 31.3);
    Transaction t2 = new Transaction("tim2", new Date("1996/12/02"), 29.3);
    Transaction t3 = new Transaction("tim5", new Date("1926/12/02"), 79.9);
    Transaction t4 = new Transaction("timmy", new Date("1926/11/02"), 11.99);
    Transaction t5 = new Transaction("cooper", new Date("1926/12/02"), 18.2);
    s[0] = t1; s[1] = t2; s[2] = t3; s[3] = t4; s[4] = t5;
    sort(s);
    assert(isSorted(s));
    show(s);
  }
}
