package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10971 {
    static int n;
    static int[][] arr;
    static boolean[] visited;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        visited = new boolean[n];
        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited[0] = true;
        back(0,0,0,0);
//        for(int i=0; i<n; i++) {
//            visited[i] = true;
//            back(0,i,i,0);
//        }
        System.out.println(min);
    }

    private static void back(int depth, int start, int now, int sum) {
        if(depth == n-1){
            //System.out.println(sum);
            if(arr[now][start] == 0){
                return;
            }else {
                min = Math.min(min, sum + arr[now][start]);
            }
            return;
        }

        for(int i=0; i<n; i++) {
            if(arr[now][i] != 0 && !visited[i]) {
                //System.out.println(now + " " + i + " " + depth + " " + sum);
                visited[i] = true;
                int dap = sum + arr[now][i];
                back(depth + 1, start, i, dap);
                visited[i] = false;
            }
        }
    }
}
