package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1389 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if(i == j){
                    arr[i][j] = 0;
                }else{
                    arr[i][j] = 500001;
                }
            }
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        for(int k=1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    arr[i][j] = Math.min(arr[i][j],arr[i][k] + arr[k][j]);
                }
            }
        }


        int min = Integer.MAX_VALUE;
        int minIdx = 0;
        for(int i=1; i<=n; i++){
            int sum = 0;
            for(int j=1; j<=n; j++){
                sum += arr[i][j];
            }
            if(min > sum){
                min = sum;
                minIdx = i;
            }
        }

        System.out.println(minIdx);
    }
}
