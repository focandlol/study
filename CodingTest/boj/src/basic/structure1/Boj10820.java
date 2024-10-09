package basic.structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj10820 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[4];
        StringBuilder sb = new StringBuilder();

        String s = null;
        while((s = br.readLine()) != null){
            for(int i=0; i<s.length(); i++){
                if(s.charAt(i)==' '){
                    arr[3]++;
                }else if(Character.isUpperCase(s.charAt(i))){
                    arr[1]++;
                }else if(Character.isLowerCase(s.charAt(i))){
                    arr[0]++;
                }else{
                    arr[2]++;
                }
            }
            for(int j=0; j<arr.length; j++){
                sb.append(arr[j]).append(" ");
                arr[j] = 0;
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
