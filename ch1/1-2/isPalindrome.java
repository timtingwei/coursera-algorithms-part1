// Copyright [2018] <mituh>
// isPalindrome.java
// 判断字符串是否回文

public class isPalindrome {
  public static boolean judge(String s) {
    int N = s.length();
    for (int i = 0; i < N/2; i++) {
      if (s.charAt(i) != s.charAt(N-1-i))
        return false;
    }
    return true;
  }
  
  public static void main(String[] args) {
    String s = args[0];
    System.out.println("judge(s) = " + isPalindrome.judge(s));
  }
}
