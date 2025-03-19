package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1005 {

  static StringBuilder sb = new StringBuilder();

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int t = Integer.parseInt(st.nextToken());

    for(int p = 0; p<t; p++){
      st = new StringTokenizer(br.readLine());

      int n = Integer.parseInt(st.nextToken());
      int k = Integer.parseInt(st.nextToken());

      int[] time = new int[n+1];
      ArrayList<Integer>[] graph = new ArrayList[n+1];

      st = new StringTokenizer(br.readLine());
      for(int i=1; i<=n; i++){
        time[i] = Integer.parseInt(st.nextToken());
        graph[i] = new ArrayList<>();
      }

      int[] arr = new int[n+1];

      for(int i=0; i<k; i++){
        st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        arr[y]++;
        graph[x].add(y);
      }

      st = new StringTokenizer(br.readLine());

      int w = Integer.parseInt(st.nextToken());

      find(time, arr, graph,w);
    }
    System.out.println(sb);
  }

  private static void find(int[] time, int[] arr, ArrayList<Integer>[] graph, int w) {
    Queue<Integer> queue = new LinkedList<>();
    int[] result = new int[time.length];

    for(int i=1; i<time.length; i++){
      if(arr[i] == 0){
        queue.add(i);
        result[i] = time[i];
      }
    }

    while(!queue.isEmpty()){
      int cur = queue.poll();

      for(int i : graph[cur]){
        result[i] = Math.max(result[i], result[cur] + time[i]);
        arr[i]--;

        if(arr[i] == 0){
          queue.add(i);
        }
      }
    }

    sb.append(result[w]).append("\n");

  }
}
