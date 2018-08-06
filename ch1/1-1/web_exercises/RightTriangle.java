// Copyright [2018] <mituh>
// RightTriangle.java

public class RightTriangle {
  public static void main(String[] args) {
    /*
     * 给定a, b, c构成直角三角形, c为斜边
     * 圆心pt是边c中点, 半径r是边c的一半
     * 以直角点为原点pt, 调用StdDraw绘图库绘图
     */
    double a = 3, b = 4;        // c = 5;
    double c_x = 15, c_y = 15;

    double b_x = c_x + b, b_y = c_y;
    double a_x = c_x, a_y = c_y + a;
    double[] x_arr = {b_x, c_x, a_x};
    double[] y_arr = {b_y, c_y, a_y};


    int N = 30;
    StdDraw.setXscale(0, N);
    StdDraw.setYscale(0, N);
    StdDraw.setPenRadius(.001);
    StdDraw.setPenColor(StdDraw.BLUE);

    StdDraw.circle((a_x + b_x) / 2, (a_y + b_y) / 2, Math.sqrt(a*a + b*b) / 2);
    StdDraw.polygon(x_arr, y_arr);
  }
}
