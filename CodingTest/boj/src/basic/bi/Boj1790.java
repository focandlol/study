package basic.bi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1790 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long i = 9;
        long j = 1;
        while(k > i * j){
            k -= i * j;
            i = i * 10;
            j++;
        }

        k--;
        long num = (long) (Math.pow(10,(j-1)) + (k/j));
        if(num > n) {
            System.out.println(-1);
        }
        else {
            System.out.println(String.valueOf(num).charAt((int) (k % j)));
        }

    }
}