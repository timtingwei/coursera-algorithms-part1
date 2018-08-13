// Copyright [2018] <mituh>
// TraceInsertion.java
// 为插入排序插入排序棒状图

public class TraceInsertion {

  public static void sort(String[] a) {
    int N = a.length;
    for (int i = 1; i < N; i++) {
      for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
        exch(a, j, j-1);
      }
      show(a);
    }
  }

  public static boolean less(String v, String w) {
    return v.compareTo(w) < 0;
  }

  public static void exch(String[] a, int i, int j) {
    String tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }

  public static boolean isSorted(String[] a) {
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i-1])) return false;
    }
    return true;
  }

  public static void tail(String[] a) {
    // ..作为结束的图片
  }

  public static void show(String[] a) {
    
    int max_p = 0, min_p = 0;
    int N = a.length;
    for (int i = 0; i < N; i++) {
      if (less(a[max_p], a[i])) max_p = i;
      if (less(a[i], a[min_p])) min_p = i;
    }
    String max = a[max_p]; String min = a[min_p];
    // 如果没有重载运算符的话, 就需要计算出, key值最小元素和最大元素之间差几个档次,
    // 答案的解决方法竟然是把Comparable改写成String, 当场晕倒
    

    double scale_x = 100; double scale_y = 100;
    double max_x = scale_x * 0.6;                   // 所有柱形x方向上的总长
    double max_y = max_x * 0.3;                     // 所有柱形y方向上的最大长度
    double offset_scale = 0.5;                // 间距是棒宽的0.5倍
    double width = max_x/(offset_scale*(N-1) + N);
    double offset = (offset_scale+1) * width;     // 棒和棒的中心间距

    double dist_h = (max_y - 0.1 * max_y) / (max.charAt(0) - min.charAt(0));      // 渐进的差值, 最短为0.1
    double start_x = 0.2 * scale_x;
    double start_y = 0.5 * scale_y;     // error
    StdDraw.setXscale(0, scale_x);
    StdDraw.setYscale(0, scale_y);
    for (int i = 0; i < N; i++) {
      double h = (a[i].charAt(0) - min.charAt(0)) * dist_h + (0.1 * max_y);
      double half_width = width / 2;
      double half_height = h / 2;
      double center_x = start_x + i * offset;
      double center_y = start_y + half_height;
      StdDraw.filledRectangle(center_x, center_y, half_width, half_height);
      StdDraw.pause(100);   // 暂停1s
    }
    StdDraw.pause(1000);
    StdDraw.clear();
  }


  public static void main(String[] args) {
    String[] s = {"S", "O", "R", "T", "E", "X", "A", "M", "P", "L", "E"};
    sort(s);
    assert(isSorted(s));
    // show(s);
  }
}
