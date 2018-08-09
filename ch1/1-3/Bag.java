// Copyright [2018] <mituh>
// Bag.java
// 用链表实现bag, 且可用foreach语句遍历

import java.util.Iterator;
import java.util.Scanner;
public class Bag<Item> implements Iterable<Item> {
  private Node first;   // 链表的首结点
  // private int  N;    // 不需要知道背包中的数量
  private class Node {
    Item item;
    Node next;
  }
  public boolean isEmpty()
  { return first == null; }
  public int size()
  { return N; }
  public void add(Item e) {
    Node oldFirst = first;
    first = new Node();
    first.item = e;
    first.next = oldFirst;
    ++N;
  }
  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  private class ListIterator implements Iterator<Item> {
    private Node current = first;
    public boolean hasNext() { return current != null;}
    public Item    next()    {
      Item item = current.item;
      current = current.next;
      return item;
    }
    public void    remove()  {                        }
  }

  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Bag<String> bag = new Bag<String>();
    while (read.hasNext()) {
      String item = read.next();
      if (!item.equals("-")) {
        bag.add(item);
      } else if (!bag.isEmpty()) {
        // System.out.print(bag.dequeue() + " ");
      }
    }
    System.out.println("(" + bag.size() + " left on stack)");
  }
}                      
