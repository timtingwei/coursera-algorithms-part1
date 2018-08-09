// Copyright [2018] <mituh>
// Parentheses.java
// 使用栈判定其中的括号对是否完整匹配
// case:
// [()]{}{[()()]()}
// [(])
// ()[
// )

import java.util.Scanner;
public class Parentheses {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    String s = read.next();
    Stack<Character> stk_ch = new Stack<Character>();
    int ok = 1;
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == '(' || s.charAt(i) == '[' || s.charAt(i) == '{') {
        stk_ch.push(s.charAt(i));
      }
      else if (s.charAt(i) == ')' || s.charAt(i) == ']' || s.charAt(i) == '}') {
        if (stk_ch.isEmpty()) {       // 右括号要匹配左括号时, 栈已空, 无法匹配
          ok = 0; break;
        }
        if      (stk_ch.top() == '(' && s.charAt(i) == ')') { stk_ch.pop(); }
        else if (stk_ch.top() == '[' && s.charAt(i) == ']') { stk_ch.pop(); }
        else if (stk_ch.top() == '{' && s.charAt(i) == '}') { stk_ch.pop(); }
        else { ok = 0; break; }   // 右括号匹配失败
      }
    }
    if (stk_ch.isEmpty() && ok == 1) {  // 打印的端口变窄
      System.out.println("true");
    } else {
      System.out.println("false");      // 还没有匹配完全 ()[
    }
  }
}

