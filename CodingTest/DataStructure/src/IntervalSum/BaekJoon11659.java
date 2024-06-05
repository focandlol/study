package IntervalSum;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BaekJoon11659 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int n = sc.nextInt();
        int m = sc.nextInt();

        int[] sum = new int[n + 1];
        sum[0] = 0;
        for(int i=1; i<n + 1; i++){
                sum[i] = sum[i-1] + sc.nextInt();
        }

        for(int b=0; b<m; b++){
            int start = sc.nextInt();
            int end = sc.nextInt();
                sb.append(sum[end] - sum[start - 1]).append("\n");
        }
        System.out.println(sb);
        sc.close();
    }
}
