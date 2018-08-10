# coursera-algorithms-part1

## 1 基础
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


#### Exercises
	
##### 1.3.4 使用栈判定其中的括号对是否完整匹配

先为Stack类添加一个top()方法
```java
// Parentheses.java
// 使用栈判定其中的括号对是否完整匹配
// case:
// [()]{}{[()()]()}
// [(])
// ()[
// )

import java.util.Scanner;
public class Parentheses {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    String s = read.next();
    Stack<Character> stk_ch = new Stack<Character>();
    int ok = 1;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
        stk_ch.push(s.charAt(i));
      }
      else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
        if (stk_ch.isEmpty()) {       // 右括号要匹配左括号时, 栈已空, 无法匹配
          ok = 0; break;
        }
        if      (stk_ch.top() == '(' && s.charAt(i) == ')') { stk_ch.pop(); }
        else if (stk_ch.top() == '[' && s.charAt(i) == ']') { stk_ch.pop(); }
        else if (stk_ch.top() == '{' && s.charAt(i) == '}') { stk_ch.pop(); }
        else { ok = 0; break; }   // 右括号匹配失败
      }
    }
    if (stk_ch.isEmpty() && ok == 1) {  // 打印的端口变窄
      System.out.println("true");
    } else {
      System.out.println("false");      // 还没有匹配完全 ()[
    }
  }
}
```
	


##### 1.3.5 用栈实现二进制转换
O(n), 可以减少一个逆序输出
```java
Stack<int> stack = new Stack<int>;
while (N > 0) {
  stack.push(N % 2);
  N /= 2;
}
for (int i : stack) {System.out.print(i);}
System.out.println();
```

##### 1.3.6 为stack添加一个peek(), 已经做了
```java
public Item peek() {
  // 获得栈顶元素, 但不删除(需要判空)
  if (isEmpty()) throw new NoSuchElementException;
  return first.item;
}
```
	
##### 1.3.10 中缀表达式转化为后缀表达式
```java
Solution:
// 一个后缀表达式queue, 一个操作符stack
1, 忽略左括号
2, 遇到数入queue
3, 遇到操作符入stack
4, 遇到')', 操作符出栈入列
5, FIFO打印队列
```


##### 1.3.11 后缀表达式的计算

只用一个栈存储操作数, 每次读到操作符, 出栈两次.
<span style="color:red">注意, 减法和除法出栈后的顺序,  原意是 a - b;  出栈后的顺序是 b - a;  因此要做些细微处理</span>
```java
import java.util.Scanner;
public class EvaluatePostfix {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Stack<Double> stk_d = new Stack<Double>();
    while (read.hasNext()) {
      String s = read.next();
      double v = 0.0;
      // if (s.equals(" ") || s.equals("(")) continue;     // 后缀表达式无括号, 且这种读取方式无空串
      if (s.equals("+"))      { v = stk_d.pop() + stk_d.pop(); stk_d.push(v);}
      else if (s.equals("-")) { v = -(stk_d.pop() - stk_d.pop()); stk_d.push(v);}
      else if (s.equals("*")) { v = stk_d.pop() * stk_d.pop(); stk_d.push(v);}     // 小心溢出
      else if (s.equals("/")) { v = 1 / (stk_d.pop() / stk_d.pop()); stk_d.push(v); }
      else stk_d.push(Double.parseDouble(s));
    }
    System.out.println(stk_d.peek());
  }
}

/*
$ java InfixToPostfix | java EvaluatePostfix
( (3 - 4) * 5 )
-5.0
*/
```

##### 1.3.14 实现一个动态分配数组大小的队列

```java
// Copyright [2018] <mituh>
// ResizingArrayQueueOfStrings.java

import java.util.NoSuchElementException;
import java.util.Scanner;
public class ResizingArrayQueueOfStrings<Item> {
  private int first;     // 队首元素索引
  private int last;      // 指向队尾元素的下一个位置
  private Item[] a;

  public ResizingArrayQueueOfStrings() {    // 动态分配数组大小的构造函数
    // Item[] a = (Item[]) new Object[1];   // why?又是相同的问题
    a = (Item[]) new Object[1];
    first = 0; last = 0;
  }

  // 判空
  public boolean isEmpty()
  {return first == last;}
  public int size()
  { return last - first;}

  // 根据需要分配数组大小
  private void resize(int max) {
    Item[] temp = (Item[]) new Object[max];
    for (int i = 0; i < size(); i++) {
      temp[i] = a[i+first];      // 将队列的扩容缩容量的同时
    }
    a = temp;                    // a指向新的对象引用
    last -= first;               // 整理至数组头部
    first = 0;
  }

  public Item front() {
    if (isEmpty()) throw new NoSuchElementException("Queue empty");
    return a[first];
  }

  public void enqueue(Item e) {
    if (last == a.length) {    // 队列的尾部满
      resize(a.length * 2);    // 扩容两倍, 且利用数组前面的空间
    }
    a[last++] = e;
  }

  public Item dequeue() {
    if (isEmpty()) throw new NoSuchElementException("Queue overflow");
    Item e = a[first];
    a[first++] = null;              // 游离被删除对象, first向后移动一位
    if (size() == a.length / 4) {   // 删除后剩余空过多, 缩容
      resize(a.length / 2);
    }
    return e;                       // 不清楚这样是不是会导致e指向null呢?
  }
}
```

#### Creative Problems

##### 1.3.37 Josephus问题

###### 题目:
Josephus问题。在这个古老的问题中, N个身陷绝境的人一致同意通过以下的方式减少生存人数。他们围坐成一圈(位置记为0到N-1)并从第一个人开始报数, 报到M的人会被杀死, 直到最后一个人留下来。传说中Josephus找到了不会被杀死的位置。编写一个Queue的用例Josephus, 从命令行接受N和M并打印出人们被杀死的顺序(这也将显示Josephus在圈中的位置)。


###### 输入:
$ java Josephus 7 2

###### 输出:
1 3 5 0 4 2 6 


###### 思路:

交换不断交换两个队列, 直到所有元素出栈为止

```java
import java.util.Scanner;
public class Josephus {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    int M = read.nextInt();
    Queue<Integer> q1 = new Queue<Integer>();
    Queue<Integer> q2 = new Queue<Integer>();
    // Queue<Integer> q_ans = new Queue<Integer>();  // 直接打印储存
    for (int i = 0; i < N; i++) { q1.enqueue(i);}
    int num = 1, cnt = 0;
    while (cnt != N) {
      if (!q1.isEmpty()) {
        if (num != M) { q2.enqueue(q1.dequeue()); num++; }   // q1队首出队, 进q2队
        else {
          System.out.print(q1.dequeue() + " ");
          num = 1; cnt++;
        }
      } else {
        if (num != M) { q1.enqueue(q2.dequeue()); num++; }   // q1队首出队, 进q2队
        else {
          System.out.print(q2.dequeue() + " ");
          num = 1; cnt++;
        }
      }
    }
    System.out.println();
  }
}
```

###### 改进:
<span style="color:red">但看了书中给出的解答之后, 大为吃惊, 他竟然在**头部出列,同时, 在尾部入列**</span>

```java
public class Josephus {
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
	int M = Integer.parseInt(args[1]);
	Queue<int> queue = new Queue<int> ();
	for (int i = 0; i < N; i++) { queue.enqueue(i); }
	while (!queue.isEmpty()) {
	  for (int i = 0; i < m-1; i++) {
	    queue.enqueue(queue.dequeue());   // 出列 同时 进列
	  }
	  System.out.print(queue.dequeue() + " ");
	}
	System.out.println();
  }
}
```

Output:
```sh
$ java Josephus 7 2
1 3 5 0 4 2 6 
```

队列头进尾出的策略, 你理解了多少？

###### Queue链表实现的注意点
<span style="color:red"> 注意如下几个: </span>
enqueue尾部插入
> * 1, 旧尾结点用一个Node保存, last指向新的引用
> * 2, last结点指向下一个null结点
> * 3, 判空，if (first == null) first = last;
> * 4, 非空, 上旧尾结点与新尾结点链接

dequeue头部删除
> * 1, 判空, 抛出异常
> * 2, 保留返回值, first = first.next;删除结点
> * 3, 判空, last = null; 若删除前只剩一个结点, first.next为空, 

##### 1.3.42 复制栈 why?

构造Node结点来实现复制Stack的构造函数, why?

```java
Node(Node x) { this.item = x.item; this.next = x.next; }   // 使用结点来复制栈
```

```java
public Stack(Stack<Item> s) {
    // 使用Node构造Stack
    if (s.first != null) {
      first = new Node(s.first);
      for (Node x = first; x.next != null; x = x.next)
        x.next = new Node(x.next);
    }
  }
}  
```

##### 1.3.50 快速出错的迭代器

用计数器记录push()和pop()的次数, 作为Iterator的实例变量
```java
private class ArrayStackIterator implements Iterator<Item> {
    private Node current = first;
    private int orig_pop = pop_n;
    private int orig_push = push_n;
    public boolean hasNext() {
      if (pop_n != orig_pop || push_n != orig_push) {
        throw new ConcurrentModificationException("Revise data in iteration");
      }
      return current != null;
    }
    public Item next() {
      if (pop_n != orig_pop || push_n != orig_push) {
        throw new ConcurrentModificationException("Revise data in iteration");
      }
      Item e = current.item;
      current = current.next;
      return e;
    }
    public void remove() {                          }
  }
```



#### 1.3 练习小结

完成了一些栈和队列的习题, 对栈和队列的链表构造记忆加深了, 也应用到了一些题中。
但由于这星期的课程设置要完成1.5节, 所以把以下题目放到下星期学有余力去完成:

> * 课本后链表的专项练习没做, 涉及到双向链表
> * 提高题中还有几道那题不太能理解, 复制栈, 栈的可生成性
> * 提高题中关于双向队列, 栈队列, 随机背包, 随机队列这些题还没实现,
> * Web exercises 没做(这里有62题!!)

这些题目是要回来继续做的, 但先对算法知识, 优先广度搜索。

