import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon10974Refactoring {
    static int[] arr;
    static int[] visited;
    static int n;
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new int[n];
        dfs(0);
        System.out.println(sb);
    }

    private static void dfs(int depth) {
        if(depth == n){
            for(int i=0; i<n; i++){
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i=0; i<n; i++){
            if(visited[i] == 0) {
                arr[depth] = i + 1;
                visited[i] = 1;
                dfs(depth + 1);
                visited[i] = 0;
            }
        }
    }
}
