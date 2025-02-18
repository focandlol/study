package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2239 {

  static int[][] arr;
  static boolean flag = false;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    arr = new int[9][9];
    for(int i=0; i<9; i++){
      String s = br.readLine();
      for(int j=0; j<9; j++){
        arr[i][j] = s.charAt(j) - '0';
      }
    }

    dfs(0);

    StringBuilder sb = new StringBuilder();

    for(int i=0; i<9; i++){
      for(int j=0; j<9; j++){
        sb.append(arr[i][j]);
      }
      sb.append("\n");
    }

    System.out.println(sb);

  }

  static void dfs(int a){
    if(a == 81){
      flag = true;
      return;
    }

    int r = a / 9;
    int c = a % 9;

    if(arr[r][c] == 0){
      for(int i=1; i<=9; i++){

        if(valid(r,c,i)){
          arr[r][c] = i;
          dfs(a+1);
          if(flag) return;

          arr[r][c] = 0;
        }
      }
    }else{
      dfs(a + 1);
    }
  }

  static boolean valid(int r, int c, int i){
    for(int k=0; k<9; k++){
      if(arr[r][k] ==i) return false;
    }

    for(int k=0; k<9; k++){
      if(arr[k][c] == i) return false;
    }

    int sr = r/3 * 3;
    int sc = c/3 * 3;
    for(int k = sr; k< sr + 3; k++){
      for(int j = sc; j<sc + 3; j++){
        if(arr[k][j] == i) return false;
      }
    }

    return true;
  }

}
