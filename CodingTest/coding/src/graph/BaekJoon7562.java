package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class BaekJoon7562 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int[][] visited;
        int[] ddx = {-2, -2, -1,1,2,2,-1,1};
        int[] ddy = {1,-1,-2,-2,-1,1,2,2};

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            visited = new int[l][l];

            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int dx = Integer.parseInt(st.nextToken());
            int dy = Integer.parseInt(st.nextToken());

            Deque<int[]> dq = new ArrayDeque<>();
            dq.add(new int[]{x,y});
            while(!dq.isEmpty()){
                int[] cur = dq.poll();

                if(cur[0] == dx && cur[1] == dy){
                    sb.append(visited[cur[0]][cur[1]]).append("\n");
                    break;
                }
                for(int j=0; j<8; j++){
                    int cx = cur[0] + ddx[j];
                    int cy = cur[1] + ddy[j];

                    if(cx >=0 && cy >= 0 && cx < l && cy < l && visited[cx][cy] == 0){
                        dq.add(new int[]{cx,cy});
                        visited[cx][cy] = visited[cur[0]][cur[1]] + 1;
                    }
                }

            }

        }
        System.out.println(sb);

    }
}
