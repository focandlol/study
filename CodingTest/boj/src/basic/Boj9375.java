package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj9375 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<t; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            for(int j=0; j<n; j++){
                st = new StringTokenizer(br.readLine());
                String a = st.nextToken();
                String b = st.nextToken();
                map.put(b,map.getOrDefault(b,0)+1);
            }


            int dap = 1;
            for(int k : map.values()){
                dap *= (k+1);
            }
            sb.append(dap -1).append("\n");
        }
        System.out.println(sb);
    }
}
