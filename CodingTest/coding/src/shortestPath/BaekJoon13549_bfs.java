package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon13549_bfs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[200000];
        boolean[] visited = new boolean[200000];

        Deque<int[]> pq = new ArrayDeque<>();
        pq.add(new int[]{n,0});
        visited[n] = true;

        int min = Integer.MAX_VALUE;
        while (!pq.isEmpty()) {
            int[] poll = pq.poll();
            visited[poll[0]] = true;
            if(poll[0] == k){
                min = Math.min(min,poll[1]);
                continue;
            }
            if(poll[0] * 2 <= arr.length-1 && poll[0] * 2 >= 0 && visited[poll[0] * 2] == false){
                pq.add(new int[]{poll[0] * 2,poll[1]});
            }
            if(poll[0] + 1 <= k && visited[poll[0] + 1] == false){
                pq.add(new int[]{poll[0] + 1,poll[1] + 1});
            }
            if(poll[0] - 1 >= 0 && visited[poll[0] - 1] == false){
                pq.add(new int[]{poll[0] - 1,poll[1] + 1});
            }

        }
        System.out.println(min);
    }
}

