package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon13305 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        long[] length = new long[n-1];
        long[] gap = new long[n];

        long maxLength = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n-1; i++) {
            length[i] = Integer.parseInt(st.nextToken());
            maxLength += length[i];
        }

        long minGap = Integer.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            gap[i] = Integer.parseInt(st.nextToken());
            if(i != n-1){
                minGap = Math.min(minGap, gap[i]);
            }
        }

        long dap = 0;
        for(int i = 0; i < n-1; i++){
            int a = 0;
            if(gap[i] != minGap){
                long leng = length[i];
                for(int j = i+1; j < n-1; j++){
                    if(gap[i] <= gap[j]){
                        leng += length[j];
                    }else{
                        a = j-1;
                        break;
                    }
                }
                dap += gap[i] * leng;
                maxLength -= leng;
                i = a;
            }else{
                dap += gap[i] * maxLength;
                break;
            }
        }

        System.out.println(dap);

    }
}
