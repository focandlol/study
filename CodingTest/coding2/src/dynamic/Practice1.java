package dynamic;

import java.util.Arrays;

public class Practice1 {
    public static void main(String[] args) {
        //System.out.println(solution(2));
       // System.out.println(solution(4));
        //System.out.println(solution(9));
        System.out.println(solution(10));
    }

    private static int solution(int n) {
        int[] arr = new int[n+1];
        arr[1] = 0;

        for(int i=2; i<=n; i++){
            if(i % 6 == 0){
                arr[i] = Math.min(arr[i-1] + 1,1+ arr[i / 3]);
            }
            else if(i % 3 == 0){
                arr[i] = Math.min(arr[i-1] + 1,1 + arr[i / 3]);
            }else if(i % 2 == 0){
                arr[i] = Math.min(arr[i-1] + 1, 1 + arr[i / 2]);
            }else{
                arr[i] = arr[i-1] + 1;
            }
        }
        System.out.println(Arrays.toString(arr));
        return arr[n];
    }
}
