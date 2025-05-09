package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2748 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    int n = Integer.parseInt(br.readLine());

    long[] arr = new long[n+1];
    if(n == 0){
      System.out.println(0);
      return;
    }else if(n == 1){
      System.out.println(1);
      return;
    }

    arr[0] = 0;
    arr[1] = 1;

    for(int i=2; i<=n; i++){
      arr[i] = arr[i-2] + arr[i-1];
    }

    System.out.println(arr[n]);
  }

}
