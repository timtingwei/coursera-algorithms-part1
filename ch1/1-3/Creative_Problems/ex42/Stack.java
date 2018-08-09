// Copyright [2018] <mituh>
// Stack.java
// 链表实现栈(添加用于复制栈的构造函数)

import java.util.NoSuchElementException;
public class Stack<Item> {
  private Node first;   // 栈顶元素结点
  private int N;
  private class Node {
    Item item;
    Node next;
    Node() {};          // 默认构造函数
    Node(Node x) { this.item = x.item; this.next = x.next; }   // 使用结点来复制栈
  }

  public Stack() {}     // 默认构造函数
  public Stack(Stack<Item> s) {
    // 使用Node构造Stack
    if (s.first != null) {
      first = new Node(s.first);
      for (Node x = first; x.next != null; x = x.next)
        x.next = new Node(x.next);
    }
  }

  public boolean isEmpty() { return first == null; }
  public int     size()  { return N;}
  public Item peek() {
    if (isEmpty()) throw new NoSuchElementException("Stack is empty");
    return first.item;
  }
  public void push(Item e) {
    Node oldFirst = first;
    first = new Node();
    first.item = e;
    first.next = oldFirst;
    ++N;
  }

  public Item pop() {
    if (isEmpty()) throw new NoSuchElementException("Stack overflow");
    Item e = first.item;
    first = first.next;
    --N;
    return e;
  }

  public static void main(String[] args) {
    Stack<String> s1 = new Stack<String>();
    s1.push("mituh");
    s1.push("algorithms");
    Stack<String> t = new Stack<String>(s1);
    System.out.println("size = " + t.size());
    System.out.println("peek = " + t.peek());
  }
}
