// Copyright [2018] <mituh>
// Rational.java
// 为有理数实现一个不可变数据类型Rational, 支持加减乘除操作

public class Rational {
  private final int up;       // 分子
  private final int down;     // 分母

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
    Rational rational = new Rational(this);   // 构造一个新的与this相同的对象
    int same_down = r.down * b.down;
    rational.up = (same_down / r.down) * rational.up + (same_down / b.down) * b.up;
    rational.down = same_down;

    rational = remove_divisor(rational);      // 用欧几里得算法消除最大公约数(注意不变性)
    return rational;
  }

  // 该数与有理数b相减
  public Rational minus(Rational b) {
    Rational rational = new Rational(this);   // 构造一个新的与this相同的对象

    return rational;
  }

  // 该数与有理数b相乘
  public Rational minus(Rational b) {
    Rational rational = new Rational(this);   // 构造一个新的与this相同的对象

    return rational;
  }

  // 该数与有理数b相除
  public Rational minus(Rational b) {
    Rational rational = new Rational(this);   // 构造一个新的与this相同的对象

    return rational;
  }

  // 该数与that相等吗
  public boolean equals(Object that) {
    if (this == that) return true;
    if (x == null) return false;
    if (this.getClass() != that.getClass()) return false;
    Rational x = (Rational) that;             // 经过上面的判断, 可以转化
    if (this.up != x.up)                    return false;
    if (this.down != x.down)                return false;
    return true;
  }

  public String toString() {
    return String.format("%5d / %5d", this.up, this.down);
  }
  
  // 消除最大公约数
  public Rational remove_divisor(Rational orig_rational) {
    Rational rational = new Rational(orig_rational);

    return rational;
  }
  
  
}
