// Copyright [2018] <mituh>

import java.util.Scanner;

public class Sattolo {

  public static void simple(int[] a, int N) {
    // 普通的随机置乱数组
    /* i from 0->N-1
     * 生成一个随机数j
     * 如果 j != i, 交换a[i], a[j]
     */
    int i = 0;
    while (i < N - 1) {
      int j = (int)(StdRandom.random() * N);
      if (i != j) {
        int t = a[i]; a[i] = a[j]; a[j] = t;
        i++;
      }
    }
  }

  // 以下才是Sattolo置乱算法
  public static void cycle(Object[] a) {
    int n = a.length;
    for (int i = n; i > 1; i--) {
      int r = (int)(Math.random() * (i-1));
      Object swap;
      swap = a[r]; a[r] = a[i-1]; a[i-1] = swap;
    }
  }
  
  public static void foo(String args[]) {
    
    int[] a = new int[50];
    Scanner read = new Scanner(System.in);    // 别忘记System.in
    int cnt = 0;
    while (read.hasNextInt()) {
      a[cnt++] = read.nextInt();
    }
    simple(a, cnt);
    for (int i = 0; i < cnt; i++) {
      System.out.printf("%d ", a[i]);
    }
    System.out.println();
  }

  public static void main(String args[]) {
    // read the data
    String[] a = StdIn.readAllStrings();
    
    // shuffle the array
    Sattolo.cycle(a);

    // print the result
    for (int i = 0; i < a.length; i++) {
      System.out.println(a[i]);
    }
  }
}

/*
case 1:
1
2
3
4
5
6
7
*/
