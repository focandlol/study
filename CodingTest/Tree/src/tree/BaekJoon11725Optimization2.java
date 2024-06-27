package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon11725Optimization2 {
    static ArrayList<Integer>[] arr;
    static int[] visited;
    //부모 노드 배열
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        arr = new ArrayList[n+1];
        visited = new int[n+1];
        parent = new int[n+1];
        for(int i=1; i<=n; i++){
            arr[i] = new ArrayList<>();
        }

        //양방향 그래프
        for(int i=0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            arr[a].add(b);
            arr[b].add(a);
        }

        //루트 노드가 1이므로 1부터 탐색
        dfs(1);

        //2부터 출력
        //속도 증가를 위해 stringbuilder 사용
        for(int i = 2; i<=n; i++){
            sb.append(parent[i]).append("\n");
        }
        System.out.println(sb);
    }

    //dfs
    private static void dfs(int i) {
        visited[i] = 1;
        for(int j : arr[i]){
            //부모 노드에 현재 노드 삽입
            if(visited[j] == 0){
                parent[j] = i;
                dfs(j);
            }
        }
    }
}
