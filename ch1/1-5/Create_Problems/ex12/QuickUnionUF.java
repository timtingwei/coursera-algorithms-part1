// Copyright [2018] <mituh>
// QuickUnionUF.java
// union-find算法实现

import java.util.Scanner;
public class QuickUnionUF {
  private int[] id;
  private int count;

  public QuickUnionUF(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) { id[i] = i; }   // 每个触点只包含自己的分量
    count = N;
  }

  // 返回索引p的根触点的标识符
  public int find(int p) {
    while (p != id[p]) p = id[p];                // 当p指向它自己时, end loop, p是根结点
    return p;                                    // 所有的子触点的标识符都是根触点的标识符
  }

  // 判断两个触点是否连接
  public boolean connected(int p, int q) {
    return find(p) == find(q);                   // p,q的标识符是否相同, id[p] == id[q]
  }

  // 返回当前连通分量数目
  public int count() { return count;}

  public void union(int p, int q) {
    // 将p和q归并到相同的分量中 -> 将p和q的根结点统一
    int pRoot = find(p);
    int qRoot = find(q);
    
    // 已经在相同分量中
    if (pRoot == qRoot) return;

    // 将p的根触点重新命名为q的根触点
    id[pRoot] = qRoot;                          // 此时同一连通分量中, 存放的是索引, 形成了链接

    /*
    // 将p的分量名重新命名为q的名称
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pID) { id[i] = qID; }          // 归并为一个分量
    }
    */
    --count;                                      // p.q连接后, 连通分量数-1
  }

  public static void main(String[] args) {
    // 解决由标准输入得到的动态连通性问题
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    QuickUnionUF uf = new QuickUnionUF(N);
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
