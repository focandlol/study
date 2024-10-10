package basic.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj9613 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            long count = 0;
            ArrayList<Long> list = new ArrayList<>();
            for(int j=0; j<n; j++) {
                list.add(Long.parseLong(st.nextToken()));
            }
            for(int k=0; k<list.size(); k++) {
                for(int b=k+1; b<list.size(); b++) {
                    count += getGcd(list.get(k),list.get(b));
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static long getGcd(long k, long b) {
        if(k % b == 0) {
            return b;
        }
        return getGcd(b, k % b);
    }
}
