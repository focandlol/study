package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10844 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final long mod = 1000000000;
        int n = Integer.parseInt(br.readLine());

        long[][] arr = new long[n+1][10];

        for(int i=1;i<10;i++){
            arr[1][i] = 1;
        }

        for(int i=2; i<n+1;i++){
            for(int j=0;j<10;j++){

                if(j == 0){
                    arr[i][j] = arr[i-1][1];
                }
                else if(j == 9){
                    arr[i][j] = arr[i-1][8];
                }
                else{
                    arr[i][j] = (arr[i-1][j-1] + arr[i-1][j+1]) % mod;
                }
            }
        }

        long dap = 0;
        for(int i=0;i<10;i++){
            dap += arr[n][i];
        }

        System.out.println(dap % mod);

    }
}
