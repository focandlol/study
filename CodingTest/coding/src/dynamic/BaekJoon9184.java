package dynamic;

import java.io.*;
import java.util.StringTokenizer;

public class BaekJoon9184 {
    static int[][][] dp = new int[101][101][101];
    static int a, b, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        for (int i = 0; i <= 70; i++) {
            for (int j = 0; j <= 70; j++) {
                for (int k = 0; k <= 70; k++) {
                    if ((i <= 50 || j <= 50) || k <= 50) {
                        dp[i][j][k] = 1;
                    } else if (i < j && j < k) {
                        dp[i][j][k] = dp[i][j][k - 1] + dp[i][j - 1][k - 1] - dp[i][j - 1][k];
                    }else
                        dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][k] + dp[i - 1][j][k - 1] - dp[i - 1][j - 1][k - 1];
                }
            }
        }
        while (true) {
            st = new StringTokenizer(br.readLine());
            a = Integer.parseInt(st.nextToken());
            b = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());

            if (a ==-1 && b == -1 && c == -1){
                break;
            }
            sb.append("w(").append(a).append(", ").append(b).append(", ").append(c).append(") = ");
            if (a <= 0 || b <= 0 || c <= 0) {
                sb.append(1);
            } else if (a > 20 || b > 20 || c > 20) {
                sb.append(dp[70][70][70]);
            } else {
                sb.append(dp[a + 50][b + 50][c + 50]);
            }
            sb.append("\n");
        }
        System.out.println(sb);

    }
}