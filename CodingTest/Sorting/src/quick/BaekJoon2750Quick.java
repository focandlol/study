package quick;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BaekJoon2750Quick {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int[] arr = new int[num];
        for(int i=0; i<num; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        quick(arr,0,num-1);

        for(int i=0; i<num; i++){
            System.out.println(arr[i]);
        }
    }

    private static void quick(int[] arr, int i, int j){

        if(i>=j){
            return;
        }
        int pivot = partition(arr, i, j);

        quick(arr,i,pivot-1);
        quick(arr,pivot,j);

    }

    private static int partition(int[] arr, int start, int end){
        int pivot = arr[(start+end)/2];

        while(start <= end){
            while(arr[end] > pivot){
                end--;
            }
            while(arr[start] < pivot){
                start++;
            }
            if(start <= end) {
                swap(arr, start++, end--);
            }
        }
        return start;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i]  = arr[j];
        arr[j] = temp;
    }
}