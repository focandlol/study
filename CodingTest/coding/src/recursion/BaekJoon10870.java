package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon10870 {
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        arr = new long[21];
        arr[0] = 0;
        arr[1] = 1;

        if(n == 0 || n == 1){
            System.out.println(arr[n]);
            return;
        }
        System.out.println(find(n));
    }

    private static long find(int n) {
        if(arr[n] != 0){
            return arr[n];
        }
        if (n == 0) {
            return arr[0];
        }
        return arr[n] = find(n-1) + find(n-2);
    }
}
