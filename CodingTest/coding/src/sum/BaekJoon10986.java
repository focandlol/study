package sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon10986 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] arr = new long[n+1];
        long[] sum = new long[n+1];
        int[] na = new int[m];

        long count = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++){
            arr[i] = Long.parseLong(st.nextToken());
            sum[i] = sum[i-1] + arr[i];
            if(sum[i] % m == 0){
                na[0]++;
                count++;
            }else{
                na[(int) (sum[i] % m)]++;
            }
        }

        for(int i=0; i<na.length; i++){
            long ga = na[i];
            count += ga * (ga-1) / 2;
        }

        System.out.println(count);

    }
}
