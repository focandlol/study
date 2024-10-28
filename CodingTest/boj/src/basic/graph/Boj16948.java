package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16948 {
    static int n;
    static int[][] arr;
    static int endX;
    static int endY;
    static int dap = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        int[][] dis = {{-2,-1},{-2,1},{0,-2},{0,2},{2,-1},{2,1}};
        boolean[][] visited = new boolean[n][n];
        st = new StringTokenizer(br.readLine());
        int startX = Integer.parseInt(st.nextToken());
        int startY = Integer.parseInt(st.nextToken());
        endX = Integer.parseInt(st.nextToken());
        endY = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{startX, startY,0});

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            if(cur[0] == endX && cur[1] == endY) {
                dap = cur[2];
                break;
            }
            for(int i=0; i<6; i++) {
                int dx = cur[0]+dis[i][0];
                int dy = cur[1]+dis[i][1];
                if(dx >= 0 && dx < n && dy >= 0 && dy < n && !visited[dx][dy]) {
                    visited[dx][dy] = true;
                    queue.add(new int[]{dx,dy,cur[2] + 1});
                }
            }
        }
        System.out.println(dap);
    }
}
