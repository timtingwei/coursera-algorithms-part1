// Copyright [2018] <mituh>
// Stack.java
import java.util.Iterator;
public class Stack<Item> implements Iterable<Item> {
  private int N;        // 结点的数量
  private Node first;   // 首个结点
  private class Node {  // 链表的结点构造
    Item item;
    Node next;
  }
  public int size() { return N;}
  public boolean isEmpty() { return first == null;}  // 首个结点指向null
  public void push(Item elem) {
    // 向栈中添加元素
    Node oldFirst = first;
    // Node first = new Node();     // bug, 构造一个新first对象, oldFirst指向空
    first = new Node();             // debug: why? 与上一行的区别 first指向新的Node对象引用, oldFirst指向原来first对象引用
    first.next = oldFirst;          // 如果oldFirst指针新的first对象引用的花, first.next = first?
    first.item = elem;
    ++N;
  }
	
  public Item pop() {
    // 弹出栈顶元素
    if (first == null) return null;    // 栈不为空
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

  // 测试函数
  public static void main(String[] args) {
    Stack<String> s = new Stack<String>();
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
