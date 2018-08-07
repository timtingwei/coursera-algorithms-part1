// Copyright [2018] <mituh>
// TestCreateArray.java
// 在创建一个对象数组时, 忘记使用new关键字, 会发生什么?

public class TestCreateArray {
  public static void main(String[] args) {
    // 错误1: 忘了创建数组
    Counter[] a;
    a[0] = new Counter("test");
    // * error: variable a might not have been initialized

    // 错误2: 创建数组中的一个对象时, 忘记使用new
    Counter[] a = new Counter[2];
    a[0].increament();
    // * Exception in thread "main" java.lang.NullPointerException
    //	* at TestCreateArray.main(TestCreateArray.java:11)

    // 正确的版本
    Counter[] a = new Counter[2];
    a[0] = new Counter("test");
    a[0].increament();
    // 创建对象数组, 有N个对象, 用N+1次new, 数组1次, 数组中各个对象各1次(总共N次)
  }
}
