package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj15686 {
    static int[] dap;
    static int minDap = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dap = new int[m];

        int[][] arr = new int[n+1][n+1];

        ArrayList<int[]> list = new ArrayList<>();

        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 2){
                    list.add(new int[]{i,j});
                }
            }
        }

        find(arr,list,0,m,new boolean[list.size()],0);
        System.out.println(minDap);
    }

    private static void find(int[][] arr, ArrayList<int[]> list, int depth,int m, boolean[] visited, int start) {
        if(depth == m){
            Queue<int[]> queue = new LinkedList<>();
            boolean[][] visit = new boolean[arr.length][arr.length];
            for(int k : dap){
                int[] a = list.get(k);
                queue.add(new int[]{a[0], a[1], 0});
                visit[a[0]][a[1]] = true;
            }
            //System.out.println("queue.size = " + queue.size());
            bfs(queue,arr,visit);
            return;
        }

        for(int i=start; i<list.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                //dap.add(new int[]{list.get(i)[0], list.get(i)[1]});
                dap[depth] = i;
                find(arr,list,depth+1,m,visited,i+1);
                visited[i] = false;
            }
        }
    }

    private static void bfs(Queue<int[]> queue, int[][] arr, boolean[][] visited) {
        int[][] di = new int[][]{{0,1,0,-1},{1,0,-1,0}};
        int count = 0;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            //System.out.println("cur[0] = " + cur[0] + ", cur[1] = " + cur[1] + "cur[2] = " + cur[2]);
            if(arr[cur[0]][cur[1]] == 1){
                count += cur[2];
            }

            for(int i=0; i<4; i++){
                int x = cur[0] + di[0][i];
                int y = cur[1] + di[1][i];
                if(x >= 1 && x < arr.length && y >= 1 && y < arr[0].length && !visited[x][y]){
                    visited[x][y] = true;
                    queue.add(new int[]{x,y,cur[2] + 1});
                }
            }
        }
        //System.out.println("count =" + count);
        minDap = Math.min(count,minDap);
    }
}
