package sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon25682 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] sum = new long[m+1][n+1];

        char temp = 'W';
        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            for(int j = 1; j <= m; j++) {
                if(a.charAt(j-1) == temp) {
                    sum[j][i] = sum[j-1][i] + 1;
                }else{
                    sum[j][i] = sum[j-1][i];
                }

                if(temp == 'B'){
                    temp = 'W';
                }else{
                    temp = 'B';
                }
            }
            if(m % 2 == 0){
                if(temp == 'B'){
                    temp = 'W';
                }else{
                    temp = 'B';
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                sum[j][i] = sum[j][i-1] + sum[j][i];
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i=k; i <= n; i++) {
            for(int j = k; j <= m; j++) {
                long dap = sum[j][i] - sum[j-k][i] - sum[j][i-k] + sum[j-k][i-k];
                min = (int) Math.min(min,Math.min(dap, k*k - dap));
            }
        }
        System.out.println(min);
    }
}
