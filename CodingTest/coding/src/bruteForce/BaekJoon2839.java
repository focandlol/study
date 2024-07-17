package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2839 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        if(n < 5 && n%3 != 0){
            System.out.println("-1");
            return;
        }
        int mock = n/5;

        for(int i = mock; i>=0; i--){
            int na = n-(5*i);
            if(na == 0){
                System.out.println(i);
                return;
            }
            if(na % 3 == 0){
                System.out.println(i + na/3);
                return;
            }
            if(i==0 && n%3 != 0){
                System.out.println("-1");
            }
        }
    }
}
