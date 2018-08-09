// Copyright [2018] <mituh>
// InfixToPostfix.java

import java.util.Scanner;
public class InfixToPostfix {
  public static void convert(String s) {
    Queue<Character> q = new Queue<Character> ();    // 队列存放表达式
    Stack<Character> ops = new Stack<Character> ();    // 栈存放操作符
    for (int i = 0; i < s.length(); i++) {
      Character ch = s.charAt(i);
      if (ch == '(' || ch == ' ') continue;
      if ('0' <= ch && ch <= '9') { q.enqueue(ch);}
      else if (ch == '+' || ch == '-' || ch == '*' || ch == '/') { ops.push(ch);}
      else if (ch == ')') {     // 遇到右括号
        q.enqueue(ops.pop());
      }      
    }
    while (!q.isEmpty()) {
      System.out.print(q.dequeue() + " ");
    }
    System.out.println();
  }

  public static void main(String[] args) {
    Scanner read = new Scanner(System.in);
    String s = read.nextLine();
    read.close();
    convert(s);
  }
    
}

/*
$ java InfixToPostfix
( ( 1 + 2 ) * ( ( 3 - 4) * ( 5 - 6 ) ) )
1 2 + 3 4 - 5 6 - * *

$ java InfixToPostfix
( (3 - 4) * 5 )
3 4 - 5 *

$ java InfixToPostfix
( 3 - ( 4 * 5 ) )
3 4 5 * -
*/
