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


