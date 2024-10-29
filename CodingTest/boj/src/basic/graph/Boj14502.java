package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj14502 {
    static int n;
    static int m;
    static int[][] arr;
    static boolean[][] visited;
    static int[][] dis = {{0,1,0,-1},{1,0,-1,0}};
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back(0);
        System.out.println(max);
    }

    private static void back(int count) {
        if(count == 3){
            int dap = 0;
            Queue<int[]> queue = new LinkedList<>();
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(!visited[i][j] && arr[i][j] == 2){
                        visited[i][j] = true;
                        queue.add(new int[]{i, j});
                    }
                }
            }
            while(!queue.isEmpty()){
                int[] cur = queue.poll();
                for(int k=0; k<4; k++){
                    int dx = cur[0] + dis[0][k];
                    int dy = cur[1] + dis[1][k];
                    if(dx >=0 && dx < n && dy >= 0 && dy < m && arr[dx][dy] != 1 && !visited[dx][dy]){
                        visited[dx][dy] = true;
                        queue.add(new int[]{dx,dy});
                    }
                }
            }
            for(int i=0; i<n; i++){
                for(int j=0; j<m; j++){
                    if(!visited[i][j] && arr[i][j] == 0){
                        dap++;
                    }
                }
            }
            max = Math.max(dap, max);
            visited = new boolean[n][m];
            return;
        }

        for(int i=0; i<n; i++) {
            for(int j=0; j<m; j++) {
                if(arr[i][j] == 0) {
                    arr[i][j] = 1;
                    back(count + 1);
                    arr[i][j] = 0;
                }
            }
        }
    }
}
