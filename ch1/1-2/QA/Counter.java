// Copyright [2018] <mituh>
// Counter.java

public class Counter {
  private final String name;
  private int count;
  public Counter(String s) {name = s;}
  public void increament() {count++;}
  public int tally() {return count;}
  public String toString() {return count + " " + name;}
}
