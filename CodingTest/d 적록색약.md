```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int count = 0;
    static boolean[][] visited;
    static int[][] di = {{0,1,0,-1},{1,0,-1,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] common = new char[n][n];
        char[][] st = new char[n][n];

        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<n; j++){
                char c = s.charAt(j);
                common[i][j] = c;
                st[i][j] = c;
                if(c == 'G'){
                    st[i][j] = 'R';
                }
            }
        }

        visited = new boolean[n][n];
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    dfs(common,i,j);
                    count++;
                }
            }
        }
        sb.append(count).append(" ");
        visited = new boolean[n][n];
        count = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(!visited[i][j]){
                    dfs(st,i,j);
                    count++;
                }
            }
        }
        sb.append(count);
        System.out.println(sb);
    }

    static void dfs(char[][] arr, int i, int j) {
        visited[i][j] = true;
        for(int k=0; k<4; k++){
            int dx = i + di[0][k];
            int dy = j + di[1][k];
            if(dx >= 0 && dx < arr.length && dy >= 0 && dy < arr[0].length && !visited[dx][dy]){
                if(arr[i][j] == arr[dx][dy]){
                    dfs(arr,dx,dy);
                }
            }
        }
    }


}

```
