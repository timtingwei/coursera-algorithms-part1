// Copyright [2018] <mituh>
// Stack.java
// 实现一旦在在迭代器中修改集合数据, 就抛出异常

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ConcurrentModificationException;
public class Stack<Item> implements Iterable<Item> {
  private Node first;   // 栈顶元素结点
  private int N;
  private int pop_n;
  private int push_n;
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
    ++push_n;
  }

  public Item pop() {
    if (isEmpty()) throw new NoSuchElementException("Stack overflow");
    Item e = first.item;
    first = first.next;
    --N;
    ++pop_n;
    return e;
  }

  public Iterator<Item> iterator() {
    return new ArrayStackIterator();
  }

  private class ArrayStackIterator implements Iterator<Item> {
    private Node current = first;
    private int orig_pop = pop_n;
    private int orig_push = push_n;
    public boolean hasNext() {
      if (pop_n != orig_pop || push_n != orig_push) {
        throw new ConcurrentModificationException("Revise data in iteration");
      }
      return current != null;
    }
    public Item next() {
      if (pop_n != orig_pop || push_n != orig_push) {
        throw new ConcurrentModificationException("Revise data in iteration");
      }
      Item e = current.item;
      current = current.next;
      return e;
    }
    public void remove() {                          }
  }

  public static void main(String[] args) {
    Stack<String> s1 = new Stack<String>();
    s1.push("mituh");
    s1.push("algorithms");
    Stack<String> t = new Stack<String>(s1);
    System.out.println("size = " + t.size());
    System.out.println("peek = " + t.peek());
    for (String it : t) {
      System.out.println(it);
      // t.push(it);       // throw error
    }
  }
}
