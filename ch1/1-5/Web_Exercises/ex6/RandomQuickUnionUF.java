// Copyright [2018] <mituh>
// RandomQuickUnionUF.java

import java.util.Scanner;
public class RandomQuickUnionUF {
  private int[] parent;
  private int[] label;
  private int count;

  public RandomQuickUnionUF(int N) {
    parent = new int[N];
    label = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
      label[i] = (int)Math.random() * N;
    }
    count = N;
  }
  public int count() { return count;}
  public int find(int p) {
    while (p != parent[p]) { p = parent[p];}
    return p;
  }
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
  public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) return;

    if (label[pRoot] < label[qRoot]) { parent[pRoot] = qRoot; }
    else                             { parent[qRoot] = pRoot; }
    count--;
  }

  public static void main(String[] args) {
    // 解决由标准输入得到的动态连通性问题
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    RandomQuickUnionUF uf = new RandomQuickUnionUF(N);
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
