package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj15666 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        find(arr,new int[m],0,m,0);
        System.out.println(sb);
    }

    private static void find(int[] arr, int[] dap, int depth,int m,int start) {
        if(depth == m){
            for(int i : dap){
                sb.append(i).append(" ");
            }
            sb.append("\n");
            return;
        }

        int prev = -1;
        for(int i=start; i<arr.length; i++){
            if(prev != arr[i]){
                prev = arr[i];
                dap[depth] = prev;
                find(arr,dap,depth+1,m,i);
            }
        }
    }
}
