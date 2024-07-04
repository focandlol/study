package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11726TopDown {
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        arr = new long[n+1];
        arr[1] = 1;
        if(n>1) {
            arr[2] = 2;
        }

        long dap = dap(n);
        System.out.println(dap);
    }

    private static long dap(int n) {
        if(arr[n] != 0){
            return arr[n] % 10007;
        }
        return arr[n] = (dap(n-1) + dap(n-2)) % 10007;
    }
}
