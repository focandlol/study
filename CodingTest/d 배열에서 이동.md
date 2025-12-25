```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[][] arr;
    static int min = 2010;
    static int max = 0;
    static int[][] di = {{0,1,0,-1},{1,0,-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, arr[i][j]);
                min = Math.min(min, arr[i][j]);
            }
        }

        int left = 0;
        int right = max - min + 1;
        int dap = 20100000;
        while(left < right){
            int mid = (left + right) / 2;
            boolean flag = false;

            for(int i = min; i <= max; i++){
                if(i <= arr[0][0] && arr[0][0] <= i + mid){
                    if(flag = bfs(i,i + mid)) {
                        break;
                    }
                }
            }
            if (flag) {
                right = mid;
                dap = Math.min(dap, mid);    // 경로가 있으면 차이를 정답에 반영
            } else
                left = mid + 1;
        }
        System.out.println(dap);
    }

    private static boolean bfs(int min, int max) {
        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[]{0, 0});
        boolean[][] visited = new boolean[n][n];
        visited[0][0] = true;
        while(!q.isEmpty()){
            int[] cur = q.poll();
            if(cur[0] == n-1 && cur[1] == n-1){
                return true;
            }
            for(int i=0; i<4; i++){
                int x = cur[0] + di[0][i];
                int y = cur[1] + di[1][i];
                if(x >= 0 && x < n && y >= 0 && y < n && !visited[x][y]){
                    if(arr[x][y] >= min && arr[x][y] <= max){
                        visited[x][y] = true;
                        q.add(new int[]{x, y});
                    }
                }
            }
        }
        return false;
    }
}


```
