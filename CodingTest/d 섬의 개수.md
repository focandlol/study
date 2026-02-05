```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] arr;
    static boolean[][] visited;
    static int[][] di = {{0,1,1,1,0,-1,-1,-1},{-1,-1,0,1,1,1,0,-1}};
    static int w;
    static int h;
    static int count;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        while(true){
            count = 0;
            st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());

            arr = new int[h][w];
            visited = new boolean[h][w];
            if(w == 0 && h == 0) break;
            for(int i=0; i<h; i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<w; j++){
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<h; i++){
                for(int j=0; j<w; j++){
                    if(!visited[i][j] && arr[i][j] == 1){
                        dfs(i,j);
                        count++;
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int x, int y) {
        visited[x][y] = true;
        for(int i=0; i<8; i++){
            int dx = x+di[0][i];
            int dy = y+di[1][i];
            if(dx >= 0 && dx < h && dy >= 0 && dy < w && !visited[dx][dy] && arr[dx][dy] == 1){
                dfs(dx,dy);
            }
        }
    }
}
```
