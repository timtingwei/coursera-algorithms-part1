// Copyright [2018] <mituh>
// Stack.java
// 链表实现一个栈(改写top为peek, 加入报错)

import java.util.Iterator;
public class Stack<Item> implements Iterable<Item> {
  private Node first;    // 栈顶结点
  private int N;
  private class Node {
    Item item;
    Node next;
  }

  public boolean isEmpty() { return first == null;}
  public int     size()    { return N; }
  public void push(Item item) {
    // 向栈中添加元素item
    Node oldFirst = first;
    first = new Node();
    first.item = item;
    first.next = oldFirst;
    ++N;
  }

  public Item pop() {
    // 栈非空时, 栈顶元素出栈
    if (isEmpty()) return null;
    Item item = first.item;
    first = first.next;
    --N;
    return item;
  }

  public Item peek() {
    // 获得栈顶元素, 但不删除(需要判空)
    if (isEmpty()) throw new NoSuchElementException;
    return first.item;
  }

  public Iterator<Item> iterator() {
    return new LinkedStack();      // 迭代器类本身不存在泛型
  }

  private class LinkedStack implements Iterator<Item> {
    private Node current = first;
    public boolean hasNext() { return current != null;}
    public void    remove()  {                        }
    public Item    next()    {
      Item item = current.item;
      current = current.next;
      return item;
    }
  }

  /*
  // 测试函数
  public static void main(String[] args) {
    Stack<String> stk = new Stack<String>();
    stk.push("tim");
    System.out.println(stk.peek());
    System.out.println(stk.pop());
    System.out.println(stk.isEmpty());
    stk.push("a");
    stk.push("b");
    stk.push("c");
    stk.push("d");
    for (String s : stk) {
      System.out.println(s);
    }
  }
  */
}
