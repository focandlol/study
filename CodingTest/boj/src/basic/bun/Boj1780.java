package basic.bun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1780 {
    static int n;
    static int[][] arr;
    static int[] dap = new int[3];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        find(0,0,n,true);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<3; i++) {
            sb.append(dap[i]).append("\n");
        }
        System.out.println(sb);
    }

    private static void find(int x, int y, int size, boolean flag) {
        for(int i=x; i<x+size; i++) {
            for(int j=y; j<y+size; j++) {
                if(arr[i][j] != arr[x][y]){
                    flag = false;
                }
            }
        }
        if(flag) {
            if(arr[x][y] == -1){
                dap[0]++;
            }else if(arr[x][y] == 0){
                dap[1]++;
            }else{
                dap[2]++;
            }
            return;
        }

        int a = size/3;
        for(int i=x; i<x+size; i+=a) {
            for(int j=y; j<y+size; j+=a) {
                find(i,j,a,true);
            }
        }
    }
}
