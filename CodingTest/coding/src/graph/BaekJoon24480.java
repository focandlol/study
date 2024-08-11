package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BaekJoon24480 {
    static ArrayList<Integer>[] arr;
    static int[] visited;
    static int count = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        visited = new int[n+1];

        for(int i = 1; i < n+1; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());

            arr[u].add(v);
            arr[v].add(u);
        }

        for(int i = 1; i < n+1; i++) {
            arr[i].sort(Comparator.reverseOrder());
        }

        dfs(r);
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i < n+1; i++) {
            sb.append(visited[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int r) {

        visited[r] = count;
        for(int i : arr[r]){
            if(visited[i] == 0){
                count++;
                dfs(i);
            }
        }
    }
}
