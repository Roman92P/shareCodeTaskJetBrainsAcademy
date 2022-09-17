// You can experiment here, it won’t be checked

import java.time.LocalTime;

public class Task {
  public static void main(String[] args) {
    // put your code here
    System.out.println(secondDefference( LocalTime.of(12,23,30), LocalTime.of(12,23,35)));
  }

  private static int secondDefference(LocalTime of, LocalTime of1) {
    return of.getSecond() - of1.getSecond();
  }
}
