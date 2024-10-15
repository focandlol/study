package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1476 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int e = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int se = 1;
        int ss = 1;
        int sm = 1;
        int count = 1;

        while(true) {
            if(e==se && s==ss && m==sm) break;
            se = (se+1) % 15 == 0 ? 15 : (se + 1) % 15;
            ss = (ss+1) % 28 == 0 ? 28 : (ss + 1) % 28;
            sm = (sm+1) % 19 == 0 ? 19 : (sm + 1) % 19;
            count++;
        }

        System.out.println(count);
    }
}
