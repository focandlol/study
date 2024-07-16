package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2798 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] dap = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int max = 0;
        for(int i = 0; i < n-2; i++) {
            dap[0] = arr[i];
            for(int j = i+1; j < n-1; j++) {
                dap[1] = arr[j];
                for(int k = j+1; k < n; k++) {
                    dap[2] = arr[k];
                    if(dap[0] + dap[1] + dap[2] > max && dap[0] + dap[1] + dap[2] <= m) {
                        max = dap[0] + dap[1] + dap[2];
                    }
                }
            }
        }

        System.out.println(max);




    }
}
