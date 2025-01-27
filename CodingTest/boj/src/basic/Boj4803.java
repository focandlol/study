package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj4803 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st;
    StringBuilder sb = new StringBuilder();
    int t = 1;

    while(true){
      st = new StringTokenizer(br.readLine());
      int n = Integer.parseInt(st.nextToken());
      int m = Integer.parseInt(st.nextToken());

      if(n == 0 && m == 0) break;

      ArrayList<Integer>[] graph = new ArrayList[n+1];
      boolean[] visited = new boolean[n+1];
      for(int i=1; i<=n; i++){
        graph[i] = new ArrayList<>();
      }

      for(int i=0; i<m; i++){
        st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        graph[a].add(b);
        graph[b].add(a);
      }

      int count = 0;
      for(int i=1; i<=n; i++) {
        if(graph[i].size() == 0){
          count++;
          continue;
        }
        if(!visited[i] && find(i,graph, visited)){
          count++;
        }
      }

      sb.append("Case ").append(t).append(": ");
      if(count == 0){
        sb.append("No trees.\n");
      }else if(count == 1){
        sb.append("There is one tree.\n");
      }else{
        sb.append("A forest of ").append(count).append(" trees.\n");
      }
      t++;
    }

    System.out.println(sb);
  }

  private static boolean find(int start, ArrayList<Integer>[] graph, boolean[] visited) {
    int dot = 1;
    int gasu = graph[start].size();
    Queue<Integer> queue = new LinkedList<>();
    queue.add(start);
    visited[start] = true;

    while(!queue.isEmpty()){
      int cur = queue.poll();

      for(int a : graph[cur]){
        if(!visited[a]){
          visited[a] = true;
          gasu += graph[a].size();
          dot++;
          queue.add(a);
        }
      }
    }
    return dot-1 == gasu/2;
  }

}
