// Copyright [2018] <mituh>

public class Date {
  private final int year;
  private final int month;
  private final int day;

  public Date(String s) {
    String[] strings = s.split("/");
    year = Integer.parseInt(strings[0]);
    month = Integer.parseInt(strings[1]);
    day = Integer.parseInt(strings[2]);
  }
  public int year() { return year; }
  public int month() { return month; }
  public int day() { return day; }

  public String toString() {
    return year + "." + month + "." + day;
  }
}
