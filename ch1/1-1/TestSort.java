import java.util.Arrays;

public class TestSort {
  public static void main(String[] args) {
    int[] a = {3, 4, 1, 4, 5, 3};
    Arrays.sort(a);
    int b = -4;
    System.out.printf("abs(b) = %d\n", Math.abs(b));
    for (int i = 0; i < a.length; i++) {
      // System.out.print(a[i]);
      System.out.printf("%d ", a[i]);
    }
    System.out.println();
    
  }
}
