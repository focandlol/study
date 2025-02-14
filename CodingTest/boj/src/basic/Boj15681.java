package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj15681 {
  static int n;
  static int r;
  static int q;
  static ArrayList<Integer>[] list;
  static boolean[] visited;
  static int[] dap;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    r = Integer.parseInt(st.nextToken());
    q = Integer.parseInt(st.nextToken());

    list = new ArrayList[n+1];
    for(int i=1; i<=n; i++) {
      list[i] = new ArrayList<>();
    }

    visited = new boolean[n+1];
    dap = new int[n+1];

    for(int i=0; i<n-1; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      list[a].add(b);
      list[b].add(a);
    }

    dfs(r);

    StringBuilder sb = new StringBuilder();
    for(int i=0; i<q; i++){
      sb.append(dap[Integer.parseInt(br.readLine())]).append("\n");
    }

    System.out.println(sb);
  }

  static int dfs(int node){
    int count = 1;
    visited[node] = true;

    for(int a : list[node]){
      if(!visited[a]){
        count += dfs(a);
      }
    }

    dap[node] = count;
    return count;
  }

}
