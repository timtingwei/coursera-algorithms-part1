// Copyright [2018] <mituh>
// QuickUnionUF.java
// 实现quick-union算法, 并画出分摊成本图像

import java.util.Scanner;
public class QuickUnionUF {

  private int[] id;
  private int count;
  private long visit;
  public QuickUnionUF(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;     // 每个触点独立成为一个连通分量
    }
    count = n;
    visit = 0;       // 构造时, 访问数组的次数清空
  }

  // 返回连通分量计数
  public int count() { return count;}

  // 返回当前索引的根触点
  public int find(int p) {
    while (p != id[p]) {    // 不清楚这次访问要不要记录??
      p = id[p];
      visit++;
    }
    return id[p];
  }

  // 触点p和触点q是否已经连接
  public boolean connected(int p, int q) {
    // // 是否是同一个根结点
    return find(p) == find(q);
  }

  // 连接触点p和q
  public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) return;

    id[pRoot] = qRoot;     // 将q作为p的根结点, 这里还未加权
    visit++;
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
    QuickUnionUF uf = new QuickUnionUF(N);

    int i = 1;
    long total = 0;
    long cost = 0;
    uf.setDraw(N, 100);

    while (read.hasNext()) {
      int p = read.nextInt();
      int q = read.nextInt();
      if (uf.connected(q, p)) continue;

      cost = uf.getVisit();                // 调用union之前的计数
      
      uf.union(p, q);                      // 注意与union(p, q)的区别
      total  = uf.getVisit();              // 总的计数
      cost = total - cost;                 // union调用后改变的计数
      uf.drawCost(i, cost);
// System.out.println(cost);
      uf.drawTotal(i, total);

      System.out.println(p + " " + q);
      i++;
    }
    System.out.println(uf.count() + " components");
  }
}
