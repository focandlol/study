package lca.plus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon11438v1 {
    static ArrayList<Integer>[] list;
    static int[] visited;
    static int[] depth;
    static int[] parent;
    static int maxDepth = 0;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        //노드 리스트
        list = new ArrayList[n + 1];
        //방문 확인 배열
        visited = new int[n + 1];
        //노드의 깊이 배열
        depth = new int[n + 1];
        //노드의 부모 노드 배열
        parent = new int[n + 1];

        //첫 번째 노드 초기화
        depth[1] = 1;
        parent[1] = 0;

        //노드 리스트 초기화
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        //노드 입력
        for (int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            list[a].add(b);
            list[b].add(a);
        }

        //꼭대기 1번 노드부터 bfs 탐색
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        bfs(1);

        //maxDepth = 현재 트리의 최대 깊이
        //while문으로 maxDepth보다 작은 2의 지수 승 구함
        int depth = 0;
        while(Math.pow(2,depth) < maxDepth){
            depth++;
        }

        arr = new int[depth][n+1];
        for(int i=0; i<depth; i++){
            for(int j=1; j<=n; j++){
                if(i == 0){
                    arr[i][j] = parent[j];
                }else{
                    arr[i][j] = arr[i-1][arr[i-1][j]];
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        //최소 공통부모 찾기
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
        //항상 a를 더 깊도록 만듬
        if(depth[a] < depth[b]){
            int temp = a;
            a = b;
            b = temp;
        }

        while(depth[a] != depth[b]) {
            int cha = depth[a] - depth[b];
            int count = 0;
            while (Math.pow(2, count) <= cha) {
                count++;
            }
            int k = count - 1;
            a = arr[k][a];
        }

        for(int i = arr.length-1; i >= 0; i--){
            if(arr[i][a] != arr[i][b]){
                a = arr[i][a];
                b = arr[i][b];
            }
        }

        if(a == b){
            return a;
        }else{
            return parent[a];
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
                    //부모 노드와 깊이 삽입
                    parent[n] = now;
                    int i1 = depth[now] + 1;
                    depth[n] = depth[now] + 1;
                    maxDepth = i1;
                }
            }
        }
    }
}