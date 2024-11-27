package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj1620 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        HashMap<String, Integer> nameMap = new HashMap<>();
        HashMap<Integer, String> numMap = new HashMap<>();

        for(int i=1; i<=n; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            nameMap.put(name,i);
            numMap.put(i,name);
        }

        StringBuilder sb = new StringBuilder();
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            if(nameMap.containsKey(name)) {
                sb.append(nameMap.get(name)).append("\n");
            }else{
                sb.append(numMap.get(Integer.parseInt(name))).append("\n");
            }
        }
        System.out.println(sb);
    }
}
