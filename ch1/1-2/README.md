# coursera-algorithms-part1

## 1 基础

### 1.2 数据抽象

#### 对象
`对象是能够继承数据类型的值的实体。`

三个重要特性:
> * 1. 状态: 数据类型中的值
> * 2. 标识: 能够将一个对象区别于另一个对象, 认为是在内存中的位置
> * 3. 行为: 数据类型的操作

引用是访问对象的一种方式, 可以认为引用就是内存地址

#### 创建对象

`用关键字new + 类名以及()来触发它的构造函数。`

构造函数没有返回值, 引用总是返回它的数据类型的引用

每当调用new(), 系统都会
> * 为新的对象分配内存空间
> * 调用构造函数初始化对象中的值
> * 返回该对象的一个引用

#### 调用实例方法

区别静态方法 和 实例方法
> * 静态方法一般类名(习惯大写)开头
> * 实例方法一般对象名(习惯小写)开头
```java
head.increament();    // 实例方法
Math.sqer(2, 0);      // 静态方法
```

#### 赋值语句(产生很多bug的原因)
<span style="color:red">赋值语句不会创建新的对象, 而只是创建另一个指向已经存在对象的引用, 也就是别名。</span>

改变一个对象的状态, 影响到和该对象的别名有关的代码。！！！注意

#### 数组也是对象(java中非原始数据类型都是对象)

将数组传递给方法时, 或者将一个数组变量放在赋值语句的右侧时, 都在创建引用的副本, 而不是数组的副本！！

#### 对象的数组

在Java中, 对象数组是一个由对象的引用组成的数组, 而非所有对象本身组成的数组！
对象大, 效率高; 对象小, 需要通过引用, 降低效率.

#### 1.2.2 抽象数据类型举例

##### 字符串
> * 测试了几个方法
> * 尝试了典型的字符串处理代码

##### 抽象数据类型的实现

###### 实现一个Conter类

局部变量和参数变量
```java
...
  private final String name;      // 定义两个实例变量, 作用域是整个类
  private int count;

  public Counter (String s) {     // 参数变量的作用域是整个方法
    String name = "tim";          // 局部变量声明定义在方法中, 作用于整个方法
    this.name = s;                // 出现二义性用this
    System.out.println(name);     // "tim"
  }
...
```

Flips类调用Flips类方法, 模拟抛硬币
```java
public class Flips {
  public static void main(String[] args) {
    int T = Integer.parseInt(args[0]);
    Counter heads = new Counter("heads");
    Counter tails = new Counter("tails");
    for (int i = 0; i < T; i++) {
      if (StdRandom.bernoulli(0.5)) {
        heads.increament();
      } else {
        tails.increament();
      }
    }
    System.out.println(heads);
    System.out.println(tails);
    int delta = heads.tally() - tails.tally();
    System.out.println("delta: " + delta);
  }
}
```

###### 实现一个累加器 Accumulator


#### QA

##### 在创建一个对象数组时, 忘记使用new关键字, 会发生什么?


``` java
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
```

#### Exercises

##### 1.2.4
Java中的String对象不可变, 对对象赋值是指向等号右边对象的引用, 但对String对象赋值时, 等号右边的双引号中的字符串, 实际上是新创建了一个对象, 指向了新对象的引用。

```java
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
```

主要困惑我的地方，一开始没看清楚String和42页左下角Counter对象在赋值时候的区别。
```java
Counter c1 = new Counter("ones");
c1.increment();
Counter c2 = c1;
c2.increment();
StdOut.println(c1);
```

那段代码的第三行, 
```java
Count c2 = c1;   // c1是不是创建新的对象的，c2指向的就是c1的地址
```
而
```
string2 = "world";  // 看似差不多, 实际上"world"是个新对象, string2指向新对象
```

##### 1.2.5
打印出:
```sh
Hello World
```
所有字符串方法都返回一个新的String对象, 不会改变参数对象的值



##### 1.2.13
> * 实现自己的Transcation类
> * 新的构造函数, 比较两笔交易, 错误异常抛出

	
##### 1.2.14
为Transaction类添加equals(Object x)

```java
public boolean equals(Object x) {
    if (this == x) return true;    // 对象的引用相同
    if (x == null) return false;   // 避免下在面的代码中使用空引用
    if (this.getClass() != x.getClass()) return false;   // 同一种类型的所有对象的getCl
    Transaction that = (Transaction) x;                  // ass()方法一定返回相同的引用, 上步后强转
    if (this.name !=  that.name)         return false;
    if (this.time !=  that.time)         return false;
    if (this.amount != that.amount)      return false;
    return true;
}
```

#### Creative Problems
##### 1.2.16
有理数, 为有理数实现一个不可变数据类型Rational, 支持加减乘除操作

##### 1.2.17 
有理数实现的健壮性, 用断言防止溢出
```java

{ // ..
  // 该数与有理数b相加
  public Rational plus(Rational b) {
    assert(down < Integer.MAX_VALUE   && down > Integer.MIN_VALUE);
    assert(b.down < Integer.MAX_VALUE && b.down > Integer.MIN_VALUE);
    Rational rational = new Rational(this);     // 构造一个新的与this相同的对象
    if (rational.down == b.down) {              // 如果分母相等
      rational.up += b.up;
    } else {
      int same_down = rational.down * b.down;   // 如果分母不相等
      rational.up = b.down * rational.up + rational.down * b.up;
      rational.down = same_down;
      rational = remove_divisor(rational);      // 用欧几里得算法消除最大公约数(注意不变性)
    }
    
    return rational;   // 也可以先计算实例变量, 在返回时创建对象
  }
}
```

##### 1.2.19
为之前1.2.13练习中的Date和Transaction编写能够解析字符串的构造函数
```java
public Date(String s) {
    String[] strings = s.split("/");
    year = Integer.parseInt(strings[0]);
    month = Integer.parseInt(strings[1]);
    day = Integer.parseInt(strings[2]);
  }
```


```java
public Transaction(String transaction) {
    // 解析字符串的构造函数
    String[] a = transaction.split("\\s+");
    name = a[0];
    time = new Date(a[1]);     // Date中已经写好分解string的构造函数, 但要构造这个Date对象
    amount = Double.parseDouble(a[2]);
    if (Double.isNaN(amount) || Double.isInfinite(amount))
      throw new IllegalArgumentException("Amount can't be NaN or Infinite");
  }
```


