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

/*
String string1 = "hello";
String string2 = string1;    // string2确实是指向string1的引用
string2 = "world";           // 创建新的对象"wolrd", 此时string2指向新对象的引用
*/
