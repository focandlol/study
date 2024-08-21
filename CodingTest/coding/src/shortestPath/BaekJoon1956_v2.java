package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1956_v2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        long[][] arr = new long[v+1][v+1];
        for(int i = 1; i <= v; i++){
            for(int j = 1; j <= v; j++){
                if(i == j){
                    arr[j][i] = 0;
                }else{
                    arr[j][i] = Integer.MAX_VALUE;
                }
            }
        }


        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if(arr[a][b] > c){
                arr[a][b] = c;
            }
        }

        for(int k=1; k<=v; k++){
            for(int s=1; s<=v; s++){
                for(int end=1; end<=v; end++){
                    arr[s][end] = Math.min(arr[s][end],arr[s][k] + arr[k][end]);
                }
            }
        }

        long dap = Integer.MAX_VALUE;

        for(int i=1; i<=v; i++){
            for(int j=1; j<=v; j++){
                if(i != j && arr[j][i] != Integer.MAX_VALUE && arr[i][j] != Integer.MAX_VALUE){
                    dap = Math.min(dap, arr[j][i] + arr[i][j]);
                }
            }
        }

        if(dap == Integer.MAX_VALUE){
            System.out.println("-1");
        }else{
            System.out.println(dap);
        }



    }
}

