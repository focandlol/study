package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj6603 {
    static int n;
    static int[] dap = new int[6];
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            if(n == 0) break;

            visited = new boolean[n];
            arr = new int[n];
            for(int i=0; i<n; i++){
                arr[i] = Integer.parseInt(st.nextToken());
            }

            back(0,0);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void back(int depth,int start) {
        if(depth == 6){
            for(int i=0; i<6; i++){
                sb.append(dap[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                dap[depth] = arr[i];
                back(depth+1, i+1);
                visited[i] = false;
            }
        }
    }
}
