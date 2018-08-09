// Copyright [2018] <mituh>
// EvaluatePostfix.java
// 后缀表达式求值
import java.util.Scanner;
public class EvaluatePostfix {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Stack<Double> stk_d = new Stack<Double>();
    while (read.hasNext()) {
      String s = read.next();
      double v = 0.0;
      // if (s.equals(" ") || s.equals("(")) continue;     // 后缀表达式无括号, 且这种读取方式无空串
      if (s.equals("+"))      { v = stk_d.pop() + stk_d.pop(); stk_d.push(v);}
      else if (s.equals("-")) { v = -(stk_d.pop() - stk_d.pop()); stk_d.push(v);}
      else if (s.equals("*")) { v = stk_d.pop() * stk_d.pop(); stk_d.push(v);}     // 小心溢出
      else if (s.equals("/")) { v = 1 / (stk_d.pop() / stk_d.pop()); stk_d.push(v); }
      else stk_d.push(Double.parseDouble(s));
    }
    System.out.println(stk_d.peek());
  }
}

/*
$ java InfixToPostfix | java EvaluatePostfix
( (3 - 4) * 5 )
-5.0
*/
