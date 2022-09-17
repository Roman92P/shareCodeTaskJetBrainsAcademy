import java.util.*;
import java.time.*;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);
        String d = scanner.nextLine();
        String[] arr = d.split(" ");
        LocalDate date = LocalDate.of(Integer.parseInt(arr[0]),Integer.parseInt(arr[1]),Integer.parseInt(arr[2]));
        LocalDate date1 = date.plusMonths(1).minusDays(date.getDayOfMonth());
        LocalDate localDate = date1.minusDays(date.getDayOfMonth()-1);
        System.out.print(localDate);
    }
}
