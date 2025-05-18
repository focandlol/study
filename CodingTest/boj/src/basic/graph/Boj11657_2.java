package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11657_2 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] graph = new int[m][3];

    long[] dap = new long[n + 1];
    Arrays.fill(dap, Long.MAX_VALUE);

    dap[1] = 0;

    for(int i=0; i<m; i++){
      st = new StringTokenizer(br.readLine());

      graph[i][0] = Integer.parseInt(st.nextToken());
      graph[i][1] = Integer.parseInt(st.nextToken());
      graph[i][2] = Integer.parseInt(st.nextToken());
    }

    for(int i=0; i<n; i++){
      for(int j=0; j<m; j++){
        if(dap[graph[j][0]] != Long.MAX_VALUE && dap[graph[j][0]] + graph[j][2] < dap[graph[j][1]]){
          if(i == n-1){
            System.out.println(-1);
            return;
          }
          dap[graph[j][1]] = dap[graph[j][0]] + graph[j][2];
        }
      }
    }

    StringBuilder sb = new StringBuilder();

    for(int i=2; i<=n; i++){
      if(dap[i] == Long.MAX_VALUE){
        sb.append(-1).append("\n");
      }else{
        sb.append(dap[i]).append("\n");
      }
    }

    System.out.println(sb);
  }

}
