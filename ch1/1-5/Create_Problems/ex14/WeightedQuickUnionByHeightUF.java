// Copyright [2018] <mituh>
// WeightedQuickUnionByHeightUF.java
// 实现根据高度加权的quick-union算法

import java.util.Scanner;
public class WeightedQuickUnionByHeightUF {
  private int[] id;
  private int[] size;
  private int[] height;
  private int count;

  public WeightedQuickUnionByHeightUF(int N) {
    id = new int[N];
    size = new int[N];
    height = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
      height[i] = 0;         // 每个触点的高度都为0, 因为无子结点
    }
    count = N;
  }

  public int count() { return count;}
  public boolean connected(int p, int q) {
    return find(p) == find(q);
  }

  public int find(int p) {
    while (p != id[p]) { p = id[p];}   // p指向下个结点, 指向自己时, end loop
    return p;
  }

  public void union(int p, int q) {
    if (connected(p, q)) return;   // 在判断connected时, 已将子结点与根连接直接相连
    int i = find(p);           // 查找次数
    int j = find(q);

    // 两棵树高度不等, 合并不会增加树的高度, 因为是合并到根结点上, 合起来不会超过当前高度
    if      (height[i] < height[j]) {id[i] = j; size[j] += size[i];}
    else if (height[i] > height[j]) {id[j] = i; size[i] += size[j];}
    else {   // 两棵树高度相等
      if (size[i] < size[j]) {       // 仍旧按照结点数量决定合并主次
        id[i] = j; size[j] += size[i]; 
        height[j]++;                 // 合到q上, q增高
      } else {
        id[j] = i; size[i] += size[j]; 
        height[i]++;                 // 合到p上, p增高
      }
    }
    count--;
  }

  public String toString() {
    String s = new String();
    for (int i = 0; i < id.length; i++) {
      s += id[i];
      s += " ";
    }
    return s;
  }
  
  public static void main(String[] args) {
    // 解决由标准输入得到的动态连通性问题
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    WeightedQuickUnionByHeightUF uf = new WeightedQuickUnionByHeightUF(N);
    while (read.hasNextInt()) {
      int p = read.nextInt();
      int q = read.nextInt();              // 读取整数对
      if (uf.connected(p, q)) continue;    // 如果已经连通则忽略
// System.out.println(uf);
      uf.union(p, q);                      // 归并分量
// System.out.println(uf);
      System.out.println(p + " " + q);     // 打印新增连接
    }
    System.out.println(uf.count() + " Components");
  }
}
