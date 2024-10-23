package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14889 {
    static int[][] arr;
    static boolean[] visited;
    static int n;
    static int min = 100;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        visited = new boolean[n+1];
        arr = new int[n+1][n+1];
        for(int i=1; i<=n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(1,0);
        System.out.println(min);
    }

    private static void find(int index,int depth) {

        if(depth == n/2){
            int dap = 0;
            for(int i=1; i<=n; i++){
                for(int j=i+1; j<=n; j++){
                    if(visited[i] && visited[j]){
                        dap += arr[i][j] + arr[j][i];
                    }else if(!visited[i] && !visited[j]){
                        dap -= arr[i][j] + arr[j][i];
                    }
                }
            }
            min = Math.min(min,Math.abs(dap));
            return;
        }

        for(int i=index; i<=n; i++){
            visited[i] = true;
            find(i+1,depth+1);
            visited[i] = false;
        }
    }
}
