// Copyright [2018] <mituh>
// FixedCapacityStackOfStrings_01.java
// 为定容栈添加isFull()方法

public class FixedCapacityStackOfStrings_01 {
  private String[] a;
  private int N;
  public FixedCapacityStackOfStrings_01(int cap) {
    a = new String[cap];                       // 数组a在构造函数中构造
    N = 0;
  }
  public boolean isEmpty() { return N == 0;}           // 判空
  public boolean isFull()  { return N == a.length;}    // 判满
  public int size() { return N;}
  public void push(String elem) {
    a[N++] = elem;
  }
  public String pop() {
    return a[--N];
  }

  public static void main(String args[]) {
    FixedCapacityStackOfStrings_01 f_obj = new FixedCapacityStackOfStrings_01(2);
    System.out.println(f_obj.isEmpty());
    System.out.println(f_obj.size());
    f_obj.push("Turing");
    System.out.println(f_obj.isEmpty());
    f_obj.push("Dijkstra");
    System.out.println(f_obj.isFull());
  }
}
