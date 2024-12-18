package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());
        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] arr = new int[2][n];
            int[][] dp = new int[2][n];
            for(int j=0; j<2; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<n; k++){
                    arr[j][k] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][0] = arr[0][0];
            dp[1][0] = arr[1][0];

            for(int j=1; j<n; j++){
                if(j == 1){
                    dp[0][j] = arr[1][j-1] + arr[0][j];
                    dp[1][j] = arr[0][j-1] + arr[1][j];
                    continue;
                }
                dp[0][j] = Math.max(dp[1][j-1],dp[1][j-2]) + arr[0][j];
                dp[1][j] = Math.max(dp[0][j-1],dp[0][j-2]) + arr[1][j];
            }

            sb.append(Math.max(dp[0][n-1],dp[1][n-1])).append("\n");
        }
        System.out.println(sb);
    }
}
