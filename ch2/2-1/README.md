# coursera-algorithms-part1

## 第2章 排序

### 2.1 初级排序算法

#### 2.1.1 规则
关注的主要对象是重新排列数组元素的算法, 其中每个元素都有一个主键(key).

元素和主键的具体性质在不同的应用中千差万别。

在Java中, 元素通常是对象, 对主键的抽象描述则是通过一种内置的机制, Comparable接口

##### 2.1.1.2 运行时间

排序成本模型:
> * 在研究排序算法时, 我们需要计算 比较和交换的数量。对于不交换元素的算法, 我们会计算 访问数组的次数

##### 2.1.1.3 内存

排序的内存开销和运行时间一样重要。

可以分为两类:
> * 除了 函数调用所需要的栈 和 固定数目的实例变量 之外无需额外内存的原地排序算法
> * 需要额外空间来存储另一份副本的其他排序方法

##### 2.1.1.4 数据类型

Java中封装的数字类型Integer和Double, 以及String和其他许多高级数据类型(如File, URL)都实现了Comparable接口。对于自己创建的类型, 只要实现Comparable接口, 就能保证用例代码可以将其排序.


compareTo()方法来定义对象的`自然次序`。
compareTo()必须实现一个全序关系,
> * 自反性, 对于所有的v, v=v;
> * 反对称性, 对于所有的v<w都有v>w, 且v=w时w=v;
> * 传递性, 对于所有的v.w和x, 如果v<=w且 w<=x, 则v<=x

compareTo()实现了主键抽象, 实现了接口任意数据类型的对象的大小顺序的定义.

但是, compareTo()不一定会进行比较实例的所有实例变量.

这本书里介绍的六种排序, 
> * 选择排序, 插入排序, 希尔排序, 归并排序, 快速排序, 堆排序 (冒泡排序)

#### 2.1.2 选择排序

选择数组中最小的元素, 让它和数组中第一个元素进行交换. 再次, 在剩下元素中找到最小的元素, 让它与数组的第二个元素交换位置,  如此往复, 直到将整个数组排序.

我自己根据思路, 先实现的选择算法
```java
public class Selection {
  public void sort(int[] a) {                   // 没有用统一的接口
    for (int i = 0; i < a.length - 1; i++) {
      int pMin = i;
      for (int j = i; j < a.length; j++) {      // 这里存在问题
        if (a[j] < a[pMin]) pMin = j;           // 没抽象出less方法
      }
      if (pMin != i) exch(a, i, pMin);          // 与上面的问题呼应
    }
  }

  public void exch(int[] a, int p, int q) {
    int tmp = a[p]; a[p] = a[q]; a[q] = tmp;
  }
  
  public static void main(String[] args) {
    int[] a = {3, 5, 2, 9, 6, 1};
    Selection s = new Selection();
    s.sort(a);
    for (int i = 0; i < a.length; i++) {
      System.out.printf(a[i] + " ");
    }
    System.out.println();

  }
}
```



1, 内层循环j=i+1, 从当前i的后一个元素开始比较大小
2, 排序算法中的方法, 都应该声明成静态.
3, 比较大小和交换数据抽象成less和exch方法, 将数据操作限制在这两个方法中, 使得代码的可读性和可移植性更好.

坑点:
1, 第一次用的时候, 把Comparable, 拼写成了Comparable

```java
int[] a = {3, 5, 2, 9, 6, 1};
sort(a);
```

```sh
incompatible types: int[] cannot be converted to Comparable[]
```
<span style="color:red"> 2, int是一个准确的数据类型, 它不是一个对象类型, int[] 不能自动装箱和拆箱成Comparable[]</span>

less方法中, 测试一下v.compareTo(w) < 0
```java
v必须是一个引用类型, 比如Comparable[]中的某一元素,
v<w, -1;
v=w, 0;
v>w, 1
```


修改后的代码:
```java
public class Selection {
  public static void sort(Comparable[] a) { 
    for (int i = 0; i < a.length; i++) {         // 因为j=i+1, 内循环不会比较最后一个元素
      int min = i;
      for (int j = i+1; j < a.length; j++) {     // 略去与自己的比较
        if (less(a[j], a[min])) min = j;         // less抽象成方法
      }
      exch(a, i, min);
    }
  }

  public static boolean less(Comparable v, Comparable w) {
    return v.compareTo(w) < 0;                   // less方法中调用compareTo比较
  }

  public static void exch(Comparable[] a, int i, int j) {
    Comparable tmp = a[i]; a[i] = a[j]; a[j] = tmp;
  }

  public static boolean isSorted(Comparable[] a) {
    // 测试数组元素是否有序
    for (int i = 1; i < a.length; i++) {
      if (less(a[i], a[i-1])) return false;
    }
    return true;
  }

  public static void show(Comparable[] a) {
    for (int i = 0; i < a.length; i++) {
      System.out.printf(a[i] + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Integer[] a = {3, 5, 2, 9, 6, 1};
    sort(a);
    assert(isSorted(a));
    show(a);
  }
}
```

排序算法每次都会将第i小的元素, 放到第a[i]之中

选择排序性能分析:

> * 运行时间和输入无关. 找出最小元素的扫描和下一遍扫描没有什么关系, 如果已经排序好的和未排序的一样耗时
> * 数据移动最少, 只用了N次交换, 后面其他任何的排序算法都不具备这个特征(线性对数, 平方级别)

#### 2.1.3 插入排序
为了给插入的元素腾出空间, 将其余所有元素在插入之前后向右移动一位。

思路: 当前索引左边的所有元素都是有序的, 但它们最终位置不确定, 为了给更小的元素腾出空间, 他们可能会被移动.但是当索引达到数组的右端时, 数组排序就完成了.

性能：
> * 排序时间取决于输入中元素的初始顺序(越接近有序, 比随机和逆序都快的多.)
> * 插入排序对已经排序完的数组, 运行时间是线性的, 这点比选择排序平方级别的快很多(扫描一遍就发现已经有序)

我自己实现的插入排序

```java
public class Insertion {
  public static void sort(Comparable[] a) {
    int p = 1;
    while (p < a.length) {
      if (less(a[p], a[p-1])) {
        for (int i = 0; i < p; i++) {
          if (less(a[p], a[i])) {           // 插入时, 不用判断是否夹着, 只判断是否出现比a[p]大的值
            Comparable tmp = a[p];
            for (int j = p; j > i; j--) {
              a[j] = a[j-1];
            }
            a[i] = tmp;
            break;
          }
        }
      }
      p++;
    }
  }
  // ..
}
```


书中给出的代码
```java
public class Insertion {

  public static void sort(Comparable[] a) {
    for (int i = 1; i < a.length; i++) {
      for (int j = i; j > 0 && less(a[j], a[j-1]); j--) {
        exch(a, j, j-1);
      }
    }
  }

}
```

标准的插入排序算法, i从左到右扫描每个位置, 当前位置比前一位置小less(a[j], a[j-1]), 向前交换, 直到比前一个位置大或者到数组最左边.

而我的算法则是, 扫描某个位置, 如果当前位置比前一位置小, 先用一层循环找到当前元素应该放的位置, 然后再一层存换让数列整体移动, 再把这个元素插入进去。

<span style="color:red">区别在于, 书中是边移动边找到应该在的位置; 而我的做法是, 先找位置, 再整体移动</span>


i从左向右移动过程中, 左侧元素总是有序的, 当i到达数组最右端时, 整个数列有序.

接下来考虑, 对部分有序的定义

部分有序: 如果数组中的倒置(相邻两个数组相反)的元素数量小于数组大小的某个倍数, 那么这个数组部分有序..

<span style="color:red">适合插入排序的数组特征:</span>
> * 数组中每个位置距离它最终的位置都不远
> * 有序大数组接无序小数组
> * 数组中只有几个数倒置

```cpp
插入排序需要的交换操作和数组中的倒置的数量相同, 
需要的比较次数大于等于倒置的数量, 小于倒置数量+数组大小-1
```

提高插入排序的速度的方法, 将数组中较大的元素都向右移动, 这样访问数组的次数就能减半. ex2.1.25
```java
// .. 向右边移动, 代替交换, 难道移动不是交换吗？暂时不很理解
```


插入排序是其他一些高级排序算法的中间过程.



#### 2.1.4 排序算法的可视化

使用StdDraw来来绘制棒状图, 而不是打印结果的show()方法. 练习2.1.18
```java
public static void show() {}
```

将轨迹变成动画, 2.1.17
```java
public static void show() {}
```

通过轨迹可以看出, 
> * 插入排序只访问索引左侧元素, 不访问右侧元素
> * 选择排序只访问索引右侧元素, 不访问左侧元素

#### 2.1.5 比较两种排序算法

这一节介绍了SortCompare, 学会这套方法后, 对渐进式的算法研究十分重要, 能够通过这类程序, 来了解改进后的算法的性能是否产生了新的预期进步.

比较两个算法的方法:
> * 实现并调试它们
> * 分析它们的基本性质
> * 对它们的相对性能作出猜想
> * 用实验验证我们的猜想

**只有研究那些最重要的算法的专家才会经历完整的研究过程, 但每个使用算法的程序员都应该了解算法的性能特性背后的科学过程.**

```
命题A: 对于长度为N的数组, 选择排序需要大约 (N^2)/2 次比较 和 N次交换
命题B: 对于随机排列的长度为N且主键不重复的数组, 平均情况下插入排序需要~(N^2)/4次比较, -(N^2)/4交换, 最坏情况下需要~(N^2)/2次比较 和 ~(N^2)/2交换, 最好情况下需要N-1次比较和0次交换
命题C: 插入排序需要的 交换操作 和 数组中倒置的数量相同, 需要的比较操作次数大于等于倒置的数量, 小于等于倒置的数量+数组长度-1
```


```
命题D: 对随机排序的无重复主键的数组, 插入排序和选择排序的运行时间是平方级别的, 两者只比应该是个常数
```


time()函数中, 用可以调用不同的排序算法, 然后用Stopwatch来记录时间

Stopwatch类的核心就是调用System.currentTimeMillis(), 利用两次打点的差/1000得到秒数(millis是毫秒的意思)

```java
public class Stopwatch {
  private long start;
  public Stopwatch() {
    start = System.currentTimeMillis();
  }
  public double elapsedTime() {
    long now = System.currentTimeMillis();
    return (now-start) / 1000.0;
  }
}
```

```java
public class SortCompare {
   public static double time(String alg, Double[] a) {
     Stopwatch timer = new Stopwatch();
     if (alg.equals("Insertion")) Insertion.sort(a);
     if (alg.equals("Selection")) Selection.sort(a);
     // if (alg.equals("Shell"))     Shell.sort(a);
     // if (alg.equals("Merge"))     Merge.sort(a);
     // if (alg.equals("Quick"))     Quick.sort(a);
     // if (alg.equals("Heap"))      Heap.sort(a);
   }
}
```


实现两种算法比较后, Insertion反而更慢呢?
```sh
$ java SortCompare Insertion Selection 1000 10
For 1000 random Double
  Insertion is 0.7 times faster than Selection
```

<span style="color:red">应该time算的是总时间, 因此, t1比t2快多少倍, 应该用t2/t1来计算才对。</span>

<span style="color:red">但是我在解决这个问题时, 对两个算法做了优化, 将在循环条件中求a.length改成在循环外部求N = a.length, 将循环条件替换, 少做了很多运算</span>

下面是SortCompare类的代码
```java
// 实现多个排序算法之间的比较

public class SortCompare {
  public static double time(String alg, Double[] a) {
    Stopwatch timer = new Stopwatch();
    if (alg.equals("Insertion"))    Insertion.sort(a);
    if (alg.equals("Selection"))    Selection.sort(a);
    // if (alg.equals("Shell"))     Shell.sort(a);
    // if (alg.equals("Merge"))     Merge.sort(a);
    // if (alg.equals("Quick"))     Quick.sort(a);
    // if (alg.equals("Heap"))      Heap.sort(a);
    return timer.elapsedTime();
  }

  public static double timeRandomInput(String alg, int N, int T) {
    // 使用算法alg对T个长度为N的数组排序
    double total = 0.0;
    Double[] a = new Double[N];
    for (int t = 0; t < T; t++) {
      for (int j = 0; j < N; j++)
        a[j] = StdRandom.uniform();
      total += time(alg, a);
    }
    return total;
  }

  public static void main(String[] args) {
    String alg1 = args[0];
    String alg2 = args[1];
    int N = Integer.parseInt(args[2]);
    int T = Integer.parseInt(args[3]);
    double t1 = timeRandomInput(alg1, N, T);
    double t2 = timeRandomInput(alg2, N, T);
    System.out.printf("For %d random Double\n  %s is", N, alg1);
    System.out.printf(" %.1f times faster than %s\n", t2/t1, alg2);
  }
}
```

```sh
java SortCompare Insertion Selection 500 10
For 500 random Double
  Insertion is 1.6 times faster than Selection
```
无序的数组, 插入排序比选择排序快了1.7倍

有些情况下, 主键有重复或者排列不随机, 需要用StdRandom.shuffle()来将一个数组打乱


最后讲了, 学习初级算法的必要性:
> * 它们帮助我们建立一些基本的规则
> * 它们展示了一些性能的基准
> * 在某些特殊情况下, 它们也是很好的选择
> * 更强大开发的基石
