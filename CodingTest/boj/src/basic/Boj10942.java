package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10942 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());
    int[] arr = new int[n + 1];

    boolean[][] dp = new boolean[n + 1][n + 1];

    for(int i=1; i<=n; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    find(dp, n, arr);

    st = new StringTokenizer(br.readLine());

    int m = Integer.parseInt(st.nextToken());

    StringBuilder sb = new StringBuilder();

    for(int i=0; i<m; i++){
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      if(dp[a][b]){
        sb.append(1);
      }else{
        sb.append(0);
      }
      sb.append("\n");

    }

    System.out.println(sb);
  }

  private static void find(boolean[][] dp, int n, int[] arr) {
    for(int i=1; i<=n; i++){
      dp[i][i] = true;
    }

    for(int i=1; i<n; i++){
      if(arr[i] == arr[i+1]){
        dp[i][i+1] = true;
      }
    }

    for(int i=2; i<n; i++){
      for(int j=1; j <= n-i; j++){
        if(arr[j] == arr[j+i] && dp[j+1][j+i-1]){
          dp[j][j+i] = true;
        }
      }
    }
  }

}
