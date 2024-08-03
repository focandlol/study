package divide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1780 {
    static int[][] arr;
    static int miCount = 0;
    static int zeCount = 0;
    static int plCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++){
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        find(0,0,n);
        sb.append(miCount).append("\n").append(zeCount).append("\n").append(plCount);
        System.out.println(sb);
    }

    private static void find(int x, int y, int n) {


        if(!check(x,y,n)){
            if(n == 1){
                return;
            }
            int a = n/3;
            find(x,y,a);
            find(x+a,y,a);
            find(x+2*a,y,a);
            find(x,y+a,a);
            find(x+a,y+a,a);
            find(x+2*a,y+a,a);
            find(x,y+2*a,a);
            find(x+a,y+2*a,a);
            find(x+2*a,y+2*a,a);
        }

    }

    private static boolean check(int x, int y, int n) {
        for(int i = y; i<y+n; i++) {
            for(int j = x; j<x+n; j++) {
                if(arr[x][y] != arr[j][i]){
                    return false;
                }
            }
        }
        if(arr[x][y] == 1){
            plCount++;
        }else if(arr[x][y] == -1){
            miCount++;
        }else{
            zeCount++;
        }
        return true;
    }
}

