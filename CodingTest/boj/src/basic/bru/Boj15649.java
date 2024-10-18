package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj15649 {
    static int n;
    static int m;
    static StringBuilder sb = new StringBuilder();
    static boolean[] visited;
    static int[] dap;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new boolean[n+1];
        dap = new int[m];
        back(0);
        System.out.println(sb.toString());
    }

    private static void back(int depth) {
        if(depth == m){
            for(int i=0; i<m; i++){
                sb.append(dap[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i=1; i<=n; i++){
            if(!visited[i]){
                visited[i] = true;
                dap[depth] = i;
                back(depth+1);
                visited[i] = false;
            }
        }
    }
}
