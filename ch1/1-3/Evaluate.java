// Copyright [2018] <mituh>
// Evaluate.java
// 用两个栈(一个用于保存运算符, 一个用于保存操作数), 完成算术表达式的求值, E.W.Dijkstra
// (1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )

/*
 * 遇到运算符加入运算符栈
 * 遇到数字加入操作数栈
 * 遇到左括号 "("或 " ", 忽略
 * 右括号栈顶两个操作数出栈, 栈顶运算符出栈
 * 计算该表达式后, 结果入操作数栈
 */

/* 改进:  但改进后, 对输入的输入格式有所限制
 * 并行边读边运算
 * 简化出栈后运算部分代码, 不用创造无关的对象
 * 添加sqrt功能
 */
// ( ( sqrt ( 25 )  -  3 ) * 4 )

import java.util.Scanner;
import java.util.Stack;
public class Evaluate {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    Stack<String> stk_s = new Stack<String>();
    Stack<Double> stk_d = new Stack<Double>();
    while (read.hasNext()) {
      String s = read.next();                  // 读取字符串, 可省略对" "判断的代码
      if      (s.equals("(")) continue;
      // == 判断引用所指向的对象是否为同一个, equals判断对象的等价性, 现在成更相等
      else if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")
               || s.equals("sqrt")) {
        stk_s.push(s);

      } else if (s.equals(")")) {
        double v = stk_d.pop();    // why? 因为像sqrt这种只需要取一个数, 按需求pop比较好
        String op = stk_s.pop();
        if (op.equals("+")) v += stk_d.pop();
        else if (op.equals("-")) v = stk_d.pop() - v;
        else if (op.equals("*")) v = stk_d.pop() * v;
        else if (op.equals("/")) v = stk_d.pop() / v;
        else if (op.equals("sqrt")) v = Math.sqrt(v);
        stk_d.push(v);
      } else {   // 既不是括号也不是字符, 按照double压入
        stk_d.push(Double.parseDouble(s));
      }
    }
    read.close();
    System.out.println(stk_d.peek());
  }
}


/* 根据算法自己给出的实现
import java.util.Scanner;
import java.util.Stack;
public class Evaluate {
  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    String s = read.nextLine();       // 读入一整行字符串
    read.close();
    Stack<Character> stk_ch = new Stack<Character>();            // Java, Char需要写全拼
    Stack<Double> stk_d = new Stack<Double>();
    for (int i = 0; i < s.length(); i++) {
      if (s.charAt(i) == ' ' || s.charAt(i) == '(') continue;    // Java中用s.charAt(i), 不是s[i]
      // if (s.charAt(i).isDigit()) {}                           // 回答下面问题
      if ( 0 <= s.charAt(i)- '0' && s.charAt(i) - '0' <= 9) {    // Java中的isdigit()是什么
        stk_d.push((double)s.charAt(i) - '0');                   // 转化成int->double入栈
      } else if (s.charAt(i) == '+' || s.charAt(i) == '-'
                 || s.charAt(i) == '*' || s.charAt(i) == '/') {
        stk_ch.push(s.charAt(i));
      } else if (s.charAt(i) == ')') {
        double a = stk_d.pop(), b = stk_d.pop();    // 栈顶两个运算符出栈
        char ch = stk_ch.pop();
        double ans = 0.0;
        switch (ch) {
          case '+':
            ans = a+b;
            break;
          case '-':
            ans = a-b;
            break;
          case '*':
            ans = a*b;
            break;
          case '/':
            ans = a/b;
            break;
        }
        stk_d.push(ans);
      }
    }
    System.out.println(s + " = " + String.format("%.2f", stk_d.peek()));
  }
}
*/
