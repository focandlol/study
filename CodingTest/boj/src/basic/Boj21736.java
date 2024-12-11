package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj21736 {
    static int[][] di = {{1,0,-1,0},{0,1,0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        char[][] arr = new char[n][m];
        int idx = 0;
        int idy = 0;
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<m; j++){
                arr[i][j] = s.charAt(j);
                if(arr[i][j] == 'I'){
                    idx = i;
                    idy = j;
                }
            }
        }
        int dfs = dfs(n, m, idx, idy, new boolean[n][m], arr);
        if(dfs > 0){
            System.out.println(dfs);
        }else{
            System.out.println("TT");
        }

    }
    static int dfs(int n, int m, int idx, int idy,boolean visited[][], char[][] arr){
        visited[idx][idy] = true;
        int count = 0;
        if(arr[idx][idy] == 'P') count++;
        for(int i=0; i<4; i++){
            int dx = idx + di[0][i];
            int dy = idy + di[1][i];
            if(dx < n && dx >= 0 && dy < m && dy >= 0 && !visited[dx][dy] && arr[dx][dy] != 'X'){
                count += dfs(n,m,dx,dy,visited,arr);
            }
        }
        return count;
    }
}
