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

