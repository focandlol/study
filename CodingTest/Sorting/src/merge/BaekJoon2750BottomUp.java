package merge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class BaekJoon2750BottomUp {

    private static int[] sorted;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(bf.readLine());
        int[] arr = new int[num];
        for(int i=0; i<num; i++){
            arr[i] = Integer.parseInt(bf.readLine());
        }
        sorted = new int[arr.length];
        System.out.println("arr = " + Arrays.toString(arr));
        merge_sort(arr,0,arr.length-1);

        for(int i=0; i<arr.length; i++){
            System.out.println(arr[i]);
        }
    }

    private static void merge_sort(int[] arr, int left, int right){
        for(int i=1; i<=right; i*=2){

            for(int j=0; j<=right-i+1; j+=(i*2)){
                int low = j;
                int mid = i+j-1;
                int high = Math.min(j+2*i-1,right);



                System.out.println("low =" +low + "mid=" + mid + "high=" + high);
                merge(arr,low,mid,high);
            }
        }
    }

    private static void merge(int[] arr, int low, int mid, int high){
        int l = low;
        int p = low;
        int r = mid + 1;

        while(l <= mid && r <= high){
            if(arr[l] < arr[r]){
              //  System.out.println(arr[l]);
             //   System.out.println(arr[r]);
                sorted[p++] = arr[l++];
            }else{
                //System.out.println(arr[l]);
              //  System.out.println(arr[r]);
                sorted[p++] = arr[r++];
            }
        }
       //System.out.println(Arrays.toString(sorted));

        if(l <= mid){
            while(l <= mid){
                sorted[p++] = arr[l++];

            }
            //System.out.println(Arrays.toString(sorted));
        }else{
            while(r <= high){
                sorted[p++] = arr[r++];
            }
           // System.out.println(Arrays.toString(sorted));
        }

        for(int i=0; i<=high; i++){
            arr[i] = sorted[i];
        }
       // System.out.println(Arrays.toString(sorted));
        System.out.println("Arr" +Arrays.toString(arr));
    }
}
