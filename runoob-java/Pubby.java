public class Pubby {
  public Pubby(String name) {
    System.out.println("the dog's name is: " + name);
  }

  // public static int age;
  int age;
  public void setAge(int a) {
    age = a;
  }

  public int getAge() {
    System.out.println("the pubby's age is:" + age);
    return age;
  }

  public static void main(String []args) {
    // 创建一个Pubby对象
    Pubby myPubby = new Pubby("tommy");
    // 设置age
    myPubby.setAge(2);
    // 调用方法获取age
    int mya = myPubby.getAge();
    // 访问age变量
    System.out.println("age is:" + myPubby.age);
  }
}
