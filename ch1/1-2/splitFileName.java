// Copyright [2018] <mituh>
// splitFileName.java
// 从命令行参数中提取文件名和扩展

public class splitFileName {
  
  public static void main(String[] args) {
    String s = args[0];
    int dot = s.indexOf(".");
    String base = s.substring(0, dot);
    String extension = s.substring(dot + 1, s.length());
    System.out.printf("base = %s\nextension = %s\n", base, extension);
  }
}

/*
$ java splitFileName timtingwei.github
base = timtingwei
extension = github
*/
