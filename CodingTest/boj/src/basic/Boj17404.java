package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj17404 {
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[][] arr = new int[n+1][3];

    for(int i=1; i<=n; i++) {
      st = new StringTokenizer(br.readLine());
      int r = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      arr[i][0] = r;
      arr[i][1] = g;
      arr[i][2] = b;
    }

    int[][] dp = new int[n+1][3];

    for(int i=1; i<=n; i++) {
      Arrays.fill(dp[i], Integer.MAX_VALUE);
    }

    int min = Integer.MAX_VALUE;
    for(int i=0; i<3; i++){
      for(int k=0; k<3; k++){
        if(i == k) dp[1][i] = arr[1][i];
        else dp[1][k] = 1000001;
      }

      for(int j=2; j<=n; j++){
        dp[j][0] = Math.min(dp[j-1][1], dp[j-1][2]) + arr[j][0];
        dp[j][1] = Math.min(dp[j-1][0], dp[j-1][2]) + arr[j][1];
        dp[j][2] = Math.min(dp[j-1][0], dp[j-1][1]) + arr[j][2];
      }

      for(int b = 0; b<3; b++){
        if(b != i) min = Math.min(min, dp[n][b]);
      }
    }

    System.out.println(min);
  }
}
