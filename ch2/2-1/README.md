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

选择排序性能分析:

> * 运行时间和输入无关. 找出最小元素的扫描和下一遍扫描没有什么关系, 如果已经排序好的和未排序的一样耗时
> * 数据移动最少, 只用了N次交换, 后面其他任何的排序算法都不具备这个特征(线性对数, 平方级别)
