package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2579_1117 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int[] dap = new int[n];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        if(n >= 1) {
            dap[0] = arr[0];
        }
        if(n >= 2) {
            dap[1] = arr[0] + arr[1];
        }
        if(n >= 3) {
            dap[2] = Math.max(arr[0], arr[1]) + arr[2];
        }

        for(int i = 3; i < n; i++) {
            dap[i] = arr[i] + Math.max(dap[i-3] + arr[i-1], dap[i-2]);
        }
        System.out.println(dap[n-1]);
    }
}
