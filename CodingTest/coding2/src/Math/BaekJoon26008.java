package Math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon26008 {
    static final int na = 1000000007;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long a = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int h = Integer.parseInt(st.nextToken());

        System.out.println(find(m, n - 1));
    }

    private static long find(long a, int b) {
        long dap = 1;
        while (b > 0) {
            if (b % 2 == 1) {
                dap = (dap * a) % na;
            }
            b /= 2;
            a = (a * a) % na;
        }
        return dap;
    }
}