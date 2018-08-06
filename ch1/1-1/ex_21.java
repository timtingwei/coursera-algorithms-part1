// Copyright [2018] <mituh>
import java.util.Scanner;

/*
public class Node {
  public String name;
  public int a;
  public b;
}
*/

public class ex_21 {
  public static void main(String args[]) {
    // 标准输入按行读取数据, 1名字+2个整数
    Scanner read = new Scanner(System.in);
    int cnt = 0;
    // Node nodelist[] = new Node();
    while (read.hasNext()) {
      // Node node = new Node();
      // node.name = read.nextString();
      // node.a = read.nextInt(); node.b = read.nextInt();
      String s = read.next();
      int a = read.nextInt();
      int b = read.nextInt();
      Double d = new Double((double)b);    // 将整型转化为double类型
      // nodelist[cnt++] = node;
      // printf打印表格, 名字, 整数, 1数除以2数3位小数
      System.out.printf("%s %d %.3f\n", s, a, a / d);
    }
    // printNode(nodelist);
  }
}
