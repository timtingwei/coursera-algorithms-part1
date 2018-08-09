// Copyright [2018] <mituh>
// 链表实现一个栈

public class Stack<Item> {
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

  public Item top() {
    // 获得栈顶元素, 但不删除
    if (isEmpty()) return null;
    return first.item;
  }

  /*
  // 测试函数
  public static void main(String[] args) {
    Stack<String> stk = new Stack<String>();
    stk.push("tim");
    System.out.println(stk.top());
    System.out.println(stk.pop());
    System.out.println(stk.isEmpty());
  }
  */
}
