# coursera-algorithms-part1

## 1 基础

### 1.5 案例研究: union-find 算法

#### 1.5.1 动态连通性

一对整数p和q是`相连`的.

等价关系:
> * 自反性: p和p是相连
> * 对称性: p和q是相连的, q和p是相连的.
> * 传递性: 如果p和q是相连的, 且q和r是相连的, 那么p和r也是相连的.

等价关系能将对象分为多个等价类。

限定话题, 一些网络方面的术语:
对象->`触点`, 整数对 -> `连接`, 等价类 -> `连通分量`

设计算法的任务 转换成 实现API,
> * 定义一种数据结构表示已知的连接
> * 基于此数据结构实现高效的union(), find(), connected() 和 counted()方法

<span style="color:red">从这里可以看出, 数据结构和算法是如此紧密联系, 数据结构的性质将直接影响到算法的效率</span>

自己先实现union-find的API的各种算法
```java
// union-find算法实现

import java.util.Scanner;
public class UF {
  private int[] id;
  private int N;

  public UF(int N) {
    id = new int[N];
    for (int i = 0; i < N; i++) { id[i] = i; }   // 每个触点只包含自己的分量
    this.N = N;
  }

  // 返回索引p的连通分量标识符
  public int find(int p) {
    return id[p];
  }

  // 判断两个触点是否连接
  public boolean connected(int p, int q) {
    return find(p) == find(q);                   // p,q的标识符是否相同, id[p] == id[q]
  }

  // 返回当前连通分量数目
  public int count() { return N;}

  public void union(int p, int q) {
    // if (connected(p, q)) return;
    // p, q两个触点未连通
    // q的所有连通分量标识符, 与p一致
    int sp = find(p); int sq = find(q);
    for (int i = 0; i < id.length; i++) {
      if (id[i] == sq) { id[i] = sp; }          // 归并为一个分量
    }
    --N;                                        // p.q连接后, 连通分量数-1
  }

  public static void main(String[] args) {
    // 解决由标准输入得到的动态连通性问题
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    UF uf = new UF(N);
    while (read.hasNextInt()) {
      int p = read.nextInt();
      int q = read.nextInt();              // 读取整数对
      if (uf.connected(p, q)) continue;    // 如果已经连通则忽略
      uf.union(p, q);                      // 归并分量
      System.out.println(p + " " + q);     // 打印新增连接
    }
    System.out.println(uf.count() + " Components");
  }
}
```

为了分析算法, 我们猜测, 各种算法在一台特定的计算机上的运行时间在这个量乘以某个常数的范围之内.
即:

union-find的成本模型。在研究实现union-find的API的各种算法时, 我们统计的是**访问任意数组元素的次数, 无论读写**
	
#### 1.5.2 实现

讨论三种不同实现, 均以触点为索引的id[]数组来确定两个触点是否存在相同的连通分量中

##### 1.5.2.1 quick-find算法
```
public void union(int p, int q) {
    // 将p和q归并到相同的分量中
    int pID = find(p);
    int qID = find(q);

	// 已经在相同分量中
    if (pID == qID) return;

    // 将p的分量名重新命名为q的名称
    for (int i = 0; i < id.length; i++) {
        if (id[i] == pID) { id[i] = qID; }          // 归并为一个分量
    }
    --count;                                        // p.q连接后, 连通分量数-1
}
```


##### 1.5.2.2 quick-union算法

我的理解:
<span style="color:red">通过归并两个触点的**根触点**, 去归并两个触点所属的**连通分量**。</span>
此时相同的连通分量之间, 不是简单的标识符相等关系, 而是需要通过`链接`寻找根触点, 若根触点标识符相同, 则属于同个连通分量；
反之, 如果根触点标识符不同, 需要union两个触点, 就只需要修改一个根触点a的下一个触点b, 这个时候, a不再是根触点, b同时成为p和q的根触点.

这种算法通过寻找根触点的方法, `减少了很多对id数组的写操作`, 每次连接只需要修改一次, 在访问时, 虽然要根据链接到根触点, 但是这条链中, 除了同一连通量内的触点之外, 没有其他额外多余的结点了。

```java
  // 返回索引p的根触点的标识符
  public int find(int p) {
    while (p != id[p]) p = id[p];                // 当p指向它自己时, end loop, p是根结点
    return p;                                    // 所有的子触点的标识符都是根触点的标识符
  }

  // 将p和q的根结点统一
  public void union(int p, int q) {
    // 将p和q归并到相同的分量中
    int pRoot = find(p);
    int qRoot = find(q);
    
    // 已经在相同分量中
    if (pRoot == qRoot) return;

    // 将p的根触点重新命名为q的根触点
    id[pRoot] = qRoot;                          // 此时同一连通分量中, 存放的是索引, 形成了链接
    --count;                                    // p.q连接后, 连通分量数-1
  }
```


但这个解决连通性问题使用quick-union算法在最坏的情况下, 也是平方级别的...想象一下树根很深, 所有树都是在一个根触点上, 最终只得到一个分量的情况...


**对树的定义**:
> * 大小: 树的结点数量;
> * 结点深度: 该结点到根结点你的路径上的连接数
> * 高度: 它所有结点中的最大深度

##### 1.5.2.3 加权quick-union算法

quick-union算法的问题在于, 根触点在链接对象的选择上是随意的, 经常会把大树, 链接到小树上, 这样就加深了总体的树的长度, 造成了更多的访问次数。 为此, 提出一种加权quick-union算法, 当连通分量归并时, 只会把小树绑到大树上, 大树的根结点, 作为小树的新根结点.

**具体的代码如下：**

添加一个数组, 用于记录树中的结点数
```java
private int[] size;     // 实例变量记录每个触点的深度
```

构造函数中, 设置1为每个结点的初始深度
```java
{
  public WeightedQuickUnionUF(int N) {
    id = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;            // 每个触点只包含自己的分量
      size[i] = 1;          // 每个结点初始深度为1
    }
    count = N;
  }
}
```

修改union方法, 将结点深度小的根结点, 重新命名为深度较大的根结点
```java
{
  public void union(int p, int q) {
    // 将p和q归并到相同的分量中 -> 将p和q的根结点统一
    int pRoot = find(p);
    int qRoot = find(q);
    
    // 已经在相同分量中
    if (pRoot == qRoot) return;

    // 将结点深度小的根结点, 重新命名为深度较大的根结点
    if (size[pRoot] < size[qRoot]) { size[qRoot] += size[pRoot]; id[pRoot] = qRoot;}
    else                           { size[pRoot] += size[qRoot]; id[qRoot] = pRoot;}
    
    // 将p的根触点重新命名为q的根触点(quick-union)
    // id[pRoot] = qRoot;                          // 此时同一连通分量中, 存放的是索引, 形成了链接

    /*
    // 将p的分量名重新命名为q的名称(quick-find)
    for (int i = 0; i < id.length; i++) {
      if (id[i] == pID) { id[i] = qID; }          // 归并为一个分量
    }
    */
    --count;                                      // p.q连接后, 连通分量数-1
  }
}
```

在这种算法下, 实际应用中, 只有一个结点的数被归并到更大的书中的情况很常见, 这样该结点到根结点的距离也只有一条链接而已.

##### 1.5.2.8 三种情况的比较和最优算法

|算法                 |构造函数     |union()   |  find()|
|--------------------|------------|----------|--------|
|quick-find算法       |N           |N         |1       |
|quick-union算法      |N           |树的高度   |树的高度 |
|加权quick-union算法   |N           |lgN      | lgN    |
|路径压缩加权          |N           | 非常接近1 | 非常接近1|
|理想情况             |N           | 1        | 1       |


路径压缩加权是指, 在理想情况下, 希望每个结点都之间链接到根结点, 
方法就是检查结点的同时, 同时将他们直接链接到根结点.

要实现它， 只需要为find()添加一个循环, 将在路径上遇到的所有结点都直接链接到根结点。

但实际情况下, 已经不存在对quick-union的更多改进..

##### 1.5.2.9 分摊成本图像

结合着练习1.5.16绘制分摊成本的图像, 

处理第i个连接时, 用一个变量cost记录期间访问数组的次数, 并用一个变量total记录到目前为止的总次数, 在(i, cost)画出灰点, 在(i, total / i)画红点

两个思路, 是放在main函数里, 还是放在QuickFindUF类里面定义实例变量

1, 如果定义在类中的话, 怎么识别是哪次union (i不确定), 
先在main函数中定义

2, 在main函数中定义, 又不能看到对象内部的访问数组情况, 因此还是得在类中定义, 写一个开始和结束的方法, 求cost就是返回这两个方法之间的访问次数差,  求total就是先在循环外部调用start()方法, 在每个循环结束时, 调用end()?

```
连通性问题的三个算法，画图测试了一下, 一共有900组左右数据, 灰点代表当前这组数据, 在这个算法下, 对数组要访问（读和写）的次数；红点代表，从第一组数据当当前这组数据，一共加起来对数组的访问的次数/之前一共有多少组数据（也就是拼摊的访问次数）。

三张图分别代表三个算法，第一个图的y值是-2000, 2000; 2, 3图的y值都是-100, 100，也就是每组数据运算, 控制在100次以内。。
```




#### Exercises

#### Creative Problems

##### 1.5.12 实现路径压缩的quick-union算法

在find()方法中, 将从p到根结点的路径上的每个触点都连接到根结点, 修改后给出一列输入, 使该方法能够产生一列长度为4的路径。

我自己的实现:
```java
{
  public int find(int p) {
    int[] nodes = new int[id.length];
    int n = 0;
    while (p != id[p]) {
      nodes[n++] = p;
      p = id[p];
    }
    int root = p;
    for (int i = 0; i < n-1; i++) {    // n-1个本来就是根结点的直接子结点
      id[nodes[i]] = root;
    }
    return root;
  } 
}
```

网站上给出的实现, 省去了新数组的创建, <span style="color:red">注意这段新插入while循环结点更新顺序很有意思</span>
```java
{
  public int find(int p) {
    int root = id[p];
    while (root != id[root]) {root = id[root];}       // 只是为了找到根结点
    while (p != root) {                 // 当初始结点到根结点过程中, 各个结点
      int newP = id[p];                 // 保存p的原来的下个结点
      id[p] = root;                     // 更新p的下个结点
      p = newP;                         // p更新为原来的下个结点
    }
    return root;
  }
}
```

至于这个长度为4的路径我是这样给的
```sh
10
0 1
0 2
0 3
0 4
```

这样只减少一个连通分量, 该连通分量有两个结点, 错误.

```sh
1 0
2 0
3 0
4 0
```

这样会生成一棵四叉树, 错误.

我认为生成不了一条长度为4的路径

##### 1.5.13 实现路径压缩加权的quick-union算法

加权版本, 仍旧要求我去给出建立长度为4路径的输入

注意, 在find()方法中, 将路径上的结点链接到根结点的操作, 要更新这些结点的深度为1

对于路径压缩来说, 核心问题还是find()对每个结点链到根结点的操作
```java
{
  public int find(int p) {
    int root = id[p];
    while (root != id[root]) {root = id[root];}       // 只是为了找到根结点
    while (p != root) {                 // 当初始结点到根结点过程中, 各个结点
      int newP = id[p];                 // 保存p的原来的下个结点
      id[p] = root;                     // 更新p的下个结点
      size[p] = 1;                      // 更新完成后的结点深度为1
      p = newP;                         // p更新为原来的下个结点
    }
    return root;
  }
}
```


对于加权来说, 关键在于定义size实例变量和归并union()中比较两棵树的结点数量

```java
private int[] size;
```

```java
public WeightedQuickUnionPathCompressionUF(int N) {
    id = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
    }
    count = N;
  }
```

```java
{
  public void union(int p, int q) {
    if (connected(p, q)) return;   // 在判断connected时, 已将子结点与根连接直接相连
    int pRoot = find(p);           // 查找次数
    int qRoot = find(q);

    // 将q的根触点, 作为p的根触点
    if (size[pRoot] < size[qRoot]) {size[qRoot] += size[pRoot]; id[pRoot] = qRoot;}
    else                           {size[pRoot] += size[qRoot]; id[qRoot] = pRoot;}
    count--;
  }
}
```

另外一个问题, 这个size(), 比较的是结点的深度还是广度呢? 如果是结点深度的话, 应该是要记录深度最大的作为更结点的size()， 广度的话就是要记录更结点直接连着的子结点数。
这里分析下来, 应该只是简单的结点数量。

##### 1.5.14 根据高度加权的quick-union算法

上一道题中, 我已经讨论了是根据深度加权(树的最大深度), 还是根据广度加权(子结点数量).

这道题就要求, 根据高度加权, 但记录的是树的高度总是将较矮的树, 记录到较高的树上.

思路是:
<span style="color:red">在保留size数组存放结点数量的基础上, 加入height数组, 存放根结点的高度.</span>
//  WeightedQuickUnionByHeightUF.java
```java
int[] height;
```

```java
for (int i = 0; i < N; i++) {
  height[i] = 0;
}
```

```java
// 两棵树高度不等, 合并不会增加树的高度, 因为是合并到根结点上, 合起来不会超过当前高度
if      (height[pRoot] < height[qRoot]) {id[pRoot] = qRoot; size[qRoot] += size[pRoot];}
else if (height[pRoot] > height[qRoot]) {id[qRoot] = pRoot; size[qRoot] += size[pRoot];}
else {   // 两棵树高度相等
  if (size[pRoot] < size[qRoot]) {   // 仍旧按照结点数量决定合并主次
    id[pRoot] = qRoot; size[qRoot] += size[pRoot]; 
    height[qRoot]++;                 // 合到q上, q增高
  } else {
    id[qRoot] = pRoot; size[pRoot] += size[qRoot]; 
    height[pRoot]++;                 // 合到p上, p增高
  }
}
```

书本网站给出的代码, 在高度一样时, 没考虑结点数量问题, 应该是为了根据规模量而不用去判断结点数量, 简化算法, 但我仍旧觉得会出现深度b与a一样, 结点数a却并b多很多的情况, 这时候, 如果把a简单绑在b上, 恐怕不合理.

##### 1.5.16 均摊成本图像
结合着练习1.5.16绘制分摊成本的图像, 

处理第i个连接时, 用一个变量cost记录期间访问数组的次数, 并用一个变量total记录到目前为止的总次数, 在(i, cost)画出灰点, 在(i, total / i)画红点

两个思路, 是放在main函数里, 还是放在QuickFindUF类里面定义实例变量

1, 如果定义在类中的话, 怎么识别是哪次union (i不确定), 
先在main函数中定义

2, 在main函数中定义, 又不能看到对象内部的访问数组情况, 因此还是得在类中定义, 写一个开始和结束的方法, 求cost就是返回这两个方法之间的访问次数差,  求total就是先在循环外部调用start()方法, 在每个循环结束时, 调用end()?

我的实现:
```java
// 实现quick-find算法, 并画出分摊成本图像

import java.util.Scanner;
public class QuickFindUF {

  private int[] id;
  private int count;
  private long visit;
  public QuickFindUF(int n) {
    id = new int[n];
    for (int i = 0; i < n; i++) {
      id[i] = i;     // 每个触点独立成为一个连通分量
    }
    count = n;
    visit = 0;       // 构造时, 访问数组的次数清空
  }

  // 返回连通分量计数
  public int count() { return count;}

  // 返回索引为p的触点, 所属的连通分量
  public int find(int p) {
    visit++;
    return id[p];
  }

  // 触点p和触点q是否已经连接
  public boolean connected(int p, int q) {
    // 判断p, f是否相连, 是否属于同一个连通分量   // 是否是同一个根结点
    return find(p) == find(q);
  }

  // 连接触点p和q
  public void union(int p, int q) {
    int pID = find(p);
    int qID = find(q);
    if (pID == qID) return;
    for (int i = 0; i < id.length; i++) {
      visit++;        // id[i]访问一次
      if (id[i] == pID) {
        id[i] = qID;        // 将与p相同的连通分量, 都改为q的连通分量
        visit++;      // id[i]重新访问一次
      }
    }
    --count;
  }

  public long getVisit() {
    // 返回当前visit的值
    return visit;
  }

  public void setDraw(int max_x, int max_y) {
    StdDraw.setXscale(0, max_x);
    StdDraw.setYscale(-max_y, max_y);
    StdDraw.setPenRadius(.003);
  }
  
  public void drawCost(int i, long cost) {
    // 画出(i, cost)这个点, 灰色
    StdDraw.setPenColor(StdDraw.GRAY);
    StdDraw.point(i, cost);
  }

  public void drawTotal(int i, long total) {
    // 画出(i, total/i) 红色
    StdDraw.setPenColor(StdDraw.RED);
    StdDraw.point(i, (double)total / i);
  }
  
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    int N = read.nextInt();
    QuickFindUF uf = new QuickFindUF(N);

    int i = 1;
    long total = 0;
    long cost = 0;
    uf.setDraw(N, 2000);

    while (read.hasNext()) {
      int p = read.nextInt();
      int q = read.nextInt();
      if (uf.connected(q, p)) continue;

      cost = uf.getVisit();                // 调用union之前的计数
// System.out.println(cost);
      uf.union(p, q);                      // 注意与union(p, q)的区别
      total  = uf.getVisit();              // 总的计数
      cost = total - cost;                 // union调用后改变的计数
      uf.drawCost(i, cost);
      uf.drawTotal(i, total);

      System.out.println(p + " " + q);
      i++;
    }
    System.out.println(uf.count() + " components");
  }
}
```


画完图后的小总结:

```
连通性问题的三个算法，画图测试了一下, 一共有900组左右数据, 灰点代表当前这组数据, 在这个算法下, 对数组要访问（读和写）的次数；红点代表，从第一组数据当当前这组数据，一共加起来对数组的访问的次数/之前一共有多少组数据（也就是拼摊的访问次数）。

三张图分别代表三个算法，第一个图的y值是-2000, 2000; 2, 3图的y值都是-100, 100，也就是每组数据运算, 控制在100次以内。。
```


##### 1.5.17 随机连接

连接在图中, 叫做 边(edge);
可以通过多做几次实验, 观察一下数据。
```java
public class ErdosRenyi {

  public static int count(int n) {
    UF uf = new UF(n);
    int edge = 0;
    while (uf.count() > 1) {
      int p = StdRandom.uniform(n);
      int q = StdRandom.uniform(n);
      uf.union(p, q);
      edge++;
    }
    return edge;
  }
  
  public static void main(String[] args) {
    int N = Integer.parseInt(args[0]);
    int trails = Integer.parseInt(args[1]);
    int[] edges = new int[trails];
    for (int i = 0; i < trails; i++) {    // N个数实验trails次
      edges[i] = count(N);                // 得到每次的连接数(edge)
    }
    System.out.println("1 / 2 n ln n  = " + 0.5 * N * Math.log(N));
    System.out.println("mean          = " + StdStats.mean(edges));
    System.out.println("stddev        = " + StdStats.stddev(edges));
  }
}
```

测试数据:
```sh
$ java ErdosRenyi 100 50
1 / 2 n ln n  = 230.25850929940458
mean          = 257.88
stddev        = 52.002605900167765

$ java ErdosRenyi 1000 50
1 / 2 n ln n  = 3453.8776394910683
mean          = 3743.16
stddev        = 609.597537356427

$ java ErdosRenyi 10 50
1 / 2 n ln n  = 11.51292546497023
mean          = 16.1
stddev        = 4.2051960084385795
$ java ErdosRenyi 10 100
1 / 2 n ln n  = 11.51292546497023
mean          = 15.69
stddev        = 6.112902400462137
$ java ErdosRenyi 10 100
1 / 2 n ln n  = 11.51292546497023
mean          = 16.82
stddev        = 5.85408430331363
java ErdosRenyi 100 100
1 / 2 n ln n  = 230.25850929940458
mean          = 265.5
stddev        = 61.21290878581251
java ErdosRenyi 100 10
1 / 2 n ln n  = 230.25850929940458
mean          = 271.4
stddev        = 66.31272711763388

$ java ErdosRenyi 1000 100
1 / 2 n ln n  = 3453.8776394910683
mean          = 3745.5
stddev        = 549.594552117442
$ java ErdosRenyi 100000 100
1 / 2 n ln n  = 575646.2732485115
mean          = 607449.34
stddev        = 66257.3861064637
````

附上一份UF实现
```java
import java.util.Scanner;
public class UF {
  private int[] id;
  private int[] size;
  private int count;      // 连通量

  public UF(int N) {
    id = new int[N];
    size = new int[N];
    for (int i = 0; i < N; i++) {
      id[i] = i;
      size[i] = 1;
    }
    count = N;
  }

  public int count() { return count;}
  public boolean connected(int p, int q) { return find(p) == find(q); }

  public int find(int p) {
    int root = id[p];
    while (root != id[root]) {root = id[root];}       // 只是为了找到根结点
    while (p != root) {                 // 当初始结点到根结点过程中, 各个结点
      int newP = id[p];                 // 保存p的原来的下个结点
      id[p] = root;                     // 更新p的下个结点
      size[p] = 1;                      // 更新完成后的结点深度为1
      p = newP;                         // p更新为原来的下个结点
    }
    return root;
  }

  public void union(int p, int q) {
    if (connected(p, q)) return;   // 在判断connected时, 已将子结点与根连接直接相连
    int pRoot = find(p);           // 查找次数
    int qRoot = find(q);

    // 将q的根触点, 作为p的根触点
    if (size[pRoot] < size[qRoot]) {size[qRoot] += size[pRoot]; id[pRoot] = qRoot;}
    else                           {size[pRoot] += size[qRoot]; id[qRoot] = pRoot;}
    count--;
  }

  public String toString() {
    String s = new String();
    for (int i = 0; i < id.length; i++) {
      s += id[i]; s += " ";
    }
    return s;
  }
}
```


#### Web Exercises

##### 2 判断一个数组是否满足加权路径其中某个时刻的状态
```java
a 0 1 2 3 4 5 6 7 8 9
b 7 3 8 3 4 5 6 8 8 1
c 6 3 8 0 4 5 6 9 8 1
d 0 0 0 0 0 0 0 0 0 0
e 9 6 2 6 1 4 5 8 8 9
f 9 8 7 6 5 4 3 2 1 0
```

```sh
A可以, 初始值
B不行, 结点超过2
C不行, 结点超过2
D可以, 0为根结点, 其他均为它的子结点, 是路径压缩的最理想情况
E不行, 1->6->5->4->1...
F不行, 0->9->0
```

##### 3, 路径压缩算法使用递归
```java
public int find(int p) {
  while (p != id[p]) {
    id[p] = find(id[p]);
  }
}
```


##### 4, Path Halving

```sh
0 -> 1 -> 2 -> 3 -> 4 -> 5

halving:
0 -> 2 -> 4 -> 5
    /    /
   1    3

splitting:
0 -> 2 -> 4 -> 5
1 -> 3 -> 5
```

```java
while (p != parent[p]) {
  parent[p] = parent[parent[p]];
  p = parent[p];
}
```

##### 5, Path Splitting
```java
while (p != parent[p]) {
  int next = parent[p];
  parent[p] = parent[next];
  p = next;
}
```

##### 6. Random quick union.

将quick-union改写, N个元素中, 生成[0,N-1]不规则的随机数, 当归并两个根结点时, 总是将label小的归并到label大的根结点上

```java
{
  public RandomQuickUnionUF(int N) {
    parent = new int[N];
    label = new int[N];
    for (int i = 0; i < N; i++) {
      parent[i] = i;
      label[i] = (int)Math.random() * N;
    }
    count = N;
  }


  public void union(int p, int q) {
    int pRoot = find(p);
    int qRoot = find(q);
    if (pRoot == qRoot) return;

    if (label[pRoot] < label[qRoot]) { parent[pRoot] = qRoot; }
    else                             { parent[qRoot] = pRoot; }
    count--;
  }
}
```



#### Programming Assignment

percolation
> * Percolation.java 
> * PercolationStats.java

渗滤模型的仿真Moneto Carlo simulation(应用)需要用到quick-union(算法)

检查最下一行的任意一位, 是否和最上一行的任何一个位连通
若上下每个位置, 一一对应检查的话, 就是一个N^2的暴力求解 
在最上和最下设置一个虚拟位置, virtual top site和 virtual bottom site, 检查是否渗滤, 只检查虚拟顶位和虚拟底位是否连通, 

```java
// 不改变full的版本
public    void open(int row, int col) {    // open site (row, col) if it is not open already
    if (isOpen(row, col)) return;
    int p = row * n + col;
    open[p] = true;
    openSites++;
    // union, 0, 1, 2, 3 or 4次
    if (row == 0) {         // 最上一行
      if (isOpen(row+1, col)) { uf.union(p, p+n);}
    } else if (row == n-1) {
      if (isOpen(row-1, col)) { uf.union(p, p-n);}
    } else {
      if (isOpen(row+1, col)) { uf.union(p, p+n);}
      if (isOpen(row-1, col)) { uf.union(p, p-n);}
    }

    if (col == 0) {
      if (isOpen(row, col+1)) { uf.union(p, p+1);}
    } else if (col == n-1) {
      if (isOpen(row, col-1)) { uf.union(p, p-1);}
    } else {
      if (isOpen(row, col+1)) { uf.union(p, p+1);}
      if (isOpen(row, col-1)) { uf.union(p, p-1);}
    }
    // full??
  }
  
```


搞不清楚, empty()和full()之间存在多少差距?

full()如果代表有水的话, 那它与top是相连的

这道题里, 我个人认为最难的就是n和n-1之间的关系, 我平时从0开始索引, 它突然从1开始索引, 我该怎么转换呢?

我将所有变量改成从0开始索引之后, 放弃, 这道题让我很烦. 为什么不能好好的从0开始索引呢.

```java

    if (col == 0) {            // 最左列
      if (isOpen(row, col+1)) {
        uf.union(p, p+1);
        // if (isFull(row, col+1)) {full[p] = true;}         // 右格有水, 这个格子也有
      }
    } else if (col == n-1) {
      if (isOpen(row, col-1)) {
        uf.union(p, p-1);
        // if (isFull(row, col-1)) {full[p] = true;}         // 左格有水, 这个格子也有
      }
    } else {
      if (isOpen(row, col+1)) {
        uf.union(p, p+1);
        // if (isFull(row, col+1)) {full[p] = true;}         // 右格有水, 这个格子也有
      }
      if (isOpen(row, col-1)) {
        uf.union(p, p-1);
        // if (isFull(row, col-1)) {full[p] = true;}         // 左格有水, 这个格子也有
      }
    }
	
```
