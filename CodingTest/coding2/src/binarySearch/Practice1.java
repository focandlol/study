package binarySearch;

import java.util.Arrays;

public class Practice1 {
    public static void main(String[] args) {
        int[] arr = {1,2,5,10,20,30,40,50,60};
        System.out.println(solution(arr,30));
        System.out.println(solution(arr,3));
        System.out.println(solution(arr,11));
        System.out.println(solution(arr,35));
    }

    private static int solution(int[] arr, int i) {
        Arrays.sort(arr);
        int left = 0;
        int right = arr.length - 1;
        while(left <= right){
            int mid = (left + right) / 2;
            if(arr[mid] == i){
                return mid;
            }else if(arr[mid] > i){
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }

        return left * -1 - 1;
    }
}
