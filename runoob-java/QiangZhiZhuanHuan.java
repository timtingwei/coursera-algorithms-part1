import java.io.*;

public class QiangZhiZhuanHuan {
  public static void main(String []args) {
    int i = 55;
    byte b = (byte)i;  // 溢出
    float f = (float)i;
    
    System.out.println("f=" + f);
  }
}
