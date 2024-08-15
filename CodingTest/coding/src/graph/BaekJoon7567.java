package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BaekJoon7567 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[m][n];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        Deque<int[]> dq = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(arr[j][i] == 1){
                    dq.add(new int[]{j,i});
                }
            }
        }
        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        int max = 1;

        while(!dq.isEmpty()){
            int[] cur = dq.poll();
            for(int i=0; i<4; i++){
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];
                if(x >= 0 && y>=0 && x<m && y<n && arr[x][y] == 0){
                    dq.add(new int[]{x,y});
                    arr[x][y] = arr[cur[0]][cur[1]] + 1;
                    max = Math.max(max, arr[x][y]);
                }
            }
        }

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(arr[j][i] == 0){
                    System.out.println(-1);
                    return;
                }
            }
        }


        System.out.println(max - 1);


    }
}
