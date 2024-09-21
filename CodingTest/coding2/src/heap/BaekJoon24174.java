package heap;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon24174 {
    static int count = 0;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        heap_sort(arr);
        System.out.println(-1);
    }

    public static void heap_sort(int[] arr) {
        build_min_heap(arr,arr.length);
        for(int i=arr.length-1; i>0; i--){
            swap(arr,0,i);
            heapify(arr,0,i);
        }
    }

    private static void build_min_heap(int[] arr, int n) {
        for(int i = n/2-1; i>=0; i--){
            heapify(arr,i,n);
        }
    }

    public static void heapify(int[] arr,int p,int n){
        int left = 2*p+1;
        int right = 2*p+2;
        int smaller = 0;

        if(right < n){
            if(arr[left] < arr[right]){
                smaller = left;
            }else{
                smaller = right;
            }
        }else if(left < n){
            smaller = left;
        }else{
            return;
        }
        if(arr[smaller] < arr[p]){
            swap(arr,smaller,p);
            heapify(arr,smaller,n);
        }
    }

    private static void swap(int[] arr, int smaller, int p) {
        int temp = arr[smaller];
        arr[smaller] = arr[p];
        arr[p] = temp;
        count++;
        if(count == k){
            StringBuilder sb = new StringBuilder();
            for (int i : arr) {
                sb.append(i).append(" ");
            }
            System.out.println(sb);
            System.exit(0);
        }
    }
}
