// Copyright [2018] <mituh>
// ResizeArrayStack.java
// 下压栈(能够动态调整数组大小的实现)
import java.util.Iterator;
// public class ResizeArrayStack implements Iterator<Item>
// public class ResizeArrayStack implements Iterable<Item>
public class ResizeArrayStack<Item> implements Iterable<Item> {   // 在栈类中嵌套Iterator
  private Item[] a = (Item[]) new Object[1];   // 默认构造一个大小为1的泛型数组, 栈元素
  private int N = 0;                           // 元素数量
  public int size() { return N;}
  public boolean isEmpty() { return N == 0;}
  private void resize(int max) {
    Item[] temp = (Item[]) new Object[max];
    for (int i = 0; i < N; i++) {
      temp[i] = a[i];
    }
    // temp = a;
    a = temp;     // a重新指向temp泛型数组对象的索引
  }
  public void push(Item elem) {
    if (N == a.length) resize(a.length * 2);
    a[N++] = elem;
  }
  public Item pop() {
    // why? if (N == 0) // ..
    Item elem = a[--N];
    a[N] = null;        // 避免游离对象
    if (N > 0 && N == a.length / 4) resize(a.length/2);
    return elem;
  }
  // 创建逆序迭代器, 可用于遍历数组
  public Iterator<Item> iterator()    // 实现一个iterator()方法, 并返回一个Iterator对象
  { return new ReverseArrayIterator(); }

  private class ReverseArrayIterator implements Iterator<Item> {
    // Iterator类必须包含两个方法, hasNext()返回boolean值和 next()返回集合中的泛型元素
    private int i = N;
    public boolean hasNext() { return i > 0;}    // 索引此时比0小, 未到栈头
    public Item    next()    { return a[--i];}   // 获取逆序下一个元素
    public void    remove()  {               }   // remove函数常为空
  }
}
