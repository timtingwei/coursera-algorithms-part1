// Copyright [2018] <mituh>

// Dependencies: Date.java

public class Transaction {
  private final String name;
  private final Date time;
  private final double amount;
  public Transaction(String s, Date t, double val) {
    name = s; time = t; amount = val;
  }
  public String who()  { return name; }
  public Date   when() { return time; }
  public double much() { return amount; }
  public String toString() {
    return name + " " + time + " " + amount;     // 内部都有toString机制?
  }

  public static void main(String[] args) {
    String name = args[0];      // mituh
    String t = args[1];         // 1996/12/03
    double amount = Double.parseDouble(args[2]);    // 11.99
    Date time = new Date(t);
    Transaction trans = new Transaction(name, time, amount);
    System.out.println(trans);
  }
}
