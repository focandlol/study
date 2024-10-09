package basic.structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj11655 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<s.length(); i++) {
            if(Character.isUpperCase(s.charAt(i))) {
                if(s.charAt(i) + 13 > 'Z'){
                    sb.append((char)('A' + s.charAt(i) + 13 - 'Z' -1));
                }else{
                    sb.append((char)(s.charAt(i) + 13));
                }
            }else if(Character.isLowerCase(s.charAt(i))) {
                if(s.charAt(i) + 13 > 'z'){
                    sb.append((char)('a' + s.charAt(i) + 13 - 'z' -1));
                }else{
                    sb.append((char)(s.charAt(i) + 13));
                }
            }else{
                sb.append(s.charAt(i));
            }
        }
        System.out.println(sb);
    }
}
