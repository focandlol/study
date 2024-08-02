package divide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2630 {
    static int[][] arr;
    static int count = 0;
    static int blueCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        arr = new int[n][n];
        for(int i = 0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<n; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        find(0,0,n);
        System.out.println(count);
        System.out.println(blueCount);
    }

    private static void find(int x, int y, int n) {

        if(!check(x,y,n)){
            if(n == 1){
                return;
            }
            int a = n/2;
            find(x,y,a);
            find(x+a,y,a);
            find(x,y+a,a);
            find(x+a,y+a,a);
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
            blueCount++;
        }else{
            count++;
        }
        return true;
    }

}
