package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BaekJoon24479 {
    static ArrayList<Integer>[] arr;
    static int[] visited;
    static StringBuilder sb = new StringBuilder();
    static int count = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        visited = new int[n+1];
        for (int i = 1; i <= n; i++) {
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
            Collections.sort(arr[i]);
        }

        dfs(r);

        for(int i=1; i<=n; i++){
            sb.append(visited[i]).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int i) {

        visited[i] = count;
        for(int a : arr[i]){
            if(visited[a] == 0){
                count++;
                dfs(a);
            }
        }


    }
}
