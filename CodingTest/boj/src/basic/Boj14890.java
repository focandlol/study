package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14890 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int l = Integer.parseInt(st.nextToken());

    int[][] arr = new int[n][n];

    for(int i=0;i<n;i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++){
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    boolean[][] garo = new boolean[n][n];
    int dap = 2 * n;

    for(int i=0; i<n; i++){
      for(int j=0; j<n-1; j++){
        if(arr[i][j] == arr[i][j+1]){
          continue;
        }else if(arr[i][j] - arr[i][j+1] == 1){
          boolean flag = true;
          for(int k=1; k<=l; k++){
            if(j+k >= n || arr[i][j+1] != arr[i][j+k] || garo[i][j+k]){
              flag = false;
            }else{
              garo[i][j+k] = true;
            }
          }
          if(!flag){
            dap--;
            break;
          }
        }else if(arr[i][j] - arr[i][j+1] == -1){
          boolean flag = true;
          for(int k=0; k<l; k++){
            if(j-k < 0 || arr[i][j] != arr[i][j-k] || garo[i][j-k]){
              flag = false;
            }else{
              garo[i][j-k] = true;
            }
          }
          if(!flag){
            dap--;
            break;
          }
        }else{
          dap--;
          break;
        }
      }
    }

    boolean[][] sero = new boolean[n][n];
    for(int i=0; i<n; i++){
      for(int j=0; j<n-1; j++){
        if(arr[j][i] == arr[j+1][i]){
          continue;
        }else if(arr[j][i] - arr[j+1][i] == 1){
          boolean flag = true;
          for(int k=1; k<=l; k++){
            if(j+k >= n || arr[j+1][i] != arr[j+k][i] || sero[j+k][i]){
              flag = false;
            }else{
              sero[j+k][i] = true;
            }
          }
          if(!flag){
            dap--;
            break;
          }
        }else if(arr[j][i] - arr[j+1][i] == -1){
          boolean flag = true;
          for(int k=0; k<l; k++){
            if(j-k < 0 || arr[j][i] != arr[j-k][i] || sero[j-k][i]){
              flag = false;
            }else{
              sero[j-k][i] = true;
            }
          }
          if(!flag){
            dap--;
            break;
          }
        }else{
          dap--;
          break;
        }
      }
    }

  System.out.println(dap);
  }

}
