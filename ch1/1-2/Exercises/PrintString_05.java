// Copyright [2018] <mituh>
// PrintString_05.java

public class PrintString_05 {
  public static void main(String[] args) {
    String s = "Hello World";
    s.toUpperCase();
    s.substring(6, 11);
    StdOut.println(s);
    // Hello World
  }
}

// 所有字符串方法都返回一个新的String对象, 不会改变参数对象的值
