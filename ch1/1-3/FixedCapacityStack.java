// Copyright [2018] <mituh>
// FixedCapacityStack.java
// 实现一个定容栈(泛型版本)
import java.util.Scanner;
public class FixedCapacityStack<Item> {
  private Item[] a;
  private int N;
  // public FixedCapacityStackOfStrings() {}   // 数组a在构造函数中构造
  public FixedCapacityStack(int cap) {
    a = (Item[])new Object[cap];               // 创建泛型数组, 用Object类创建, Item[]转换
  }
  public boolean isEmpty() { return N == 0;}
  public int size() { return N;}
  public void push(Item elem) {
    a[N++] = elem;
  }
  public Item pop() {
    return a[--N];
  }

  public static void main(String args[]) {
    Scanner read = new Scanner(System.in);
    FixedCapacityStack<String> s =  new FixedCapacityStack<String>(100);
    while (read.hasNext()) {
      String item = read.next();
      if (!item.equals("-")) {
        s.push(item);
      } else if (!s.isEmpty()) {
        System.out.print(s.pop() + " ");
      }
    }
    System.out.println("(" + s.size() + " left on stack)");
  }
}

/*
$ more tobe.txt
to be or not to - be - - that - - - is
bash-3.2$ java FixedCapacityStack < tobe.txt
to be not that or be (2 left on stack)
*/
