// Copyright [2018] <mituh>
// QuickUnionPathCompressionUF.java
// 实现路径压缩的quick-union算法

import java.util.Scanner;
public class QuickUnionPathCompressionUF {
  private int[] id;
  private int count;

  public QuickUnionPathCompressionUF(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) { id[i] = i; }
    count = N;
  }

  public int count() { return count;}
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }
  /*
  public int find(int p) {
    int[] nodes = new int[id.length];
    int n = 0;
    while (p != id[p]) {
      nodes[n++] = p;
      p = id[p];
    }
    int root = p;
    for (int i = 0; i < n-1; i++) {    // n-1个本来就是根结点的直接子结点
      id[nodes[i]] = root;
    }
    return root;
  }
  */

  public int find(int p) {
    int root = id[p];
    while (root != id[root]) {root = id[root];}       // 只是为了找到根结点
    while (p != root) {                 // 当初始结点到根结点过程中, 各个结点
      int newP = id[p];                 // 保存p的原来的下个结点
      id[p] = root;                     // 更新p的下个结点
      p = newP;                         // p更新为原来的下个结点
    }
    return root;
  }

  public void union(int p, int q) {
    if (connected(p, q)) return;   // 在判断connected时, 已将子结点与根连接直接相连
    int pRoot = find(p);           // 查找次数
    int qRoot = find(q);

    // 将q的根触点, 作为p的根触点
    id[pRoot] = qRoot;
    count--;
  }
  
  public static void main(String[] args) {
    // 解决由标准输入得到的动态连通性问题
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    QuickUnionPathCompressionUF uf = new QuickUnionPathCompressionUF(N);
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
