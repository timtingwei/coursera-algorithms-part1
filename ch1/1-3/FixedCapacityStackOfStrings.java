// Copyright [2018] <mituh>
// FixedCapacityStackOfStrings.java
// 实现一个定容栈

public class FixedCapacityStackOfStrings {
  private String[] a;
  private int N;
  // public FixedCapacityStackOfStrings() {}   // 数组a在构造函数中构造
  public FixedCapacityStackOfStrings(int cap) {
    a = new String[cap];                       // 数组a在构造函数中构造
  }
  public boolean isEmpty() { return N == 0;}
  public int size() { return N;}
  public void push(String elem) {
    a[N++] = elem;
  }
  public String pop() {
    return a[--N];
  }

  public static void main(String args[]) {
    FixedCapacityStackOfStrings f_obj = new FixedCapacityStackOfStrings(2);
    System.out.println(f_obj.isEmpty());
    System.out.println(f_obj.size());
    f_obj.push("Turing");
    System.out.println(f_obj.isEmpty());
    System.out.println(f_obj.pop());
    System.out.println(f_obj.isEmpty());
    f_obj.push("Turing");
    f_obj.push("Turing");
  }
}
