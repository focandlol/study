package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj1748 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        long dap = 0;
        long gop = 1;
        long start = 9;

        while(n >= start){
            dap += start * gop;
            n -= start;
            gop++;
            start *= 10;
        }

        dap += n * gop;
        System.out.println(dap);
    }
}
