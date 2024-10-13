package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        long[][] arr = new long[n+1][2];
        arr[1][1] = 1;

        for(int i=2; i<=n; i++) {
            for(int j=0; j<2; j++) {
                if(j == 0){
                    arr[i][j] = arr[i-1][0] + arr[i-1][1];
                }else if(j == 1){
                    arr[i][j] = arr[i-1][0];
                }
            }
        }
        long dap = 0;
        for(int i=0; i<2; i++){
            dap += arr[n][i];
        }
        System.out.println(dap);
    }
}
