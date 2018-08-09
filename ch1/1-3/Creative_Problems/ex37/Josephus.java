// Copyright [2018] <mituh>
// Josephus.java

import java.util.Scanner;
public class Josephus {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    int M = read.nextInt();
    Queue<Integer> q1 = new Queue<Integer>();
    Queue<Integer> q2 = new Queue<Integer>();
    // Queue<Integer> q_ans = new Queue<Integer>();  // 直接打印储存
    for (int i = 0; i < N; i++) { q1.enqueue(i);}
    int num = 1, cnt = 0;
    while (cnt != N) {
      if (!q1.isEmpty()) {
        if (num != M) { q2.enqueue(q1.dequeue()); num++; }   // q1队首出队, 进q2队
        else {
          System.out.print(q1.dequeue() + " ");
          num = 1; cnt++;
        }
      } else {
        if (num != M) { q1.enqueue(q2.dequeue()); num++; }   // q1队首出队, 进q2队
        else {
          System.out.print(q2.dequeue() + " ");
          num = 1; cnt++;
        }
      }
    }
    System.out.println();
  }
}
