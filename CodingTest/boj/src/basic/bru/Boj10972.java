package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10972 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int i = arr.length -1;
        while(i > 0 && arr[i-1] >= arr[i]){
            i--;
        }
        
        if(i <= 0){
            System.out.println("-1");
            return;
        }
        
        int j = arr.length-1;
        while(arr[j] <= arr[i-1]){
            j--;
        }
        swap(i-1,j);

        j = arr.length-1;
        while(i<j){
            swap(i,j);
            i++;
            j--;
        }

        StringBuilder sb = new StringBuilder();
        for (int i1 : arr) {
            sb.append(i1 + " ");
        }
        System.out.println(sb);
        
        
    }

    public static void swap(int idx1, int idx2) {
        int temp = arr[idx1];
        arr[idx1] = arr[idx2];
        arr[idx2] = temp;
    }
}
