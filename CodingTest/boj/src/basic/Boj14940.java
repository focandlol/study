package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14940 {
    static int[][] dis = new int[][]{{0,1,0,-1},{1,0,-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];
        int[] start = new int[2];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start[0],start[1],0});
        boolean[][] visited = new boolean[n][m];
        visited[start[0]][start[1]] = true;
        int[][] dap = new int[n][m];

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            dap[cur[0]][cur[1]] = cur[2];

            for(int i=0; i<4; i++){
                int dx = cur[0] + dis[0][i];
                int dy = cur[1] + dis[1][i];
                if(dx >= 0 && dx < n && dy >= 0 && dy < m && !visited[dx][dy] && arr[dx][dy] != 0){
                    visited[dx][dy] = true;
                    queue.add(new int[]{dx,dy,cur[2] + 1});
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 1 && dap[i][j] == 0){
                    sb.append(-1).append(" ");
                }else {
                    sb.append(dap[i][j]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
