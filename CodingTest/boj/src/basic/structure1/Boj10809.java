package basic.structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj10809 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] al = new int[26];
        Arrays.fill(al,-1);

        for(int i = 0; i < s.length(); i++) {
            if(al[s.charAt(i)-'a'] == -1) al[s.charAt(i) - 'a'] = i;
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 26; i++) {
            sb.append(al[i]).append(" ");
        }

        System.out.println(sb);

    }
}
