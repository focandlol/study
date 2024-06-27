package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon11725Optimization1 {
    static ArrayList<Integer>[] arr;
    static int[] visited;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        visited = new int[n+1];
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        dfs(1);

        for(int i=2; i<=n; i++){
            System.out.println(parent[i]);
        }
    }

    private static void dfs(int i) {
        visited[i] = 1;
        for(int j : arr[i]){
            if(visited[j] == 0){
                parent[j] = i;
                dfs(j);
            }
        }
    }
}
