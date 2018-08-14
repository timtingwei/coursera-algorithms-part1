// Copyright [2018] <mituh>
// 二分查找两个版本以及选择, 插入, 希尔排序

public class BinarySearch {
  public static int BinarySearch_noRecursive(int[] a, int key, int lo, int hi) {
    // 非递归
    while (lo <= hi) {
      int mid = lo + (hi - lo) / 2;
      if      (key < a[mid]) hi = mid - 1;
      else if (key > a[mid]) lo = mid + 1;
      else                   return mid;
    }
    return -1;
  }

  public static int BinarySearch_recursive(int[] a, int key, int lo, int hi) {
    // 递归
    if (hi < lo) return -1;
    int mid = lo + (hi - lo) / 2;
    if      (key < a[mid]) return BinarySearch_recursive(a, key, lo, mid-1);
    else if (key > a[mid]) return BinarySearch_recursive(a, key, mid + 1, hi);
    return mid;
  }

  public static void insertionSort(int[] a) {
    int N = a.length;
    for (int i = 1; i < N; i++) {
      for (int j = i; j > 0 && (a[j] < a[j-1]); j--) {
        swap(a, j, j-1);
      }
    }
  }

  public static void selectionSort(int[] a) {
    int N = a.length;
    for (int i = 0; i < N; i++) {
      int min = i;
      for (int j = i+1; j < N; j++)
        if (a[j] < a[min]) min = j;
      swap(a, i, min);
    }
  }

  public static void shellSort(int[] a) {
    int N = a.length;
    int h = 1;
    while (h < N/3) h = h*3+1;   // 1, 4, 13, 40, 121, 364, 1093
    while (h >= 1) {
      for (int i = h; i < N; i++) {
        for (int j = i; j >= h && a[j] < a[j-h]; j -= h)
          swap(a, j, j-h);
      }
      h = h/3;       // h在while循环中缩小
    }
  }

  public static void swap(int[] a, int i, int j) {
    int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }

  public static boolean isSorted(int[] a) {
    for (int i = 1; i < a.length; i++) {
      if (a[i] < a[i-1]) return false;
    }
    return true;
  }
  public static void show(int[] a) {
    for (int i = 0; i < a.length; i++)
      System.out.printf(a[i] + " ");
    System.out.println();
  }

  public static void main(String[] args) {
    int[] a = {2, 3, 5, 8, 10, -2, 4};
    // selectionSort(a);
    // insertionSort(a);
    shellSort(a);
    assert(isSorted(a));
    show(a);
    int pos1 = BinarySearch_noRecursive(a, 7, 0, a.length-1);
    int pos2 = BinarySearch_recursive(a, 7, 0, a.length-1);
    System.out.println(pos1);
    System.out.println(pos2);
  }
}
