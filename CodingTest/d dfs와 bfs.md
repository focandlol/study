```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static ArrayList<Integer>[] arr;
    static int[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        arr = new ArrayList[n+1];
        visited = new int[n+1];

        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a].add(b);
            arr[b].add(a);
        }

        for(int i=1; i<=n; i++){
            arr[i].sort(null);
        }

        dfs(r);
        sb.append("\n");
        Arrays.fill(visited,0);

        Deque<Integer> queue = new ArrayDeque<>();
        queue.offer(r);
        visited[r] = 1;
        while(!queue.isEmpty()){
            int cur = queue.poll();
            sb.append(cur).append(" ");
            for(int i : arr[cur]){
                if(visited[i] == 0){
                    queue.offer(i);
                    visited[i] = 1;
                }
            }
        }

        System.out.println(sb);


    }

    private static void dfs(int r) {
        visited[r] = 1;
        sb.append(r).append(" ");
        for(int i : arr[r]){
            if(visited[i] == 0){
                dfs(i);
            }
        }
    }
}


```
