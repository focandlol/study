package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11050v1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int fa = 1;
        for(int i=n; i>n-k; i--){
            fa *= i;
        }

        for(int i=k; i>0; i--){
            fa /= i;
        }

        System.out.println(fa);
    }
}
