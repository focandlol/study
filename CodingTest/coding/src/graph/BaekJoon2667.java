package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BaekJoon2667 {
    static int[][] visited;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};
    static int n;
    static int[][] arr;
    static int count = 0;
    static int num = 0;
    static ArrayList<Integer> dap = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n+1][n+1];
        visited = new int[n+1][n+1];

        for(int i=1; i<=n; i++){
            String s = br.readLine();
            for(int j=1; j<=n; j++){
                arr[j][i] = s.charAt(j-1) - '0';
            }
        }

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(arr[j][i] == 1 && visited[j][i] == 0){
                    dfs(j,i);
                    num++;
                    dap.add(count);
                    count = 0;
                }
            }
        }

        Collections.sort(dap);
        StringBuilder sb = new StringBuilder();
        sb.append(num).append("\n");
        for(int i=0; i<dap.size(); i++){
            sb.append(dap.get(i)).append("\n");
        }

        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = 1;
        count++;

        for(int i=0; i<4; i++){
            int newX = x + dx[i];
            int newY = y + dy[i];
            if(newX >=0 && newY >= 0 && newX<=n && newY <= n && visited[newX][newY] == 0 && arr[newX][newY] == 1){
                dfs(newX, newY);
            }
        }

    }
}
