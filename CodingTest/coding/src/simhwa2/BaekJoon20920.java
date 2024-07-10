package simhwa2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon20920 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        HashMap<String, Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            String a = br.readLine();
            if(a.length() >= m) {
                if (map.containsKey(a)) {
                    map.put(a, map.get(a) + 1);
                } else {
                    map.put(a, 1);
                }
            }
        }

        ArrayList<String> list = new ArrayList<>(map.keySet());
        list.sort((o1, o2) -> {
            if(map.get(o1) > map.get(o2)) {
                return -1;
            }else if(map.get(o1) < map.get(o2)) {
                return 1;
            }else{
                if(o1.length() > o2.length()) {
                    return -1;
                }else if(o1.length() < o2.length()) {
                    return 1;
                }else{
                    return dic(o1,o2);
                }
            }
        });

        for(int i=0; i<list.size(); i++) {
            sb.append(list.get(i)).append("\n");
        }
        System.out.println(sb);
    }

    private static int dic(String o1, String o2) {
        for(int i=0; i<o1.length(); i++) {
            if(o1.charAt(i) != o2.charAt(i)) {
                return o1.charAt(i) - o2.charAt(i);
            }
        }
        return 1;
    }
}
