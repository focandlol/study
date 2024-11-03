package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14442 {
    static int n;
    static int m;
    static int k;
    static char[][] arr;
    static boolean[][][] visited;
    static int[][] di = {{0,1,0,-1},{1,0,-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new char[n+1][m+1];
        visited = new boolean[n+1][m+1][k+1];
        for (int i = 1; i <= n; i++) {
            String s = br.readLine();
            for (int j = 1; j <= m; j++) {
                arr[i][j] = s.charAt(j - 1);
            }
        }

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{1,1,0,k,1});
        visited[1][1][0] = true;
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            if(cur[0] == n && cur[1] == m) {
                System.out.println(cur[4]);
                return;
            }
            for(int i=0; i<4; i++) {
                int x = cur[0] + di[0][i];
                int y = cur[1] + di[1][i];
                if(x >= 1 && x<=n && y >= 1 && y<=m) {
                    if(cur[3] >= 1 && arr[x][y] == '1' && !visited[x][y][cur[2]+1]) {
                        visited[x][y][cur[2]+1] = true;
                        q.add(new int[]{x,y,cur[2]+1,cur[3]-1,cur[4]+1});
                    }else if(arr[x][y] == '0' && !visited[x][y][cur[2]]) {
                        visited[x][y][cur[2]] = true;
                        q.add(new int[]{x,y,cur[2],cur[3],cur[4]+1});
                    }
                }
            }
        }
        System.out.println(-1);

    }
}
