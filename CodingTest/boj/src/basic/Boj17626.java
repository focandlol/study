package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj17626 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(br.readLine());

        int[] arr = new int[50001];
        Arrays.fill(arr,5);
        arr[0] = 0;
        arr[1] = 1;

        for(int i=2; i<=50000; i++){
            int idx = 1;
            while(idx * idx <= i){
                arr[i] = Math.min(arr[i], arr[i - idx*idx] + 1);
                idx++;
            }
            if(i == n) break;

        }

        System.out.println(arr[n]);
    }

}
