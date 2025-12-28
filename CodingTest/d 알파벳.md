```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {

    static int r;
    static int c;
    static Character[][] arr;
    static HashSet<Character> set;
    static int[][] di = {{0,1,0,-1},{1,0,-1,0}};
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        arr  = new Character[r+1][c+1];

        for(int i = 0; i<r; i++) {
            String s = br.readLine();
            for(int j=0; j<c; j++) {
                arr[i+1][j+1] = s.charAt(j);
            }
        }
        set = new HashSet<Character>();
        set.add(arr[1][1]);
        dfs(1,1,1);

        System.out.println(max);
    }

    static void dfs(int x, int y,int count) {
        boolean flag = false;
        for(int i=0; i<4; i++){
            int dx = x + di[0][i];
            int dy = y + di[1][i];
            if(dx >= 1 && dx<=r && dy >= 1 && dy<=c){
                if(set.add(arr[dx][dy])){
                    flag = true;
                    dfs(dx,dy,count+1);
                    set.remove(arr[dx][dy]);
                }
            }
        }
        if(!flag){
            max = Math.max(max,count);
        }
    }
}

```
