package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10610 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int[] arr = new int[10];
        int sum = 0;
        for(int i=0; i<s.length(); i++) {
            int a = s.charAt(i) - '0';
            arr[a]++;
            sum += a;
        }

        if(arr[0] == 0 || sum % 3 != 0){
            System.out.println(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for(int i=9; i>=0; i--) {
            while(arr[i] > 0){
                sb.append(i);
                arr[i]--;
            }
        }
        System.out.println(sb);
    }
}
