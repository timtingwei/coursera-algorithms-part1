// Copyright [2018] <mituh>
// Transaction.java
// Dependencies: Date.java

public class Transaction  implements Comparable<Transaction> {
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
}
