package quick;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class BaekJoon2750Left {

    public static void main(String[] args) throws IOException {
        //Scanner sc = new Scanner(System.in);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        int[] arr = new int[num];
        for(int i=0; i<num; i++){
            arr[i] = Integer.parseInt(bf.readLine());
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
        quick(arr,pivot+1,j);

    }

    private static int partition(int[] arr, int i, int j){
        int start = i;
        int end = j;
        int pivot = arr[i];

        while(start < end){
            while(arr[end] > pivot && start < end){
                end--;
            }
            while(arr[start] <= pivot && start < end){
                start++;
            }
            swap(arr,start,end);
        }
        swap(arr,start,i);
        return start;
    }

    private static void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i]  = arr[j];
        arr[j] = temp;
    }
}
