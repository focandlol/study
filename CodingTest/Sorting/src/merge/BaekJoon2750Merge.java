package merge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BaekJoon2750Merge {

    private static int[] sorted;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        int[] arr = new int[num];
        for(int i=0; i<num; i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }
        sorted = new int[arr.length];
        merge_sort(arr,0,arr.length-1);

        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    private static void merge_sort(int[] arr, int left, int right){
        if(left == right){
            return;
        }
        int mid = (left + right)/2;

        merge_sort(arr,left,mid);
        merge_sort(arr,mid+1,right);

        merge(arr,left,mid,right);
    }

    private static void merge(int[] arr, int left, int mid, int right){

        int l = left;
        int r = mid + 1;
        int p = left;

        while(l <= mid && r <= right){
            if(arr[l] < arr[r]){
                sorted[p++] = arr[l++];
            }else{
                sorted[p++] = arr[r++];
            }
        }

        if(l > mid){
            while(r <= right){
                sorted[p++] = arr[r++];
            }
        }else{
            while(l <= mid){
                sorted[p++] = arr[l++];
            }
        }

        for(int i=0; i<=right; i++){
            arr[i] = sorted[i];
        }
    }
}
