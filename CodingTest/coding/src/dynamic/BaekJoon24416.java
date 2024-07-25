package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon24416 {
    static int[] arr;
    static int dyCount = 0;
    static int reCount = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        arr = new int[n + 1];
        arr[1] = 1;
        arr[2] = 1;
        recur(n);
        dynamic(n);

        System.out.println(reCount + " " + dyCount);

    }

    private static int dynamic(int n) {
        for(int i=3; i<=n; i++){
            arr[i] = arr[i-1] + arr[i-2];
            dyCount++;
        }
        return arr[n];
    }

    private static int recur(int n) {
        if(n==1 || n==2) {
            reCount++;
            return 1;
        }else{
            return recur(n-1)+recur(n-2);
        }
    }
}
