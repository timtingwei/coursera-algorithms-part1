// Copyright [2018] <mituh>
// Main.java
// 用栈实现十进制转二进制

import java.util.Scanner;
public class Main {
  public static void main(String[] args) {
    Stack<Integer> stack = new Stack<Integer>();
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    while (N > 0) {
      stack.push(N % 2);
      N /= 2;
    }
    for (int i : stack) { System.out.print(i);}
    System.out.println();
  }
}
