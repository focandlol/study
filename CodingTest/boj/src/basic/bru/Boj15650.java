package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15650 {
    static int n;
    static int m;
    static boolean[] visited;
    static int[] dap;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        dap = new int[m];
        visited = new boolean[n+1];

        back(0,1);
        System.out.println(sb);


    }

    private static void back(int depth, int start) {
        if (depth == m) {
            for(int i=0; i<m; i++){
                sb.append(dap[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<=n; i++){
            if(!visited[i]){
                visited[i] = true;
                dap[depth] = i;
                back(depth+1, i+1);
                visited[i] = false;
            }
        }
    }
}
