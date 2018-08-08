// Copyright [2018] <mituh>
// Stack_debug.java
import java.util.Iterator;
public class Stack_debug<Item> implements Iterable<Item> {
  private int N;        // 结点的数量
  private Node first;   // 首个结点
  private class Node {  // 链表的结点构造
    Item item;
    Node next;
  }
  // public Stack_debug() {
  //   Node first = new Node();
  //   first.next = null;
  // }
  public int size() { return N;}
  public boolean isEmpty() { return first == null;}  // 首个结点指向null
  public void push(Item elem) {
    // 向栈中添加元素
    Node oldFirst = first;
    // Node first = new Node();           // 这样写的逻辑更直观
    first = new Node();                   // debug: why? 与上一行的区别
    first.next = oldFirst;
    first.item = elem;
    ++N;
  }
	
  public Item pop() {
    // 弹出栈顶元素
    if (first == null) return null;    // 栈不为空
    // Item e = new Item();
    Item e = first.item;
    first = first.next;
    --N;
    return e;
  }
	
  public Iterator<Item> iterator() 
  { return new StackIterator();}
	
  private class StackIterator implements Iterator<Item> {
    // 顺序迭代器类
    private Node current = first;
    public boolean hasNext() {
      System.out.println("in hasNext()" + (current != null));
      return current != null;
    }
    public Item next() {
      System.out.println(current.item);
      Item n_item = current.item;
      current = current.next;
      return n_item;
    }
    public void remove() {}
  }

  public static void main(String[] args) {
    Stack_debug<String> s = new Stack_debug<String>();
    System.out.println(s.isEmpty());
    s.push("Truing");
    s.push("Dijkstra");
    s.push("Kruthun");
    System.out.println(s.isEmpty());
    System.out.println(s.size());
    for (String t : s) {
      System.out.println(t);
    }
  }
}

/*
$ java Stack_debug
true
false
3
in hasNext()true
Kruthun
Kruthun
in hasNext()true
Dijkstra
Dijkstra
in hasNext()true
Truing
Truing
in hasNext()false
*/
