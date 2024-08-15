package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BaekJoon7569 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        int[][][] arr = new int[m][n][h];
        Deque<int[]> dq = new ArrayDeque<>();
        for(int i=0; i<h; i++){
            for(int j=0; j<n; j++){
                st = new StringTokenizer(br.readLine());
                for(int k=0; k<m; k++){
                    int i1 = Integer.parseInt(st.nextToken());
                    arr[k][j][i] = i1;
                    if(i1 == 1){
                        dq.add(new int[]{k,j,i});
                    }
                }
            }
        }

        int max = 1;
        int[] dx = {1,0,-1,0,0,0};
        int[] dy = {0,1,0,-1,0,0};
        int[] dz = {0,0,0,0,1,-1};
        while(!dq.isEmpty()){
            int[] poll = dq.poll();
            for(int i=0; i<6; i++){
                int x = poll[0] + dx[i];
                int y = poll[1] + dy[i];
                int z = poll[2] + dz[i];
                if(x>=0 && y>=0 && z>=0 && x<m && y<n && z<h && arr[x][y][z]==0){
                    dq.add(new int[]{x,y,z});
                    arr[x][y][z] = arr[poll[0]][poll[1]][poll[2]] + 1;
                    max = Math.max(max, arr[x][y][z]);
                }
            }
        }

        for(int i=0; i<h; i++){
            for(int j=0; j<n; j++){
                for(int k=0; k<m; k++){
                    if(arr[k][j][i]==0){
                        System.out.println(-1);
                        return;
                    }
                }
            }
        }
        System.out.println(max-1);


    }
}
