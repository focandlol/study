package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Baekjoon1697 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        if (n == k){
            System.out.println(0);
            return;
        }

        int[] visited = new int[300000];
        int[] dx = {-1,1,2};

        Deque<Integer> dq = new ArrayDeque<>();
        dq.add(n);

        while(!dq.isEmpty()){
            int cur = dq.poll();
            if(cur == k){
                System.out.println(visited[cur]);
            }
            if(cur * 2 < visited.length && visited[cur * 2] == 0){
                if(cur != 0) {
                    visited[cur * 2] = visited[cur] + 1;
                    dq.add(cur * 2);
                }
            }
            if(cur - 1 >= 0 && visited[cur - 1] == 0){
                visited[cur - 1] = visited[cur] + 1;
                dq.add(cur - 1);
            }
            if(cur + 1 <= 100000 && visited[cur + 1] == 0){
                visited[cur + 1] = visited[cur] + 1;
                dq.add(cur + 1);
            }

        }
    }
}
