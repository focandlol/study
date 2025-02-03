package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2775 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int t = Integer.parseInt(br.readLine());

    StringBuilder sb = new StringBuilder();

    for(int i=0; i<t; i++) {
      int k = Integer.parseInt(br.readLine());
      int n = Integer.parseInt(br.readLine());

      int[][] arr = new int[k+1][n+1];
      for(int j=1; j<=n; j++){
        arr[0][j] = j;
      }

      for(int j=1; j<=k; j++){
        for(int l=1; l<=n; l++){
          if(l == 1){
            arr[j][l] = arr[j-1][l];
          }else{
            arr[j][l] = arr[j][l-1] + arr[j-1][l];
          }
        }
      }

      sb.append(arr[k][n]).append("\n");
    }
    System.out.println(sb);
  }
}
