package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10819 {
    static int n;
    static int[] arr;
    static int[] dap;
    static boolean[] visited;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        dap = new int[n];
        visited = new boolean[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        back(0);
        System.out.println(max);
    }

    private static void back(int depth) {
        if(depth == n){
            int sum = 0;
            for(int i=0; i<n-1; i++){
                sum += Math.abs(dap[i] - dap[i+1]);
            }
            max = Math.max(sum, max);
            return;
        }

        for(int i=0; i<n; i++){
            if(!visited[i]){
                visited[i] = true;
                dap[depth] = arr[i];
                back(depth+1);
                visited[i] = false;
            }
        }
    }
}
