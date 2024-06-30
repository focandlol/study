package lca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon11437V1 {
    static ArrayList<Integer>[] list;
    static int[] visited;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        list = new ArrayList[n+1];
        visited = new int[n+1];
        arr = new int[n+1][2];
        arr[1][0] = 0;
        arr[1][1] = 1;
        for(int i=1; i<=n; i++){
            list[i] = new ArrayList<>();
        }

        for(int i=1; i<n; i++){
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

        for(int i=1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            find(a,b);
        }
    }

    private static void find(int a, int b) {
        while(true){
            if(arr[a][1] < arr[b][1]){
                b = arr[b][0];
            }
            if(arr[a][1] > arr[b][1]){
                a = arr[a][0];
            }
            if(arr[a][1] == arr[b][1]){
                if(a == b){
                    System.out.println(a);
                    break;
                }else{
                    a = arr[a][0];
                    b = arr[b][0];
                }
            }
        }
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
                    arr[n][0] = now;
                    arr[n][1] = arr[now][1] + 1;
                }
            }
        }
    }
}

