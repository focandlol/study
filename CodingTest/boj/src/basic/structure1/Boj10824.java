package basic.structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj10824 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        StringBuilder sb  = new StringBuilder();
        sb.append(st.nextToken()).append(st.nextToken());
        long a = Long.parseLong(sb.toString());
        sb = new StringBuilder();
        sb.append(st.nextToken()).append(st.nextToken());
        long b = Long.parseLong(sb.toString());
        System.out.println(a+b);
    }
}
