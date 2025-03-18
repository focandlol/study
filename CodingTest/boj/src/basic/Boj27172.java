package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj27172 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    int[] arr = new int[n];
    int max = 0;
    for(int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
      max = Math.max(max, arr[i]);
    }

    int[] dap = new int[max + 1];

    for(int i=0; i<n; i++){
      dap[arr[i]] = i+1;
    }

    int[] result = new int[n+1];

    for(int a : arr){
      for(int i = a * 2; i<=max; i+=a){
        if(dap[i] != 0){
          result[dap[i]]--;
          result[dap[a]]++;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    for(int i=1; i<=n; i++){
      sb.append(result[i]).append(" ");
    }

    System.out.println(sb);
  }

}
