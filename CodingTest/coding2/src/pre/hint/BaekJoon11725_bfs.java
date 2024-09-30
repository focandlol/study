package pre.hint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon11725_bfs {
    static int[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        visited = new int[n+1];
        ArrayList<Integer>[] list = new ArrayList[n+1];
        for(int i = 1; i <= n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        visited[1] = 1;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            for(int c : list[cur]){
                if(visited[c] == 0){
                    visited[c] = cur;
                    queue.add(c);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        //IntStream.range(2,visited.length).forEach(i -> sb.append(visited[i]).append("\n"));
        for(int i =2; i<=n; i++){
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);
    }
}
