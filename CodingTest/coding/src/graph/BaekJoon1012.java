package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1012 {
    static int[][] arr;
    static int[][] visited;
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int m;
    static int n;
    static int k;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            m = Integer.parseInt(st.nextToken());
            n = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            arr = new int[m][n];
            visited = new int[m][n];
            for(int j=0; j<k; j++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                arr[x][y] = 1;
            }

            int count = 0;
            for(int q=0; q<n; q++) {
                for(int r=0; r<m; r++) {
                    if(arr[r][q] == 1 && visited[r][q] == 0) {
                        dfs(r, q);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = 1;
        for(int i=0; i<4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx>=0 && ny>=0 && nx<m && ny<n && visited[nx][ny] == 0 && arr[nx][ny] == 1) {
                dfs(nx, ny);
            }
        }
    }
}
