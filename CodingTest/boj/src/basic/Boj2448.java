package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj2448 {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n][2*n-1];

        find(0,n-1,n);

        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++){
            for(int j=0; j<2*n-1; j++){
                if(arr[i][j] == 0){
                    sb.append(" ");
                }else{
                    sb.append("*");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    static void find(int a, int b, int n){
        if(n == 3){
            arr[a][b] = 1;
            arr[a+1][b-1] = arr[a+1][b+1] = 1;
            for(int i=-2; i<=2; i++){
                arr[a+2][b+i] = 1;
            }
            return;
        }

        int size = n/2;
        find(a,b,size);
        find(a + size, b-size, size);
        find(a+size, b+size, size);
    }
}
