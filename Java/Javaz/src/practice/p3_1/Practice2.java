package practice.p3_1;

import java.util.Arrays;

public class Practice2 {
    public static void main(String[] args) {
        solution(new int[]{4,3,2,7,8,2,3,1});
        solution(new int[]{1,1,2});
        solution(new int[]{1,3,1,3,5,5});
    }

    private static void solution(int[] arr) {
        Arrays.sort(arr);

        int idx = 0;
        for(int i=1; i<arr.length; i++) {
            if(arr[i-1] == arr[i]){
                arr[idx++] = arr[i];
                while(i<arr.length && arr[i-1] == arr[i]){
                    i++;
                }
            }
        }

        for(int i=0; i<idx; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }
}
