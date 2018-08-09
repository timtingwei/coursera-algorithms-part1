// Copyright [2018] <mituh>
// ResizingArrayQueueOfStrings.java

import java.util.NoSuchElementException;
import java.util.Scanner;
public class ResizingArrayQueueOfStrings<Item> {
  private int first;     // 队首元素索引
  private int last;      // 指向队尾元素的下一个位置
  private Item[] a;

  public ResizingArrayQueueOfStrings() {    // 动态分配数组大小的构造函数
    // Item[] a = (Item[]) new Object[1];   // why?又是相同的问题
    a = (Item[]) new Object[1];
    first = 0; last = 0;
  }

  // 判空
  public boolean isEmpty()
  {return first == last;}
  public int size()
  { return last - first;}

  // 根据需要分配数组大小
  private void resize(int max) {
    Item[] temp = (Item[]) new Object[max];
    for (int i = 0; i < size(); i++) {
      temp[i] = a[i+first];      // 将队列的扩容缩容量的同时
    }
    a = temp;                    // a指向新的对象引用
    last -= first;               // 整理至数组头部
    first = 0;
  }

  public Item front() {
    if (isEmpty()) throw new NoSuchElementException("Queue empty");
    return a[first];
  }

  public void enqueue(Item e) {
    if (last == a.length) {    // 队列的尾部满
      resize(a.length * 2);    // 扩容两倍, 且利用数组前面的空间
    }
    a[last++] = e;
  }

  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException("Queue overflow");
    Item e = a[first];
    a[first++] = null;              // 游离被删除对象, first向后移动一位
    if (size() == a.length / 4) {   // 删除后剩余空过多, 缩容
      resize(a.length / 2);
    }
    return e;                       // 不清楚这样是不是会导致e指向null呢?
  }

  // 测试代码
  public static void main(String[] args) {
    ResizingArrayQueueOfStrings<String> q = new ResizingArrayQueueOfStrings<String>();
    /*
    System.out.println(q.isEmpty());
    System.out.println(q.size());
    // System.out.println(q.front());
    System.out.println();

    q.enqueue("mituh");
    System.out.println(q.isEmpty());
    System.out.println(q.size());
    System.out.println(q.front());
    System.out.println();

    q.enqueue("algorithms");
    System.out.println(q.isEmpty());
    System.out.println(q.size());
    System.out.println(q.front());
    System.out.println();

    System.out.println(q.dequeue());
    System.out.println(q.isEmpty());
    System.out.println(q.size());
    System.out.println(q.front());
    System.out.println();
    */
    Scanner read = new Scanner(System.in);
    while (read.hasNext()) {
      String s = read.next();
      if (s.equals("-")) System.out.print(q.dequeue() + " ");
      else q.enqueue(s);
    }
    System.out.print("(size " + q.size() + " ) left in queue");
    System.out.println();
  }
}

/* tobe.txt
to be or - - not - to be - - why that the an be - - - - love jube nu tt mit cop - - - that the an be that the an be that the an be - - - - - - - - - - - - - - - - 

$ java ResizingArrayQueueOfStrings < tobe.txt
to be or not to be why that the an be love jube nu tt mit cop that the an be that the an be that the an (size 1 ) left in queue
*/
