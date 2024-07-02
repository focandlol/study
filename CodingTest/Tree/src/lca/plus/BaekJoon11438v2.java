package lca.plus;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon11438v2 {
    static ArrayList<Integer>[] list;
    static int[] visited;
    static int[] depth;
    static int[][] arr;
    static int kmax;

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


        //첫 번째 노드 초기화
        depth[1] = 1;


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

        int temp = 1;
        kmax = 0;
        while (temp < n + 1) { // 최대 가능 depth 구하기
            temp <<= 1;
            kmax++;
        }

        arr = new int[kmax+1][n+1];

        //꼭대기 1번 노드부터 bfs 탐색
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        bfs(1);


        for(int i=1; i<=kmax; i++){
            for(int j = 1; j<=n; j++) {
                arr[i][j] = arr[i - 1][arr[i - 1][j]];
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
        if (depth[a] > depth[b]) { // 더 깊은 depth가 b가 되도록 변경하기
            int temp = a;
            a = b;
            b = temp;
        }
        for (int k = kmax; k >= 0; k--) { // depth를 빠르게 맞추기
            if (Math.pow(2, k) <= depth[b] - depth[a]) {
                if (depth[a] <= depth[arr[k][b]]) {
                    b = arr[k][b];
                }
            }
        }
        for(int i = kmax; i >= 0; i--){
            if(arr[i][a] != arr[i][b]){
                a = arr[i][a];
                b = arr[i][b];
            }
        }

        if(a == b){
            return a;
        }else{
            return arr[0][a];
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
                    arr[0][n] = now;
                    depth[n] = depth[now] + 1;
                }
            }
        }
    }
}
