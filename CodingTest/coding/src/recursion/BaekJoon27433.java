package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon27433 {
    static long count = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        if(n == 0){
            System.out.println(1);
            return;
        }

        find(n);
    }

    private static void find(int n) {
        if(n == 1){
            System.out.println(count);
            return;
        }
        count *= n;
        find(n-1);
    }
}
