package sum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon16139 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        String s = br.readLine();
        int q = Integer.parseInt(br.readLine());

        int[][] count = new int[s.length()+1][26];

        for(int i=1; i<=s.length(); i++){
            for(int j=0; j<26; j++){
                int num = s.charAt(i-1) - 'a';
                if(j == num){
                    count[i][j] = count[i-1][j] + 1;
                }else{
                    count[i][j] = count[i-1][j];
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0;i<q;i++){
            st = new StringTokenizer(br.readLine());
            int a = st.nextToken().charAt(0) - 'a';
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());

            sb.append(count[r+1][a]-count[l][a]).append("\n");
        }
        System.out.println(sb);

    }
}
