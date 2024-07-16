package bruteForce;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2231 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String a = br.readLine();
        int length = a.length();
        int n = Integer.parseInt(a);

        int dap = 0;

        for(int i= (n-(length*9)); i < n; i++){
            int num = i;
            int sum = 0;
            while(num != 0){
                sum += num % 10;
                num /= 10;
            }

            if(i + sum == n){
               dap = i;
               break;
            }
        }

        System.out.println(dap);
    }
}
