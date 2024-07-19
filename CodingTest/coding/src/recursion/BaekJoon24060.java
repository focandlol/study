package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon24060 {
    static long[] arr;
    static long[] temp;
    static int count = 0;
    static int k;
    static long dap = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new long[n];
        temp = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        merge_sort(0,arr.length-1);
        System.out.println(dap);
    }

    private static void merge_sort(int start, int end) {

        if(start == end) {
            return;
        }
        int mid = (start + end) / 2;
        merge_sort(start,mid);
        merge_sort(mid+1,end);
        merge(start,mid,end);
    }

    private static void merge(int start, int mid, int end) {
        int i = start;
        int j = mid+1;
        int left = start;
        while(i <= mid && j <= end){
            if(arr[i] < arr[j]){
                temp[start++] = arr[i++];
            }else{
                temp[start++] = arr[j++];
            }
        }

        while(i <= mid){
            temp[start++] = arr[i++];
        }

        while(j <= end){
            temp[start++] = arr[j++];
        }

        for(int p=left; p<=end; p++){
            arr[p] = temp[p];
            count++;
            if(count == k){
                System.out.println(temp[p]);
                System.exit(0);
            }
        }
    }
}
