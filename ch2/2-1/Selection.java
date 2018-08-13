// Copyright [2018] <mituh>
// Selection.java

public class Selection {
  public void sort(int[] a) { 
    for (int i = 0; i < a.length - 1; i++) {
      int pMin = i;
      for (int j = i; j < a.length; j++) {
        if (a[j] < a[pMin]) pMin = j;
      }
      if (pMin != i) exch(a, i, pMin);
    }
  }

  public void exch(int[] a, int p, int q) {
    int tmp = a[p]; a[p] = a[q]; a[q] = tmp;
  }

  public static void main(String[] args) {
    int[] a = {3, 5, 2, 9, 6, 1};
    Selection s = new Selection();
    s.sort(a);
    for (int i = 0; i < a.length; i++) {
      System.out.printf(a[i] + " ");
    }
    System.out.println();

  }
}
