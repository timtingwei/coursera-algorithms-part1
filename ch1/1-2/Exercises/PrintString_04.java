// Copyright [2018] <mituh>
// PrintString_04.java

public class PrintString_04 {
  public static void main(String[] args) {
    // String string1 = new String("hello");
    String string1 = "hello";
    String string2 = string1;    // string2是string1的引用?
    string2 = "world";           // 修改string2, string1为何不发生改变？
    StdOut.println(string1);
    StdOut.println(string2);
  }
}
