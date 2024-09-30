package algo;

import java.util.Arrays;

public class Practice1 {
    public static void main(String[] args) {
        int n=6;
        int[] times = {7,10};
        System.out.println(solution(n,times));
    }

    private static int solution(int n, int[] times) {
        Arrays.sort(times);
        int right = times[times.length-1]*n;
        int left = 0;

        while(left <= right) {
            int mid = (left + right) / 2;
            int count = 0;
            count += mid / times[0];
            count += mid / times[1];


            if (count < n) {
                left = mid + 1;
            } else {
                right = mid-1;
            }
        }
        return left;
    }
}
