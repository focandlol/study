package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1261 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n + 1][m + 1];

        for(int i=1; i<=n; i++) {
            String s = br.readLine();
            for(int j=1; j<=m; j++) {
                arr[i][j] = s.charAt(j-1);
            }
        }

        int[][] di = {{1,0,-1,0},{0,1,0,-1}};

        boolean[][] visited = new boolean[n + 1][m + 1];


        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        queue.add(new int[]{1,1,0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if(visited[cur[0]][cur[1]]) continue;
            visited[cur[0]][cur[1]] = true;

            if(cur[0] == n && cur[1] == m) {
                System.out.println(cur[2]);
                return;
            }

            for(int i=0; i<4; i++) {
                int dx = cur[0] + di[0][i];
                int dy = cur[1] + di[1][i];

                if(dx >= 1 && dx <= n && dy >= 1 && dy <= m) {
                    if(arr[dx][dy] == '1' && !visited[dx][dy]) {
                        queue.add(new int[]{dx,dy,cur[2] + 1});
                    }else if(arr[dx][dy] == '0' && !visited[dx][dy]) {
                        queue.add(new int[]{dx,dy,cur[2]});
                    }
                }
            }
        }
    }
}
