package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BaekJoon2206 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] arr = new int[m+1][n+1];

        for(int i=1; i<=n; i++){
            String s = br.readLine();
            for(int j=1; j<=m; j++){
                arr[j][i] = s.charAt(j-1)-'0';
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{1,1,1,0});
        int[][][] visited = new int[m+1][n+1][2];

        int[] dx = {0,-1,0,1};
        int[] dy = {1,0,-1,0};
        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            if(cur[0] == m && cur[1] == n){
                System.out.println(cur[2]);
                return;
            }
            for(int i=0; i<4; i++){
                int x = cur[0]+dx[i];
                int y = cur[1]+dy[i];
                if(x>=1 && y>=1 && x<=m && y<=n){
                    if(arr[x][y] == 1){
                        if(cur[3] == 0){
                            dq.add(new int[]{x,y,cur[2] + 1,1});
                            visited[x][y][1] = cur[2] + 1;
                        }
                    }
                    else if(arr[x][y] == 0){
                        if(cur[3] == 0 && visited[x][y][0] == 0){
                            dq.add(new int[]{x,y,cur[2] + 1,cur[3]});
                            visited[x][y][0] = cur[2] + 1;
                        }else if(cur[3] == 1 && visited[x][y][1] == 0){
                            dq.add(new int[]{x,y,cur[2] + 1,cur[3]});
                            visited[x][y][1] = cur[2] + 1;
                        }
                    }
                }
            }
        }

        System.out.println(-1);



    }
}
