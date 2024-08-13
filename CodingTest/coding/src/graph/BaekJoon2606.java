package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon2606 {
    static ArrayList<Integer>[] arr;
    static int[] visited;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        visited = new int[n+1];

        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        visited[1] = 1;
        dfs(1);

        System.out.println(count);

    }

    private static void dfs(int i) {

        for(int a : arr[i]){
            if(visited[a] == 0){
                visited[a] = 1;
                count++;
                dfs(a);
            }
        }
    }
}
