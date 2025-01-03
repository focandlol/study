package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14938 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n+1][n+1];
        int[] gap = new int[n+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i != j){
                    arr[i][j] = 1000000;
                }
            }
        }

        st = new StringTokenizer(br.readLine());
        for(int i=1; i<=n; i++){
            gap[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());

            arr[a][b] = l;
            arr[b][a] = l;
        }

        for(int k = 1; k<=n; k++){
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    arr[i][j] = Math.min(arr[i][j],arr[i][k] + arr[k][j]);
                }
            }
        }

        int max = 0;
        for(int i=1; i<=n; i++){
            int sum = 0;
            for(int j=1; j<=n; j++){
                if(arr[i][j] <= m){
                    sum += gap[j];
                }
            }
            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}
