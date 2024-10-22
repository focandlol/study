package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14501 {
    static int n;
    static int[] day;
    static int[] gap;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        day = new int[n];
        gap = new int[n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            day[i] = Integer.parseInt(st.nextToken());
            gap[i] = Integer.parseInt(st.nextToken());
        }

        back(0,0);
        System.out.println(max);

    }

    private static void back(int start, int sum) {
        if(start > n-1){
            //System.out.println(sum);
            max = Math.max(max, sum);
            return;
        }

        for(int i=start; i<n; i++) {
            if(i + day[i] - 1 > n-1){
                back(i + day[i], sum);
            }else{
                back(i + day[i], sum + gap[i]);
            }
        }
    }
}
