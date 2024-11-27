package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj11723 {
    static Set<Integer> set = new HashSet<Integer>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<Integer>();
        for(int i=1; i<=20; i++){
            list.add(i);
        }

        int m = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());

            String s = st.nextToken();
            int a = 0;
            if(!s.equals("all") && !s.equals("empty")) {
                a = Integer.parseInt(st.nextToken());
            }
            switch(s){
                case "add" : {
                    set.add(a);
                    break;
                }
                case "remove" : {
                    set.remove(a);
                    break;
                }
                case "check" : {
                    sb.append(set.contains(a) ? 1 : 0).append("\n");
                    break;
                }
                case "toggle" : {
                    if(set.contains(a)) {
                        set.remove(a);
                    }else{
                        set.add(a);
                    }
                    break;
                }
                case "all" : {
                    set = new HashSet<>(list);
                    break;
                }
                case "empty" :{
                    set.clear();
                    break;
                }
            }
        }
        System.out.println(sb);
    }
}
