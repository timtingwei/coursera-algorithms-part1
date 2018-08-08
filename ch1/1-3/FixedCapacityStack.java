// Copyright [2018] <mituh>
// FixedCapacityStack.java
// 实现一个定容栈(泛型版本)
import java.util.Scanner;

public class FixedCapacityStack<Item> {
  private Item[] a;
  private int N;
  private void resize(int max) {
    Item[] temp = (Item[]) new Object[max];   // 用于存放数组数据的临时数组
    for (int i = 0; i < N; i++) {
      temp[i] = a[i];    // 转移数据, 此时N一定不会越界
    }
    a = temp;            // 数组对象指向新的对象引用
    // System.out.println("resize the item array, capacity = " + max);
  }
  public FixedCapacityStack(int cap) {
    a = (Item[])new Object[cap];               // 创建泛型数组, 用Object类创建, Item[]转换
  }
  public boolean isEmpty() { return N == 0;}
  public int size() { return N;}
  public void push(Item elem) {
    if (N == a.length)     // why? 在上一次push中,N++, 如果N超过了构造大小, N将不发生改变吗?
      resize(a.length * 2);
    a[N++] = elem;
  }
  public Item pop() {
    Item item = a[--N];
    a[N] = null;           // 可以避免对象游离
    if (N > 0 && N == a.length/4) resize(a.length/2);   // 数组剩余空间太多, 缩小
    return item;
  }

  public static void main(String args[]) {
    Scanner read = new Scanner(System.in);
    FixedCapacityStack<String> s =  new FixedCapacityStack<String>(2);
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

/*
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
    FixedCapacityStack<String> s =  new FixedCapacityStack<String>(2);
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
*/
/*
// 如果不写resize函数来分配数组大小:  
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 2
	at FixedCapacityStack.push(FixedCapacityStack.java:68)
	at FixedCapacityStack.main(FixedCapacityStack.java:80)
*/
