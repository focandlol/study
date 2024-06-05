package IntervalSum;

import java.util.Arrays;
import java.util.Scanner;

public class BaekJoon12981 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int count = 0;
        int[] arr = new int[3];
        for (int i = 0; i < 3; i++) {
            String[] split = sc.next().split(" ");
            arr[i] = Integer.parseInt(split[0]);
        }

        Arrays.sort(arr);
        count += arr[0];
        int r = 0;
        int g = arr[1] - arr[0];
        int b = arr[2] - arr[0];

        int i = g / 3;
        int i1 = b / 3;
        count += i;
        count += i1;

        g = g % 3;
        b = b % 3;

        if (g == 2) {
            count+=1;
            g = 0;
        }
        if (b == 2) {
            count+=1;
            b = 0;
        }
        if (g + b > 0) {
            count+=1;
        }
        System.out.println(count);
    }
}
