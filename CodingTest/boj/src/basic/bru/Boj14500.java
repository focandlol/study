package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14500 {
    static int[] dx = {0,1,0,-1};
    static int[] dy = {-1,0,1,0};
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                visited[i][j] = true;
                dfs(i,j,arr[i][j],1);
                visited[i][j] = false;
            }
        }

        System.out.println(max);
    }

    private static void dfs(int i, int j, int sum, int count) {

        if(count == 4){
            max = Math.max(max,sum);
            return;
        }

        for(int k=0; k<4; k++){
            int x = i+dx[k];
            int y = j+dy[k];

            if(x >= 0 && x < n && y >= 0 && y<m && !visited[x][y]){

                if(count == 2){
                    visited[x][y] = true;
                    dfs(i,j,sum + arr[x][y],count+1);
                    visited[x][y] = false;
                }

                visited[x][y] = true;
                dfs(x,y,sum + arr[x][y],count + 1);
                visited[x][y] = false;
            }
        }
    }
}
