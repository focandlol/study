package sort;

import java.util.Arrays;

public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {10,32,52,27,48,17,99,56};
        shellSort(arr);
        System.out.println("쉘 정렬 " + Arrays.toString(arr));
    }

    private static void shellSort(int[] arr) {
        int gap = arr.length/2;

        for(int i = gap; i>0; i /= 2) {
            for(int a = i; a<arr.length; a++) {
                int tmp = arr[a];

                int j = 0;
                for(j = a-i; j>=0; j = j -= i) {
                    if(arr[j] > arr[a]){
                        arr[j+i] = arr[j];
                    }else{
                        break;
                    }
                }
                arr[j+i] = tmp;
            }
        }
    }
}
