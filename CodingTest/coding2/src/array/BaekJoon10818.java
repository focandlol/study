package array;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon10818 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        int max = -1000000;
        int min = 1000000;

        while(st.hasMoreTokens()) {
            int a = Integer.parseInt(st.nextToken());
            if(max < a) max = a;
            if(min > a) min = a;
        }

        System.out.println(min + " " + max);
    }
}
