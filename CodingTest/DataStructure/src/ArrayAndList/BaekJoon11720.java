package ArrayAndList;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaekJoon11720 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int sum = 0;
        String next = sc.next();

        String[] b = sc.next().split("");

        for (String s : b) {
            sum += Integer.parseInt(s);
        }

        System.out.println(sum);




    }
}
