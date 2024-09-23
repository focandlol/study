package HashTable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon10807 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        HashMap<Integer,Integer> map = new HashMap<>();
        for(int i=0; i<n; i++) {
            int key = Integer.parseInt(st.nextToken());
            if(map.containsKey(key)){
                map.replace(key, map.get(key)+1);
            }else{
                map.put(key, 1);
            }
        }

        st = new StringTokenizer(br.readLine());
        int a = Integer.parseInt(st.nextToken());
        if(map.containsKey(a)){
            System.out.println(map.get(a));
        }else{
            System.out.println(0);
        }
    }
}
