package lca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon11437V2 {
    static ArrayList<Integer>[] list;
    static int[] visited;
    static int[] depth;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        list = new ArrayList[n + 1];
        visited = new int[n + 1];
        depth = new int[n + 1];
        parent = new int[n + 1];
        depth[1] = 1;
        parent[1] = 0;
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        bfs(1);

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int result = find(a, b);
            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static int find(int a, int b) {
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        while(depth[a] != depth[b]){
            a = parent[a];
        }

        while(a != b){
            a = parent[a];
            b = parent[b];
        }
        return a;
    }

    private static void bfs(int i) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(i);
        while(!queue.isEmpty()){
            int now = queue.poll();
            visited[now] = 1;
            for(int n : list[now]){
                if(visited[n] == 0){
                    queue.add(n);
                    parent[n] = now;
                    depth[n] = depth[now] + 1;
                }
            }
        }
    }
}
