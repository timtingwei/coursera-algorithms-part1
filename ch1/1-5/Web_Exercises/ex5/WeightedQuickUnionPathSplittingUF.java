// Copyright [2018] <mituh>
// WeightedQuickUnionPathSplittingUF.java
// 实现路径压缩加权的quick-union算法(将find()改成splitting版本)

import java.util.Scanner;
public class WeightedQuickUnionPathSplittingUF {
  private int[] parent;
  private int[] size;
  private int count;

  public WeightedQuickUnionPathSplittingUF(int N) {
    parent = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
      size[i] = 1;
    }
    count = N;
  }

  public int count() { return count;}
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int find(int p) {           // 每个结点链到它的祖父
    while (p != parent[p]) {
      int next = parent[p];
      parent[p] = parent[next];
      p = next;
    }
    return p;
  }

  public void union(int p, int q) {
    if (connected(p, q)) return;   // 在判断connected时, 已将子结点与根连接直接相连
    int pRoot = find(p);           // 查找次数
    int qRoot = find(q);

    // 将q的根触点, 作为p的根触点
    if (size[pRoot] < size[qRoot]) { size[qRoot] += size[pRoot]; parent[pRoot] = qRoot;}
    else                           {size[pRoot] += size[qRoot]; parent[qRoot] = pRoot;}
    count--;
  }

  public String toString() {
    String s = new String();
    for (int i = 0; i < parent.length; i++) {
      s += parent[i];
      s += " ";
    }
    return s;
  }
  
  public static void main(String[] args) {
    // 解决由标准输入得到的动态连通性问题
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    WeightedQuickUnionPathSplittingUF uf = new WeightedQuickUnionPathSplittingUF(N);
    while (read.hasNextInt()) {
      int p = read.nextInt();
      int q = read.nextInt();              // 读取整数对
      if (uf.connected(p, q)) continue;    // 如果已经连通则忽略
      uf.union(p, q);                      // 归并分量
      System.out.println(p + " " + q);     // 打印新增连接
    }
    System.out.println(uf.count() + " Components");
  }
}
