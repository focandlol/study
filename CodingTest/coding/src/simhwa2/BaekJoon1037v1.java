package simhwa2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1037v1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int max = 0;
        int min = 1000001;
        for(int i=0; i<n; i++) {
            int a = Integer.parseInt(st.nextToken());
            if(a > max){
                max = a;
            }
            if(a < min){
                min = a;
            }

        }
        System.out.println(max*min);
    }
}
