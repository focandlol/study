package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15655 {
    static int n;
    static int m;
    static int[] arr;
    static int[] dap;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dap = new int[m];
        visited = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
        back(0,0);
        System.out.println(sb);

    }

    private static void back(int depth, int start) {
        if(depth == m) {
            for(int i=0; i<m; i++){
                sb.append(dap[i]).append(" ");
            }
            sb.append('\n');
            return;
        }

        for(int i=start; i<n; i++) {
            if(!visited[i]) {
                visited[i] = true;
                dap[depth] = arr[i];
                back(depth+1, i+1);
                visited[i] = false;
            }
        }
    }
}
