// Copyright [2018] <mituh>
// Counter.java

public class Counter {
  private final String name;      // 定义两个实例变量, 作用域是整个类
  private int count;

  public Counter (String s) {     // 参数变量的作用域是整个方法
    // String name = "tim";          // 局部变量声明定义在方法中, 作用于整个方法
    this.name = s;                // 出现二义性用this
    // System.out.println(name);     // "tim"
  }

  public void increament() {
    count++;
  }

  public int tally() {
    return count;
  }

  public String toString() {
    return count + " " + name;
  }

  public static void main(String[] args) {
    Counter heads = new Counter("heads");
    Counter tails = new Counter("tails");

    heads.increament();
    heads.increament();
    tails.increament();
    System.out.println(heads + " " + tails);   // 自动调用对象的toString()
    System.out.println(heads.tally() + " " + tails.tally());
    // System.out.println(tails.toString());
  }
}

/*
$ java Counter
heads tails
2 1
*/
