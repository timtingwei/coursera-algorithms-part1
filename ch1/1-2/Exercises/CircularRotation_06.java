
import java.util.Scanner;

public class CircularRotation_06 {
  public static boolean judge(String s, String t) {
    // 判断s和t是否互为回环变位
    if (s.length() == t.length() && s.concat(s).indexOf(t) >= 0)
      return true;
    else return false;
  }
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    String s = read.next();
    String t = read.next();
    read.close();
    System.out.println("judge = " + judge(s, t));
  }
}
