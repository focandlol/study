package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon1904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        if(n == 1){
            System.out.println("1");
            return;
        }
        if(n == 2){
            System.out.println("2");
            return;
        }

        arr[1] = 1;
        arr[2] = 2;

        for(int i=3; i<=n; i++){
            arr[i] = (arr[i-1] + arr[i-2]) % 15746;
        }
        System.out.println(arr[n]);
    }
}
