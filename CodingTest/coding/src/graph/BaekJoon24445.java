package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon24445 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[] visited = new int[n+1];
        ArrayList<Integer>[] arr = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u].add(v);
            arr[v].add(u);
        }

        for(int i=1; i<=n; i++){
            arr[i].sort(Comparator.reverseOrder());
        }

        Deque<Integer> queue = new LinkedList<>();
        queue.offer(r);
        int cnt = 1;
        visited[r] = cnt++;

        while(!queue.isEmpty()){
            int poll = queue.poll();
            for(int i : arr[poll]){
                if(visited[i] == 0){
                    queue.offer(i);
                    visited[i] = cnt++;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);
    }
}
