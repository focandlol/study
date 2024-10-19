package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10974 {
    static int n;
    static boolean[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        arr = new int[n];
        visited = new boolean[n+1];

        back(0);
        System.out.println(sb);

    }

    private static void back(int depth) {
        if(depth == n) {
            for (int i = 0; i < n; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=1; i<=n; i++){
            if(!visited[i]) {
                visited[i] = true;
                arr[depth] = i;
                back(depth+1);
                visited[i] = false;
            }
        }
    }
}
