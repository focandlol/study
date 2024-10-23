package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15661 {
    static int[][] arr;
    static boolean[] visited;
    static int n;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        visited = new boolean[n + 1];
        arr = new int[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0, 1);
        System.out.println(min);
    }

    private static void find(int depth, int start) {
        if(depth > n/2){
            return;
        }
        if(depth >= 1){
            int dap = 0;
            for(int i=1; i<n; i++){
                for(int j=i+1; j<=n; j++){
                    if(visited[i] && visited[j]){
                        dap += arr[i][j] + arr[j][i];
                    }else if(!visited[i] && !visited[j]){
                        dap -= arr[i][j] + arr[j][i];
                    }
                }
            }
            min = Math.min(Math.abs(dap), min);
        }

        for(int i=start; i<=n; i++){
            if(!visited[i]){
                visited[i] = true;
                find(depth+1, i+1);
                visited[i] = false;
            }
        }
    }
}
