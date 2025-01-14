package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14499 {
    static int[] ju;
    static int x;
    static int y;
    static int n;
    static int m;
    static int[][] di = {{0,0,-1,1},{1,-1,0,0}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        ju = new int[7];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<k; i++){
            int a = Integer.parseInt(st.nextToken());
            int dx = x + di[0][a-1];
            int dy = y + di[1][a-1];
            if(dx <  0 || dy < 0 || dx >= n || dy >= m){
                continue;
            }

            roll(a);

            x = dx;
            y = dy;

            if(arr[x][y] == 0){
                arr[x][y] = ju[6];
            }else{
                ju[6] = arr[x][y];
                arr[x][y] = 0;
            }
            sb.append(ju[1]).append("\n");

        }

        System.out.println(sb);


    }

    private static void roll(int a) {
        if(a == 1){
            int temp = ju[1];
            ju[1] = ju[3];
            ju[3] = ju[6];
            ju[6] = ju[4];
            ju[4] = temp;
        }else if(a == 2){
            int temp = ju[1];
            ju[1] = ju[4];
            ju[4] = ju[6];
            ju[6] = ju[3];
            ju[3] = temp;
        }else if(a == 3){
            int temp = ju[1];
            ju[1] = ju[5];
            ju[5] = ju[6];
            ju[6] = ju[2];
            ju[2] = temp;
        }else{
            int temp = ju[1];
            ju[1] = ju[2];
            ju[2] = ju[6];
            ju[6] = ju[5];
            ju[5] = temp;
        }
    }
}
