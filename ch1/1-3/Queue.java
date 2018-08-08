// Copyright [2018] <mituh>
// Queue.java
// 链表实现一个先进先出的队列
import java.util.Scanner;
public class Queue<Item> {
  private Node first;
  private Node last;
  int N = 0;
  private class Node {      // 定义结点的嵌套类
    Item item;
    Node next;
  }
  // 没有构造函数, first, last在空栈时, 都指向null, N = 0;

  // 判空(思考一下first == last为什么不行?)
  public boolean isEmpty() {
    // return first == last;      // why?
    return first == null;
  }

  // 获取结点个数
  public int size() { return N; }

  // 在尾部插入(插前判空, first进行额外处理)
  public void enqueue(Item e) {
    Node oldLast = last;
    last = new Node();
    last.item = e;                 // 新结点的元素对象
    last.next = null;              // 新结点指向null
    // oldLast.next = last;        // 原来尾部结点指向新插入的结点
    if (isEmpty()) first = last;
    else           oldLast.next = last;    // 空队列时, oldLast指向last为null的引用对象
    ++N;
  }
  
  // 在头部删除, 返回被删除的对象元素(删后判空, 对last进行额外处理)
  public Item dequeue() {
    if (isEmpty()) return null;  // 判空
    Item item = first.item;
    first = first.next;
    if (first == null)           // 如果删除的是最后一个元素
      last = null;
    --N;
    return item;
  }

  // iterator()

  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Queue<String> q = new Queue<String>();
    while (read.hasNext()) {
      String item = read.next();
      if (!item.equals("-")) {
        q.enqueue(item);
      } else if (!q.isEmpty()) {
        System.out.print(q.dequeue() + " ");
      }
    }
    System.out.println("(" + q.size() + " left on stack)");
  }
}
