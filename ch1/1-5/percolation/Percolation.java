// Copyright [2018] <mituh>
// Percolation.java
// 应用union-find算法计算渗滤模型

import java.util.Scanner;
public class Percolation {
  private UF uf;                       // 用于计算的连通图
  private boolean[] open;
  private boolean[] full;
  private int top;
  private int n;                       // 模型的规模
  private int openSites;               // 开放格子
  public Percolation(int n) {          // create n-by-n open, with all sites blocked
    int count = n * n;
    open = new boolean[count];
    full = new boolean[count];
    top = count;                       // virutal top site的标记是n*n, 是parent数组最后一个索引
    uf = new UF(count+1);              // 建立n*n的连通图模型
    // uf.pratent[top] = top;
    for (int i = 0; i < n; i++) {
      uf.union(i, top);                // 首行格点与虚拟根结点连通
      open[i] = false;
    }
    for (int i = n; i < count; i++) {  // 非首行格点
      open[i] = false;                 // 每个格子初始都是block状态
    }
    this.n = n;
    this.openSites = 0;
  }
  public    void open(int row, int col) {    // open site (row, col) if it is not open already
    if (isOpen(row, col)) return;
    int p = row * n + col;
    open[p] = true;
    openSites++;
    // union, 0, 1, 2, 3 or 4次
    if (row == 0) {         // 最上一行
      if (isOpen(row+1, col)) { uf.union(p, p+n);}
    } else if (row == n-1) {
      if (isOpen(row-1, col)) { uf.union(p, p-n);}
    } else {
      if (isOpen(row+1, col)) { uf.union(p, p+n);}
      if (isOpen(row-1, col)) { uf.union(p, p-n);}
    }

    if (col == 0) {
      if (isOpen(row, col+1)) { uf.union(p, p+1);}
    } else if (col == n-1) {
      if (isOpen(row, col-1)) { uf.union(p, p-1);}
    } else {
      if (isOpen(row, col+1)) { uf.union(p, p+1);}
      if (isOpen(row, col-1)) { uf.union(p, p-1);}
    }
    isFull(row, col);  // 只要根结点是top, 就能自己转成true
  }
  public boolean isOpen(int row, int col) {  // is site (row, col) open?
    return open[row*n+col];
  }
  public boolean isFull(int row, int col) {  // is site (row, col) full?
    if (uf.connected(row*n + col, top)) {
      full[row*n + col] = true;
      return true;
    }
    return false;
  }
  public     int numberOfOpenSites() {       // number of open sites
    return openSites;
  }
  public boolean percolates() {              // does the system percolate?
    int count = n * n;
    for (int i = count - 1; i > count - n; i--) {
      if (uf.connected(i, top)) return true;
    }
    return false;
  }

  public static void main(String[] args) {   // test client (optional)
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    Percolation percolation = new Percolation(N);
    // /*
    while (read.hasNextInt()) {
      int r = read.nextInt();
      int c = read.nextInt();
      percolation.open(r-1, c-1);
    }
    System.out.println(percolation.percolates());
    System.out.println(percolation.numberOfOpenSites());
    System.out.println("isFull " + percolation.isFull(6-1, 2-1));

    
  }
}

/*
3
 1 3
 2 3
 3 3
 3 1
 2 1
 1 1
*/
