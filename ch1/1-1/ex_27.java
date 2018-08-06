// Copyright [2018] <mituh>

public class ex_27 {

  public static int n;
  
  public static double binomial(int N, int k, double p) {
    n++;
    if (N == 0 && k == 0) return 1.0;
    if (N < 0 || k < 0) return 0.0;
    return (1.0 - p)*binomial(N-1, k, p) + p*binomial(N-1, k-1, p);
  }
  
  public static void main(String args[]) {
    n = 0;
    binomial(100, 50, 0.25);
    System.out.println(n);
  }
}
