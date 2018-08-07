// Copyright [2018] <mituh>
// Transaction.java
// Dependencies: Date.java

public class Transaction {
  private final String name;
  private final Date time;
  private final double amount;
  public Transaction(String s, Date t, double val) {
    // 添加异常的机制
    if (Double.isNaN(val) || Double.isInfinite(val))
        throw new IllegalArgumentException("Amount can't be NaN or Infinite");
    name = s; time = t; amount = val;
  }
  public Transaction(String transaction) {
    // 解析字符串的构造函数
    String[] a = transaction.split("\\s+");
    name = a[0];
    time = new Date(a[1]);     // Date中已经写好分解string的构造函数, 但要构造这个Date对象
    amount = Double.parseDouble(a[2]);
    if (Double.isNaN(amount) || Double.isInfinite(amount))
      throw new IllegalArgumentException("Amount can't be NaN or Infinite");
  }
  public String who()  { return name; }
  public Date   when() { return time; }
  public double much() { return amount; }

  public String toString() {
    return String.format("%-10s %10s %8.2f", name, time, amount);    // 内部都有toString机制?
  }

  // 通过amount比较两笔交易
  public int compareTo(Transaction that) {
    return Double.compare(this.amount, that.amount);
  }

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

  public static void main(String[] args) {
    String name = args[0];      // mituh
    String t = args[1];         // 1996/12/03
    double amount = Double.parseDouble(args[2]);    // 11.99
    Date time = new Date(t);
    Transaction trans = new Transaction(name, time, amount);
    Transaction[] more_trans = new Transaction[5];
    more_trans[0] = new Transaction(name, time, amount);
    more_trans[1] = new Transaction("Turing   6/17/1990   644.08");
    more_trans[2] = new Transaction("Tarjan   3/26/2002 4121.85");
    more_trans[3] = new Transaction("Knuth    6/14/1999  288.34");
    more_trans[4] = new Transaction("Dijkstra 8/22/2007 2678.40");

    
    System.out.println("Unsorted:");
    for (int i = 0; i < more_trans.length; i++) {
      System.out.println(more_trans[i]);
    }

    System.out.println(more_trans[3].equals(more_trans[3]));
    System.out.println(more_trans[3].equals(more_trans[4]));
  }
}

// mituh   1996/12/03   11.99

/*
Unsorted:
mituh       1996.12.3    11.99
Turing      6.17.1990   644.08
Tarjan      3.26.2002  4121.85
Knuth       6.14.1999   288.34
Dijkstra    8.22.2007  2678.40
true
false
*/
