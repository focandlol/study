package basic.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj17087 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++) {
            int a = Integer.parseInt(st.nextToken());
            list.add(Math.abs(s-a));
        }

        int gcd = list.get(0);
        for(int i=1; i<list.size(); i++) {
            gcd = getGcd(gcd,list.get(i));
        }
        System.out.println(gcd);

    }

    private static int getGcd(int a, int b) {
        if(a % b == 0){
            return b;
        }
        return getGcd(b, a % b);
    }
}
