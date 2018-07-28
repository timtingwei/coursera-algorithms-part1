import java.io.*;

public class EmployeeTest {
  public static void main(String []args) {
    // 构造器创建两个对象
    Employee empOne = new Employee("RUNBOO1");
    Employee empTwo = new Employee("RUNBOO2");
    // 调用两个对象的成员方法
    empOne.empAge(26);
    empOne.empDesignation("advanced programmer");
    empOne.empSalary(1000.0);
    empOne.printEmployee();

    empTwo.empAge(21);
    empTwo.empDesignation("basic programmer");
    empTwo.empSalary(500.0);
    empTwo.printEmployee();
  }
}
