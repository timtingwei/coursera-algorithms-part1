# coursera-algorithms-part1

## 1 基础

### 1.1 基础编程模型


#### 类型转化
```
int b = read.nextInt();
Double d = new Double((double)b);    // 将整型转化为double类型
```
#### 字符串
字符串与数字之间的互相转换.
**Integer**
> * Integer.parseInt(String s)
> * Integer.toString(int i)

**Double**
> * Double.parseDouble(String s)
> * Double.toString(Double x)

```java
String s1 = "123"; int a = 7;
System.out.printf("parseInt(s1) = %d\n", Integer.parseInt(s1));
System.out.printf("toString(a) = %s\n", Integer.toString(a));
```

```sh
$ java TestString
parseInt(s1) = 123
toString(a) = 7
```


#### 标准输入
```java
import java.util.Scanner;

Scanner in = new Scanner(System.in);
int a = in.nextFloat();                   // 读取浮点数
String s = in.next();                     // 读取字符串
String s2 = in.nextLine();                // 读取一整行
```

```java
import java.util.*;

public class TestScanner {
  public static void main(String[] args) {
    double sum = 0.0;
    int cnt = 0;
    Scanner in = new Scanner(System.in);
    while (in.hasNextDouble())  {           // 判断
      sum += in.nextDouble();               // 读取
      cnt++;
    }
    double avg = sum / cnt;
    System.out.printf("avg is %.5f\n", avg);
  }
}
```

nextInt();                // 读取下一个整数
hasNextInt();             // 判断是否有下一个整数

#### 标准输出
```java
System.out.println();              // 换行打印
System.out.printf("%d\n", i);      // 根据格式打印
System.out.print();                // 连续打印
```

#### 重定向与管道

```java
% java RandomSeq 1000 100.0 200.0 > data.txt   // 写入
% java Averge < data.txt                       // 读取
% java RandomSeq 1000 100.0 200.0 | Averge     // 通过管道作为输入
```

#### 标准绘图库
测试书中提供的绘图库StdDraw

#### 二分查找


#### web exercises

##### Sattolo's algorithm
##### Wget
##### Right triangle
##### Bouncing ball(不能看到movie, 暂时先过)


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


### 1.3 背包, 队列和栈

三种数据类型, 
背包(Bag), 队列(Queue)和栈(Stack), 不同之处在于删除或者访问对象的顺序不同.

这节的三个目标:
> * 1, 说明我们对集合中的对象的表示方式将直接影响各种操作的效率
> * 2, 介绍泛型和迭代, 清晰 简洁 优雅
> * 3, 链式数据结构的重要性, 才能高效实现背包, 队列和栈.

#### 1.3.1 API
这里的API都书中的实现。

##### 1.3.1.1 泛型

类名后的<Item>记号将Item定义为一个类型参数, 它一个象征性的占位符, 表示的是用例将会使用的某种具体的数据类型。
<span style="color:red">类型参数必须被实实例化引用类型</span>

##### 1.3.1.2 自动装箱
<span style="color:red">类型参数必须被实实例化引用类型</span>, Java用自动装箱机制来使泛型代码能够处理原始数据类型。

封装类型的原始数据类型对应的引用类型:
`Boolean`, `Byte`, `Character`, `Double`, `Float`, `Integer`, `Long`和`Short`
对应:
`boolean`, `byte`, `character`, `double`, `float`, `integer`, `long` 和 `short`
在处理赋值语句, 方法的参数和算数或逻辑表达式时, Java会自动在引用类型和对应的原始数据类型之间进行转换
```java
Stack<Integer> stack = new Stack<Integer>();
stack.push(17);         // int -> Integer, 自动装箱
int i = stack.pop();    // Integer -> int, 自动拆箱
```

##### 1.3.1.3 可迭代的集合类型
如果集合是可迭代的, 可以用foreach语句, 写出简洁的遍历代码
```java
Queue<Transaction> collection = new Queue<Transaction> ();
for (Transaction t : collection) {
  StdOut.println(t);
}
```

##### 1.3.1.4 背包

背包是一种不支持删除元素的集合数据类型, 他的目的就是帮助用例收集元素并迭代遍历所有收集到的元素。

添加进背包, 元素用foreach语句访问。
使用Bag说明元素的顺序不重要, 不要就使用Stack, Queue

##### 1.3.1.5 先进先出队列
队列(Queue), 基于先进先出(FIFO)策略的集合类型, (first int first out)出列的顺序和入列的顺序相同

计算的的任务响应, 排队

实现一个myReadInts()方法, 无需预先知道文件的大小, 将所有的整数读入一个数组
```java
import java.util.Scanner;
public class myReadInts {
  public static int[] myReadInts() {   // 静态方法, 返回读取的数组
    Scanner read = new Scanner(System.in);
    // Queue queue = new Queue<int> ();      // 不能使用原始数据类型
    // Queue queue = new Queue<Integer> ();  // queue泛型要定义参数类型
    // Queue is a abstract, 这里的api是书中的实现, 跟java中util中有区别
    Queue<Integer> queue = new Queue<Integer>();
    while (read.hasNextInt()) {
      queue.enqueue(read.nextInt());    // Stack用pop(), Bag用add(), 但都是书中实现
    }
    read.close();

    int N = queue.size();
    int[] a = new int[N];
    for (int i = 0; i < N; i++) {
      a[i] = queue.dequeue();
    }
    return a;
  }
}
```

##### 1.3.1.6 下压栈
栈(Stack), 是一种基于`后进先出`(LIFO)的策略的集合类型.

邮件的压箱底, 超链接的点击与退回, 双向的火车轨道, 坑

1, 实现将标准输入的整数, 逆序输出(这种实现与书本API无关)
```java
import java.util.Scanner;
import java.util.Stack;
public class Reverse {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Stack<Integer> s = new Stack<Integer>();
    while (read.hasNextInt()) {
      s.push(read.nextInt());
    }
    read.close();
    while (!s.empty()) {
      System.out.println(s.pop());
    }
  }
}
```

##### 1.3.1.7 算术表达式的求值

用两个栈(一个用于保存运算符, 一个用于保存操作数), 完成算术表达式的求值
```
/*
 * 遇到运算符加入运算符栈
 * 遇到数字加入操作数栈
 * 遇到左括号 "("或 " ", 忽略
 * 右括号栈顶两个操作数出栈, 栈顶运算符出栈
 * 计算该表达式后, 结果入操作数栈
 */
```

根据以上思路, 自己实现如下版本:
```java
import java.util.Scanner;
import java.util.Stack;
public class Evaluate {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    String s = read.nextLine();       // 读入一整行字符串
    read.close();
    Stack<Character> stk_ch = new Stack<Character>();            // Java, Char需要写全拼
    Stack<Double> stk_d = new Stack<Double>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ' || s.charAt(i) == '(') continue;    // Java中用s.charAt(i), 不是s[i]
      // if (s.charAt(i).isDigit()) {}                           // 回答下面问题
      if ( 0 <= s.charAt(i)- '0' && s.charAt(i) - '0' <= 9) {    // Java中的isdigit()是什么
        stk_d.push((double)s.charAt(i) - '0');                   // 转化成int->double入栈
      } else if (s.charAt(i) == '+' || s.charAt(i) == '-'
                 || s.charAt(i) == '*' || s.charAt(i) == '/') {
        stk_ch.push(s.charAt(i));
      } else if (s.charAt(i) == ')') {
        double a = stk_d.pop(), b = stk_d.pop();    // 栈顶两个运算符出栈
        char ch = stk_ch.pop();
        double ans = 0.0;
        switch (ch) {
          case '+':
            ans = a+b;
            break;
          case '-':
            ans = a-b;
            break;
          case '*':
            ans = a*b;
            break;
          case '/':
            ans = a/b;
            break;
        }
        stk_d.push(ans);
      }
    }
    System.out.println(s + " = " + String.format("%.2f", stk_d.peek()));
  }
}
```

根据书中给出的代码, 改进一下:
`做出3点改进, 1个添加功能, 输入格式的牺牲`

```java
/* 改进:  但改进后, 对输入的输入格式有所限制
 * 并行边读边运算
 * 简化出栈后运算部分代码, 不用创造无关的对象
 * 添加sqrt功能
 */
// ( ( sqrt ( 25 )  -  3 ) * 4 )

import java.util.Scanner;
import java.util.Stack;
public class Evaluate {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Stack<String> stk_s = new Stack<String>();
    Stack<Double> stk_d = new Stack<Double>();
    while (read.hasNext()) {
      String s = read.next();                  // 读取字符串, 可省略对" "判断的代码
      if      (s.equals("(")) continue;
      // == 判断引用所指向的对象是否为同一个, equals判断对象的等价性, 现在成更相等
      else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")
               || s.equals("sqrt")) {
        stk_s.push(s);

      } else if (s.equals(")")) {
        double v = stk_d.pop();    // why? 因为像sqrt这种只需要取一个数, 按需求pop比较好
        String op = stk_s.pop();
        if (op.equals("+")) v += stk_d.pop();
        else if (op.equals("-")) v = stk_d.pop() - v;
        else if (op.equals("*")) v = stk_d.pop() * v;
        else if (op.equals("/")) v = stk_d.pop() / v;
        else if (op.equals("sqrt")) v = Math.sqrt(v);
        stk_d.push(v);
      } else {   // 既不是括号也不是字符, 按照double压入
        stk_d.push(Double.parseDouble(s));
      }
    }
    read.close();
    System.out.println(stk_d.peek());
  }
}
```

#### 1.3.2 集合类数据类型的实现

##### 1.3.2.1 定容栈

注意需要在构造函数中, 对String数组构造大小
```java
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
}
```

泛型版本:
<span style="color:red">注意构造栈时候, 创建泛型数组, 需要用Object类创建的对象后, 用Item[] 类型进行转换, 这个是Java的特性</span>
```java
// 实现一个定容栈(泛型版本)
import java.util.Scanner;
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
```

##### 1.3.2.3 数组大小
Java数组一旦创建, 无法改变; 需要手动实现, 动态的对栈空间的分配; 
`push时, 栈满扩容两倍； pop时, 剩余空间多于3/4, 缩小1/2, 以至于后面的更多push操作`


```java
// 对数组大小分配的方法
private void resize(int max) {
  Item[] temp = (Item[]) new Object[max];   // 用于存放数组数据的临时数组
  for (int i = 0; i < N; i++) {
    temp[i] = a[i];    // 转移数据, 此时N一定不会越界
  }
  a = temp;            // 数组对象指向新的对象引用
  // System.out.println("resize the item array, capacity = " + max);
}
```

```java
// push前先分配数组大小
public void push(Item elem) {
  if (N == a.length)     // why? 在上一次push中,N++, 如果N超过了构造大小, N将不发生改变吗?
    resize(a.length * 2);
  a[N++] = elem;
}
```

```java
// pop后, 回收游离对象, 缩减额外多余的数组空间
public Item pop() {
  Item item = a[--N];
  a[N] = null;           // 可以避免对象游离
  if (N > 0 && N == a.length/4) resize(a.length/2);   // 数组剩余空间太多, 缩小
  return item;
}
```

##### 1.3.2.4 对象游离
Java垃圾回收机制, 回收所有无法被访问的对象的内存。

但pop()的实现中, 只是改变了索引的大小, 元素的引用仍然存在于数组中

**保存一个不需要对象的引用, 称为游离**
只需要将不需要对象的引用值设置为null就ok了
```java
Item item = a[--N];
a[N] = null;           // 可以避免对象游离
```

##### 1.3.2.5 迭代

为动态调整栈大小的类加入可迭代版本
```java
// 测试
ResizeArrayStack<String> s = new ResizeArrayStack<String>();
// ...
for (String it : s) {
  System.out.println(it);
}
```

要使类可以迭代
> * 1, 在它的声明中加入implenments Iterable<Item>
> * 2, 在类中添加一个iterator()方法, 返回Iterator<Item>迭代器对象
> * 3, 在类内插入一个嵌套类
> * 4, 在程序开头, import java.util.Iterator;

step 1:
```java
// public class ResizeArrayStack implements Iterator<Item>
// public class ResizeArrayStack implements Iterable<Item>
public class ResizeArrayStack<Item> implements Iterable<Item> {   // 在栈类中嵌套Iterator
  // ...
}
```
<span style="color:red">注意一下类声明中是implenments Iterable<Item>, 证明这个集合是可迭代的, 在嵌套类中是, implements Iterator<Item> 证明它就是内嵌类的迭代器</span>

step 2:
```java
// 创建逆序迭代器, 可用于遍历数组
public Iterator<Item> iterator()    // 实现一个iterator()方法, 并返回一个Iterator对象
{ return new ReverseArrayIterator(); }
```

step3:
```java
private class ReverseArrayIterator implements Iterator<Item> {
  // Iterator类必须包含两个方法, hasNext()返回boolean值和 next()返回集合中的泛型元素
  private int i = N;
  public boolean hasNext() { return i > 0;}    // 索引此时比0小, 未到栈头
  public Item    next()    { return a[--i];}   // 获取逆序下一个元素
  public void    remove()  {               }   // remove函数常为空
}
```

为了和Iterator的结构保持一致
> * 如果调用remove(), 则抛出UnsupportedOperationException
> * 如果用例在调用next()时i为0, 则抛出NoSuchElementException
因为只在foreach中使用迭代器, 这些情况都不会出现啦, 所以忽略代码。


##### 1.3.2小结
先从定容栈讲起, 实现一个后进先出(LIFO)的栈。可以将元素类型释放出来, 变成一个泛型类。 又可以用N和a.length比较, 对数组小心进行动态扩容和索容, 注意这个阶段可能会出现对象的游离, 需要将对象指向null, 便于Java回收。接着介绍了如何将一个类，改写成可迭代的类(4个步骤)。
	
#### 1.3.3 链表

##### 1.3.3.6 其他位置的插入和删除操作

简单:
> * 在表头插入结点
> * 在表头删除结点
> * 在表尾插入结点

不容易:
> * 删除指定的结点
> * 在指定结点之前插入一个新结点

遍历整条链表找出指向该结点的结点(ex1.3.19), 这种解决方案所需时间和链表长度成正比;
`为此我们需要双向链表`, 其中每个结点都含有两个链接, 不同的方向。(ex1.3.31)

##### 1.3.3.7 遍历

遍历链表的简洁表达
```java
for (Node x = first; x != null; x = x.next) {
  // ...
}
```

##### 1.3.3.8 栈的实现

最优设计目标:
> * 它可以处理任意类型的数据;
> * 所需的空间总是和集合的大小成正比
> * 操作所需时间总是和集合的大小无关
最后一点解决了动态数组实现栈的算法中, 对数组扩容需要重新构造一个数组对象, 此操作的时间和数组的长度成正比O(n)的复杂度

**用链表实现下压栈**
```java
import java.util.Iterator;
public class Stack<Item> implements Iterable<Item>{
    private int N;        // 结点的数量
    private Node first;   // 首个结点
    private class Node {  // 链表的结点构造
        Item item;
	    Node next;
    }
    public int size() { return N;}
    public boolean isEmpty() { return first == null;}  // 首个结点指向null
    public void push(Item elem) {
        // 向栈中添加元素
        Node oldFirst = first;
	    first = new Node();
		first.next = oldFirst;
		first.item = elem;
		++N;
    }
	
	public Item pop() {
	    // 弹出栈顶元素
		if (first == null) return null;    // 栈不为空
		Item e = new Item();
		e = first.item;
		first = first.next;
		--N;
		return e;
	}
	
	public Iterator<Item> iterator() 
	{ return new StackIterator();}
	
	private class StackIterator implements Iterator<Item> {
	    // 顺序迭代器类
		private Node itFirst = new Node();
		itFirst = first;
		public boolean hasNext() { return itFirst != null; }
		public Item next() {
		    Item n_item = itFirst.item;
			itFirst = itFirst.next;
			return n_item;
		}
		public void remove() {     }
	}
}
```

##### 1.3.3.9 队列的实现
**用链表实现先进先出队列**
```java
public class Queue<Item> {
  private Node first;
  private Node last;
  int N = 0;
  private class Node {      // 定义结点的嵌套类
    Item item;
    Node next;
  }
  // 没有构造函数, first, last在空栈时, 都指向null, N = 0;

  // 判空(思考一下first == last为什么不行?)
  public boolean isEmpty() {
    // return first == last;      // why?
    return first == null;
  }

  // 获取结点个数
  public int size() { return N; }

  // 在尾部插入(插前判空, first进行额外处理)
  public void enqueue(Item e) {
    Node oldLast = last;
    last = new Node();
    last.item = e;                 // 新结点的元素对象
    last.next = null;              // 新结点指向null
    // oldLast.next = last;        // 原来尾部结点指向新插入的结点
    if (isEmpty()) first = last;
    else           oldLast.next = last;    // 空队列时, oldLast指向last为null的引用对象
    ++N;
  }
  
  // 在头部删除, 返回被删除的对象元素(删后判空, 对last进行额外处理)
  public Item dequeue() {
    if (isEmpty()) return null;  // 判空
    Item item = first.item;
    first = first.next;
    if (first == null)           // 如果删除的是最后一个元素
      last = null;
    --N;
    return item;
  }
}
```

<span style="color:red">**链表实现队列要注意的地方:**</span>

<span style="color:red">1, 为什么该实现队列的判空, 不用 first == last来判断.</span>
```sh
空队列, first = null, last = null;
队列中一个结点, first = last; first.next = null;
多个结点时, first != last
因此:
first == null, 只能说明有一个结点或空
```
<span style="color:red">2, 插入(尾部)前判空, 对first进行额外处理</span>
如果当前队列为空的, 插入一个结点, first = last;

否则的话, oldLast.next = last;   在空队列时, oldLast指向last为null

<span style="color:red">3, 删除前判空 + 删除(头部)后判空, 对last进行额外处理</span>

a, 若队列为空, 返回null

b, 若队列中只剩下最后一个元素（first.next == null）, 删除后last也要变成null




##### 1.3.3.10 背包的实现


**用链表实现背包**
```java
import java.util.Iterator;
public class Bag<Item> implements Iterable<Item> {
  private Node first;       // 链表的首结点
  // private int  N;        // 不需要知道背包中的数量
  private class Node {
    Item item;
    Node next;
  }
  public boolean isEmpty()
  { return first == null; }
  public int size()
  { return N; }
  public void add(Item e) {
    Node oldFirst = first;
    first = new Node();
    first.item = e;
    first.next = oldFirst;
    ++N;
  }
  public Iterator<Item> iterator() {
    return new ListIterator();
  }
  private class ListIterator implements Iterator<Item> {
    private Node current = first;
    public boolean hasNext() { return current != null;}
    public Item    next()    {
      Item item = current.item;
      current = current.next;
      return item;
    }
    public void    remove()  {                        }
  }
}
```

#### 1.3.4 综述

目前用两种方式表示对象集合, 数组和链表(顺序存储和链式存储).

|:数据结构     :|:优点                      :|:缺点                     :|
|--------------|---------------------------|--------------------------|
|数组          |通过索引可以直接访问元素       |在初始化时, 就需要知道元素数量|
|链表          |使用空间大小和元素的数量成正比  |需要通过引用访问任意元素     |

用下面步骤识别目标, 并使用数据抽象解决问题:
> * 定义API
> * 根据特定的应用场景开发用例代码
> * 描述一种数据结构(一组值的表示), 并在API所对应的抽象数据类型的实现中根据它定义类的实例变量
> * 描述算法(实现一组操作的方式), 并根据它实现类中的实例方法
> * 分析算法性能特点
#### 1.3 小结
这一节介绍了集合中对象的表示方法, 然后介绍了一些现代编程中的方法:泛型.迭代等; 然后从栈的数组表示(定容栈)开始讲起, 逐渐改进数组大小固定的不足, 最后引出链表这一个数据结构。然后用链表重新实现和下压栈(FILO), 先进先出的队列(FIFO), 以及背包(Bag), 跟之前在《数据结构》中所讲的区别就是, 抓住了链表这一数据结构的特点, 忽略了一些内存管理的细节, 又加入了自动装箱(AutoBoxing), 泛型(Generics)和迭代(Iterator)的新知识。

之后的章节中, 会以这个数据结构类基础, 介绍更多的数据结构.

#### Q+A
##### 为什么将Node声明成私有嵌套类, 为什么使用private?
可以将Node的方法和实例变量的访问范围控制在包含它的类中。

私有嵌套类的特点是: 只有包含它的类能够直接访问它的实例变量。
##### 编译后发现Stack.class 和 Stack$Node.class
第二个文件是内部类Node创建的, Java命名规则会使用$分隔外部类和内部类

##### 为什么不在一个单独的collection中实现所有数据结构的操作
这样属于**宽接口**,
> 1, 我们总是以API作为设计高效算法和数据结构的起点, 而设计只含几个操作的接口, 比复杂的接口简单.
> 2, 坚持窄接口, 是为了它们能限制用例的行为, 比如 后进先出对Stack<String>很重要, 和先进先出对Queue<String>, 限制了访问顺序

#### AutoBoxing Q+A
#### Generics Q+A
#### Iterator Q+A

