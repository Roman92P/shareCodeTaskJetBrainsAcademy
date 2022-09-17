import java.time.LocalTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // write your code here
        String s = scanner.nextLine();
        String s1 = scanner.nextLine();

        String[] split = s.split(":");
        String[] split1 = s1.split(":");

        LocalTime of = LocalTime.of(Integer.parseInt(split[0]), Integer.parseInt(split[1]), Integer.parseInt(split[2]));
        LocalTime of1 = LocalTime.of(Integer.parseInt(split1[0]), Integer.parseInt(split1[1]), Integer.parseInt(split1[2]));

        System.out.println(secondDefference(of, of1));


    }
        // put your code here
       // System.out.println(secondDefference( LocalTime.of(12,23,30), LocalTime.of(12,23,35)));


    private static int secondDefference(LocalTime of, LocalTime of1) {
        return Math.abs(of.toSecondOfDay() - of1.toSecondOfDay());
    }
}