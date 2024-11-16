package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj12904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String t = br.readLine();

        StringBuilder sb = new StringBuilder(t);
        for(int i=sb.length()-1; i>=0; i--) {
            if(sb.charAt(i)=='A') {
                sb.deleteCharAt(i);
            }else{
                sb.deleteCharAt(i);
                sb.reverse();
            }
            if(sb.toString().equals(s)) {
                System.out.println(1);
                return;
            }
        }
        System.out.println(0);
    }
}
