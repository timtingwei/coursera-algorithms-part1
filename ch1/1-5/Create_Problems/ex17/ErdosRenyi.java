// Copyright [2018] <mituht>
// ErdosRenyi.java
// UF用例的随机连接问题


public class ErdosRenyi {

  public static int count(int n) {
    UF uf = new UF(n);
    int edge = 0;
    while (uf.count() > 1) {
      int p = StdRandom.uniform(n);
      int q = StdRandom.uniform(n);
      uf.union(p, q);
      edge++;
    }
    return edge;
  }
  
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int trails = Integer.parseInt(args[1]);
    int[] edges = new int[trails];
    for (int i = 0; i < trails; i++) {    // N个数实验trails次
      edges[i] = count(N);                // 得到每次的连接数(edge)
    }
    System.out.println("1 / 2 n ln n  = " + 0.5 * N * Math.log(N));
    System.out.println("mean          = " + StdStats.mean(edges));
    System.out.println("stddev        = " + StdStats.stddev(edges));
  }
}
