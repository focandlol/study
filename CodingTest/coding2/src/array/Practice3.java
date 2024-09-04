package array;

import java.util.Arrays;

public class Practice3 {
    public static void main(String[] args) {
        int[] arr = {1,3,5,7,9};
        int left = 0;
        int right = arr.length-1;

        while(left < right){
            int temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;

            left++;
            right--;
        }

        System.out.println(Arrays.toString(arr));
    }
}
