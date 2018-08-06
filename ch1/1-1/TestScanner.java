import java.util.*;

public class TestScanner {
  public static void main(String[] args) {
    /*
    Scanner in = new Scanner(System.in);
    float a = in.nextFloat();
    System.out.printf("%.3f\n", a);
    int b = in.nextInt();
    System.out.printf("%03d\n", b);
    */
    double sum = 0.0;
    int cnt = 0;
    Scanner in = new Scanner(System.in);
    while (in.hasNextDouble())  {
      sum += in.nextDouble();
      cnt++;
    }
    double avg = sum / cnt;
    System.out.printf("avg is %.5f\n", avg);
  }
}
