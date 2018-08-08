// Copyright [2018] <mituh>
// myReadInts.java

import java.util.Scanner;
import java.util.Queue;
// import java.util.AbstractQueue;
public class myReadInts {
  public static int[] myReadInts() {   // 静态方法, 返回读取的数组
    Scanner read = new Scanner(System.in);
    // Queue queue = new Queue<int> ();      // 不能使用原始数据类型
    // Queue queue = new Queue<Integer> ();  // queue泛型要定义参数类型
    // Queue is a abstract, 这里的api是书中的实现, 跟java中util中有区别
    Queue<Integer> queue = new Queue<Integer>();
    while (read.hasNextInt()) {
      queue.enqueue(read.nextInt());    // Stack用pop(), Bag用add(), 但都是书中实现
    }
    read.close();

    int N = queue.size();
    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
      a[i] = queue.dequeue();
    }
    return a;
  }
}
