package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj13023 {
    static int n;
    static int m;
    static ArrayList<Integer>[] graph;
    static boolean[] visited;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n];
        for(int i=0; i<n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i=0; i<n; i++){
            visited = new boolean[n];
            dfs(i,1);
        }
        System.out.println(0);
    }

    private static void dfs(int a,int depth) {
        if(depth == 5){
            System.out.println(1);
            System.exit(0);
        }
        if(!visited[a]){
            visited[a] = true;
            for(int b : graph[a]){
                if(!visited[b]){
                    dfs(b,depth+1);
                }
            }
            visited[a] = false;
        }
    }
}
