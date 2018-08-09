// Copyright [2018] <mituh>
// Josephus.java

public class Josephus {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int M = Integer.parseInt(args[1]);
    Queue<Integer> queue = new Queue<Integer> ();
    for (int i = 0; i < N; i++) { queue.enqueue(i); }
    while (!queue.isEmpty()) {
      for (int i = 0; i < M-1; i++) {
        queue.enqueue(queue.dequeue());   // 出列 同时 进列
      }
      System.out.print(queue.dequeue() + " ");
    }
    System.out.println();
  }
}

/*
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
*/
