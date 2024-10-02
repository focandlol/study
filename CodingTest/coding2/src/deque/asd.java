package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class asd {
    static int n;
    static int m;
    static int[] visited;
    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        visited = new int[n+1];
        arr = new int[m];
        find(1,0);

        System.out.println(sb);

    }

    private static void find(int start,int depth) {

        if(depth == m){
            for(int i=0; i<arr.length; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=start; i<=n; i++){
            if(visited[i] == 1){
                continue;
            }
            visited[i] = 1;
            arr[depth] = i;
            find(i + 1,depth + 1);
            visited[i] = 0;

        }
    }

}
