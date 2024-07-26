package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1932 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr =  new int[n][n];
        int[][] dap = new int[n][n];


        for(int i=n-1; i>=0; i--) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<n-i; j++) {
                int a = Integer.parseInt(st.nextToken());
                arr[j][i] = a;
            }
        }

        if(n == 1){
            System.out.println(arr[0][0]);
            return;
        }


        for(int i=1; i<n; i++) {
            for(int j=0; j<n-i; j++) {
                if(i == 1) {
                    dap[j][i] = arr[j][i] + Math.max(arr[j][i - 1], arr[j + 1][i - 1]);
                }else{
                    dap[j][i] = arr[j][i] + Math.max(dap[j][i - 1], dap[j + 1][i-1]);
                }
            }
        }


        System.out.println(dap[0][n-1]);

    }
}
