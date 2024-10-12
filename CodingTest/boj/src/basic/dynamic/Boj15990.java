package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj15990 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MOD = 1000000009;
        StringBuilder sb = new StringBuilder();
        long[][] arr = new long[100001][4];
        arr[1][1] = 1;
        arr[2][2] = 1;
        arr[3][1] = 1;
        arr[3][2] = 1;
        arr[3][3] = 1;

        for (int i = 4; i <= 100000; i++) {
            arr[i][1] = (arr[i - 1][2] + arr[i - 1][3]) % MOD;
            arr[i][2] = (arr[i - 2][1] + arr[i - 2][3]) % MOD;
            arr[i][3] = (arr[i - 3][1] + arr[i - 3][2]) % MOD;
        }

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            int a = Integer.parseInt(br.readLine());
            sb.append((arr[a][1] + arr[a][2] + arr[a][3]) % MOD).append("\n");
        }

        System.out.println(sb);
    }
}
