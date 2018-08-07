// Copyright [2018] <mituh>
// Point2D_01.java

public class Point2D_01 {
  private final double x;
  private final double y;
  public Point2D_01(double val1, double val2) {
    x = val1; y = val2;
  }

  public static double getDist(Point2D_01 pt1, Point2D_01 pt2) {
    return Math.sqrt(Math.pow((pt1.x - pt2.x), 2)
                     + Math.pow((pt1.y - pt2.y), 2));
  }
  
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);  // N是随机点个数
    StdDraw.setXscale(0, 100);
    StdDraw.setYscale(0, 100);
    StdDraw.setPenRadius(.003);
    StdDraw.setPenColor(StdDraw.BLACK);
    StdDraw.square(50, 50, 25);
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.setPenRadius(.01);
    Point2D_01[] a = new Point2D_01[N];   // 最多存放100个点
    for (int i = 0; i < N; i++) {
      double x = 25 + Math.random() * 50;
      double y = 25 + Math.random() * 50;
      a[i] = new Point2D_01(x, y);
      StdDraw.point(x, y);
    }

    double min_max = Double.MAX_VALUE;    // 最近的距离
    int p = 0, q = 0;
    for (int i = 0; i < N; i++) {
      for (int j = 0; j < N; j++) {
        if (i == j) continue;
        double dist = Point2D_01.getDist(a[i], a[j]);
        if (dist < min_max) {
          min_max = dist;
          p = i; q = j;
        }
      }
    }
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.point(a[p].x, a[p].y);
    StdDraw.point(a[q].x, a[q].y);
    System.out.println("min distance is " +
                       String.format("%.5f", min_max));
  }
}
