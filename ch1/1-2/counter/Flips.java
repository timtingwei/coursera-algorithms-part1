// Copyright [2018] <mituh>
// Flips.java

public class Flips {
  public static void main(String[] args) {
    int T = Integer.parseInt(args[0]);
    Counter heads = new Counter("heads");
    Counter tails = new Counter("tails");
    for (int i = 0; i < T; i++) {
      if (StdRandom.bernoulli(0.5)) {
        heads.increament();
      } else {
        tails.increament();
      }
    }
    System.out.println(heads);
    System.out.println(tails);
    int delta = heads.tally() - tails.tally();
    System.out.println("delta: " + delta);
  }
}
