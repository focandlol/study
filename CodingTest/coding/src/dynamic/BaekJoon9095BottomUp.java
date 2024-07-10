package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon9095BottomUp {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new int[11];

        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 4;

        for(int i=4; i<arr.length; i++){
            arr[i] = arr[i-1] + arr[i-2] + arr[i-3];
        }

        for(int i=0; i<n; i++){
            int a = Integer.parseInt(br.readLine());
            System.out.println(arr[a]);
        }
    }
}
