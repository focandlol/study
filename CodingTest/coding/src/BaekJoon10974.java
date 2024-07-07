import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon10974
{
    static int[] arr;
    static int[] visited;
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        visited = new int[n];
        dfs(0);
    }

    private static void dfs(int depth) {
        if(depth == n){
            for(int i=0; i<n; i++){
                System.out.print(arr[i] + " ");
            }
            System.out.println();
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
