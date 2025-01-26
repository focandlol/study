package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16931 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] arr = new int[n][m];

    int sum = 0;

    for(int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
        if(arr[i][j] != 0) sum++;
      }
    }

    sum *=2;

    for(int i=0; i < n; i++) {
      for(int j=0; j < m-1; j++) {
        if(arr[i][j] < arr[i][j+1]){
          sum += arr[i][j+1] - arr[i][j];
        }
      }
      sum += arr[i][0];
    }

    for(int i=0; i<m; i++){
      for(int j=n-1; j > 0; j--){
        if(arr[j][i] < arr[j-1][i]){
          sum += arr[j-1][i] - arr[j][i];
        }
      }
      sum+=arr[n-1][i];
    }

    for(int i=0; i<n; i++){
      for(int j=m-1; j>0; j--){
        if(arr[i][j] < arr[i][j-1]){
          sum += arr[i][j-1] - arr[i][j];
        }
      }
      sum+=arr[i][m-1];
    }

    for(int i=0; i<m; i++){
      for(int j=0; j<n-1; j++){
        if(arr[j][i] < arr[j+1][i]){
          sum += arr[j+1][i] - arr[j][i];
        };
      }
      sum+=arr[0][i];
    }

    System.out.println(sum);
  }

}
