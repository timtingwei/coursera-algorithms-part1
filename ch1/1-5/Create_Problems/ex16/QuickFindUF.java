// Copyright [2018] <mituh>
// QuickFindUF.java
// 实现quick-find算法, 并画出分摊成本图像

import java.util.Scanner;
public class QuickFindUF {

  private int[] id;
  private int count;
  private long visit;
  public QuickFindUF(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;     // 每个触点独立成为一个连通分量
    }
    count = n;
    visit = 0;       // 构造时, 访问数组的次数清空
  }

  // 返回连通分量计数
  public int count() { return count;}

  // 返回索引为p的触点, 所属的连通分量
  public int find(int p) {
    visit++;
    return id[p];
  }

  // 触点p和触点q是否已经连接
  public boolean connected(int p, int q) {
    // 判断p, f是否相连, 是否属于同一个连通分量   // 是否是同一个根结点
    return find(p) == find(q);
  }

  // 连接触点p和q
  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q);
    if (pID == qID) return;
    for (int i = 0; i < id.length; i++) {
      visit++;        // id[i]访问一次
      if (id[i] == pID) {
        id[i] = qID;        // 将与p相同的连通分量, 都改为q的连通分量
        visit++;      // id[i]重新访问一次
      }
    }
    --count;
  }

  public long getVisit() {
    // 返回当前visit的值
    return visit;
  }

  public void setDraw(int max_x, int max_y) {
    StdDraw.setXscale(0, max_x);
    StdDraw.setYscale(-max_y, max_y);
    StdDraw.setPenRadius(.003);
  }
  
  public void drawCost(int i, long cost) {
    // 画出(i, cost)这个点, 灰色
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.point(i, cost);
  }

  public void drawTotal(int i, long total) {
    // 画出(i, total/i) 红色
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.point(i, (double)total / i);
  }
  
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    QuickFindUF uf = new QuickFindUF(N);

    int i = 1;
    long total = 0;
    long cost = 0;
    uf.setDraw(N, 2000);

    while (read.hasNext()) {
      int p = read.nextInt();
      int q = read.nextInt();
      if (uf.connected(q, p)) continue;

      cost = uf.getVisit();                // 调用union之前的计数
// System.out.println(cost);
      uf.union(p, q);                      // 注意与union(p, q)的区别
      total  = uf.getVisit();              // 总的计数
      cost = total - cost;                 // union调用后改变的计数
      uf.drawCost(i, cost);
      uf.drawTotal(i, total);

      System.out.println(p + " " + q);
      i++;
    }
    System.out.println(uf.count() + " components");
  }
}
