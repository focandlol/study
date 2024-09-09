package sort;

import java.util.Arrays;

public class Practice1 {
    public static void main(String[] args) {
        int[] arr = {2,0,2,1,1,0};
        solution(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println();

        arr = new int[]{2, 0, 1};
        solution(arr);
        System.out.println(Arrays.toString(arr));
        System.out.println();
    }

    private static void solution(int[] arr) {
        if(arr.length == 0)return;
        int[] arr2 = new int[3];
        for(int i=0; i<arr.length; i++) {
            arr2[arr[i]]++;
        }

        int idx = 0;
        for(int i=0; i<arr2.length; i++) {
            if(arr2[i] != 0) {
                for(int j=0; j<arr2[i]; j++){
                    arr[idx++] = i;
                }
            }
        }
    }
}
