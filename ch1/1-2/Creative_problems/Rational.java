// Copyright [2018] <mituh>
// Rational.java
// 为有理数实现一个不可变数据类型Rational, 支持加减乘除操作

public class Rational {
  private int up;       // 分子, Rational满足不变性, 分子分母不需要final
  private int down;     // 分母

  public Rational(int up, int down) {
    // 分母为0时, 抛出异常
    this.up = up;
    this.down = down;
  }

  public Rational(Rational r) {      // 通过另外一个有理数, 构造新有理数
    // 分母为0时, 抛出异常
    this.up = r.up;
    this.down = r.down;
  }

  // 该数与有理数b相加
  public Rational plus(Rational b) {
    Rational rational = new Rational(this);     // 构造一个新的与this相同的对象
    if (rational.down == b.down) {              // 如果分母相等
      rational.up += b.up;
    } else {
      int same_down = rational.down * b.down;   // 如果分母不相等
      rational.up = b.down * rational.up + rational.down * b.up;
      rational.down = same_down;
      rational = remove_divisor(rational);      // 用欧几里得算法消除最大公约数(注意不变性)
    }
    
    return rational;
  }

  // 该数与有理数b相减
  public Rational minus(Rational b) {
    Rational rational = new Rational(this);     // 构造一个新的与this相同的对象
    if (rational.down == b.down) {
      rational.up -= b.up;
    } else {
      int same_down = rational.down * b.down;   // 如果分母不相等
      rational.up = b.down * rational.up - rational.down * b.up;
      rational.down = same_down;
      rational = remove_divisor(rational);      // 用欧几里得算法消除最大公约数(注意不变性)
    }

    return rational;
  }

  // 该数与有理数b相乘
  public Rational times(Rational b) {
    Rational rational = new Rational(this);   // 构造一个新的与this相同的对象
    rational.up   *= b.up;
    rational.down *= b.down;
    rational = remove_divisor(rational);
    
    return rational;
  }

  // 该数与有理数b相除
  public Rational divides(Rational b) {
    Rational rational = new Rational(this);   // 构造一个新的与this相同的对象
    if (b.up == 0)
      throw new RuntimeException("divides:Denominator is zero");
    rational.up *= b.down;
    rational.down *= b.up;
    rational = remove_divisor(rational);

    return rational;
  }

  // 该数与that相等吗
  public boolean equals(Object that) {
    if (this == that) return true;
    if (that == null) return false;
    if (this.getClass() != that.getClass()) return false;
    Rational x = (Rational) that;           // 经过上面的判断, 可以转化
    if (this.up != x.up)                    return false;
    if (this.down != x.down)                return false;
    return true;
  }

  public String toString() {
    return String.format("%d / %d", this.up, this.down);
  }
  
  // 消除最大公约数, 辗转相除算法
  public Rational remove_divisor(Rational orig_rational) {
    if (orig_rational.down == 0)                      // 分母为0抛出异常, 分子为0无需简化
      throw new RuntimeException("Error Denominator is zero.");
    Rational rational = new Rational(orig_rational);
    if (rational.up == 0) return rational;            // 分子为零不需要化简
    int a = rational.up, b = rational.down;
    while (b != 0) {         // java中不用!b
      int r = a % b;
      a = b; b = r;
    }  // end while          // a为最大公约数
    if (a != 1) {            // 分子分母同除以最大公约数, 且a不会是0
      rational.up /= a;
      rational.down /= a;
    }

    return rational;
  }

  public static void main(String[] args) {
    Rational rational1 = new Rational(3, 5);
    Rational rational2 = new Rational(-3, 5);
    System.out.println("plus: " + rational1 + " + " + rational2 + " = "
                       + rational1.plus(rational2));
    System.out.println("minus: " + rational1 + " + " + rational2 + " = "
                       + rational1.minus(rational2));
    System.out.println("times: " + rational1 + " + " + rational2 + " = "
                       + rational1.times(rational2));
    System.out.println("divides: " + rational1 + " + " + rational2 + " = "
                       + rational1.divides(rational2));
  }
  
}
