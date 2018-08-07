
public class TestStringAgain {
  public static void main(String[] args) {
    String a = "now is ";
    String b = "the time ";
    String c = "to";
    String d = "now";
    char[] c_arr = {'a', 'c', 'e'};
    System.out.println("a.length() = " + a.length());        // 7
    System.out.println("c_arr.length = " + c_arr.length);    // 7
    System.out.println("a.charAt(2) = " + a.charAt(2));      // w
    // System.out.println("a[2] = " + a[2]);  // error: array required, but String found
    System.out.println("a.indexOf(is) = " + a.indexOf("is"));
    System.out.println("a.indexOf(is, 5) = " + a.indexOf("is", 5));   // -1
    System.out.println("a.concat(b) = " + a.concat(b));      // "now is the time "
    System.out.println("a = " + a);                          // "now is "
    System.out.println("a.substring(0, 3) = " + a.substring(0, 3));
    System.out.println("a.split()[0] = " + a.split(" ")[0]
                       + ", a.split()[1] = " + a.split(" ")[1] +
                       ", a.split().length = " + a.split(" ").length);
    // a.split()[0] = now, a.split()[1] = is, a.split().length = 2
    System.out.println("b.compareTo(c) = " + b.compareTo(c));
    // b.compareTo(c) = -7
    System.out.println("a.split()[0].equals(d) = " + a.split(" ")[0].equals(d));
    // a.split()[0].equals(d) = true
    System.out.println("a.hashCode() = " + a.hashCode());
    // a.hashCode() = 2129949676
  }
}
