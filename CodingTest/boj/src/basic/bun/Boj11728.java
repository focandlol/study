package basic.bun;

import javax.swing.plaf.basic.BasicButtonUI;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11728 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] arr2 = new int[m];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }

        int a = 0;
        int b = 0;

        while(a < n && b < m) {
            if(arr[a] > arr2[b]) {
                sb.append(arr2[b++]).append(" ");
            }else{
                sb.append(arr[a++]).append(" ");
            }
        }

        if(a < n){
            while(a < n){
                sb.append(arr[a++]).append(" ");
            }
        }else if(b < m){
            while(b < m){
                sb.append(arr2[b++]).append(" ");
            }
        }

        System.out.println(sb);

    }
}
