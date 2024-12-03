package basic.dynamic.dp2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11060 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int[] dp = new int[n];
        Arrays.fill(dp,10000);
        dp[0] = 0;

        for(int i=0; i<n; i++){
            int a = arr[i];
            while(a > 0){
                if(i + a < n && dp[i + a] > dp[i] + 1){
                    dp[i + a] = dp[i] + 1;
                }
                a--;
            }
        }

        if(dp[n-1] >= 10000){
            System.out.println(-1);
        }else{
            System.out.println(dp[n-1]);
        }
    }
}
