// Copyright [2018] <mituh>
// Selection.java

public class Selection {
  public static void sort(Comparable[] a) { 
    for (int i = 0; i < a.length; i++) {         // 因为j=i+1, 内循环不会比较最后一个元素
      int min = i;
      for (int j = i+1; j < a.length; j++) {     // 略去与自己的比较
        if (less(a[j], a[min])) min = j;         // less抽象成方法
      }
      exch(a, i, min);
    }
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;                   // less方法中调用compareTo比较
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
