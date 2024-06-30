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

        //a와 b의 깊이가 같을 때까지 반복
        while(depth[a] != depth[b]){
            a = parent[a];
        }

        //a와 b의 깊이가 같다면 둘 다 부모 노드로 올라가면서 같을 때까지 반복
        while(a != b){
            a = parent[a];
            b = parent[b];
        }
        //a,b가 같아진 노드 리턴
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
                    //부모 노드와 깊이 삽입
                    parent[n] = now;
                    depth[n] = depth[now] + 1;
                }
            }
        }
    }
}
