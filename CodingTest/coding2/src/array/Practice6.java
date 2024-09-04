package array;

import java.util.Arrays;

public class Practice6 {
    public static void main(String[] args) {
        int[] arr = {1,5,3,2,2,3,1,4,1,2,3,5};
        int[] arr2 = new int[arr.length];

        int cnt = 0;
        for(int i=0; i<arr.length; i++){
            boolean flag = false;
            for(int j=0; j<cnt; j++){
                if(arr[i] == arr2[j]){
                    flag = true;
                }
            }
            if(!flag){
                arr2[cnt++] = arr[i];
            }
        }

        for(int i=0; i<cnt; i++){
            System.out.print(arr2[i] + " ");
        }
    }
}
