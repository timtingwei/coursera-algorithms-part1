// Copyright [2018] <mituh>
// Queue.java
// 队列的链表实现

import java.util.NoSuchElementException;
public class Queue<Item> {
  private Node first;
  private Node last;
  private int N = 0;
  private class Node {
    Item item;
    Node next;
  }

  public boolean isEmpty() { return first == null;}
  public int     size()    { return N; }
  public void enqueue(Item e) {   // 在尾部添加元素O(1)
    Node oldLast = last;
    last = new Node();
    last.item = e;
    last.next = null;
    if (isEmpty()) first = last;          // 别忘, 如果为first = null, first指向last引用
    else           oldLast.next = last;   // 别忘, 新结点的上一个结点与新结点的链
    ++N;                                  // 别忘, 改变计数
  }

  public Item dequeue() {        // 在头部删除
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    Item e = first.item;
    first = first.next;
    if (isEmpty()) last = null;          // 别忘, 删除后链表空, first=first.next=null, last=null;
    // 如果删除之前只有一个结点, 删除之后链表空了
    --N;                                 // 别忘, 改变计数
    return e;
  }
}
