// Copyright [2018] <mituh>
// Reverse.java
// 将标准输入的整数, 逆序输出
import java.util.Scanner;
import java.util.Stack;
public class Reverse {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Stack<Integer> s = new Stack<Integer>();
    while (read.hasNextInt()) {
      s.push(read.nextInt());
    }
    read.close();
    while (!s.empty()) {
      System.out.println(s.pop());
    }
  }
}
