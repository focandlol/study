package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] dap = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dap[0] = arr[0];

        for(int i=1; i<n; i++) {
            if(dap[i-1] < 0){
                dap[i] = arr[i];
            }else{
                dap[i] = dap[i-1] + arr[i];
            }
        }
        Arrays.sort(dap);

        System.out.println(dap[n-1]);
    }
}
