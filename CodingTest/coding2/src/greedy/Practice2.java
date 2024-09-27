package greedy;

import java.util.Arrays;

public class Practice2 {
    public static void main(String[] args) {
        int[] prices = {5,1,6,4,3,5};
        System.out.println(solution(prices));

        prices = new int[]{1,2,3,4,5};
        System.out.println(solution(prices));

        prices = new int[]{5,4,3,2,1};
        System.out.println(solution(prices));
    }

    private static int solution(int[] prices) {
//        int a = -1;
//        int sum = 0;
//        for(int i=0; i<prices.length-1; i++) {
//            if(a == -1 && prices[i] < prices[i+1]) {
//                a = prices[i];
//            }
//            if(a != -1 && a < prices[i+1]) {
//                sum+=prices[i+1]-a;
//                a=-1;
//            }
//        }

        int sum = 0;
        for(int i=1; i<prices.length; i++) {
            if(prices[i] > prices[i-1]) {
                sum += prices[i] - prices[i-1];
            }
        }
        return sum;
    }
}
