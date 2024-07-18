package complexity;

import java.util.Scanner;

public class BaekJoon24265 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        long sum = 0;
        for(int i=1; i<=n-1; i++){
            sum += i;
        }

        System.out.println(sum);
        System.out.println("2");
    }
}
