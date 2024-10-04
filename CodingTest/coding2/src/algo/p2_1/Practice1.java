package algo.p2_1;

import java.util.Arrays;

public class Practice1 {
    public static void main(String[] args) {
        int[] rocks = {11,2,14,21,17};
        int goal = 25;
        int n= 2;
        System.out.println(solution(rocks,goal,n));
    }

    private static int solution(int[] rocks, int goal, int n) {
        int left = 0;
        int right = goal;
        Arrays.sort(rocks);
        int result = Integer.MIN_VALUE;

        while (left <= right) {
            int cnt = 0;
            int prev = 0;
            int mid = left + (right - left) / 2;
            for(int i=0; i<rocks.length; i++) {
                if(rocks[i] - prev < mid){
                    cnt++;
                }else{
                    prev = rocks[i];
                }
                if(cnt > n){
                    break;
                }
            }
            if(goal - prev < mid && cnt <= 2){
                cnt++;
            }

            if(cnt > n){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
            if(cnt == n){
                result = Math.max(result, mid);
            }
        }
        return left - 1;
    }
}
