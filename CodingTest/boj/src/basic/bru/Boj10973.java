package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10973 {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int i=arr.length-1;
        while(i > 0 && arr[i] >= arr[i-1]){
            i--;
        }
        if(i == 0) {
            System.out.println(-1);
            return;
        }

        int j = arr.length-1;
        while(arr[j]>=arr[i-1]){
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
        for(int k : arr){
            sb.append(k).append(" ");
        }

        System.out.println(sb);
    }

    private static void swap(int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
