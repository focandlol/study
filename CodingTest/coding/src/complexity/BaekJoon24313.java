package complexity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon24313 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int a1 =  Integer.parseInt(st.nextToken());
        int a0 =  Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int c = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int n0 = Integer.parseInt(st.nextToken());

        if (a1*n0 + a0 <= c*n0 && c >= a1) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }




    }
}
