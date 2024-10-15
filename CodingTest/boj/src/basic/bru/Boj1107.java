package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1107 {
    static int min = 0;
    static int n;
    static boolean[] remote;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        remote = new boolean[10];
        if(m > 0) {
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < m; i++) {
                int a = Integer.parseInt(st.nextToken());
                remote[a] = true;
            }
        }

        min = Math.abs(n - 100);
        find(0,0);
        System.out.println(min);
    }

    private static void find(int depth, int cur) {
        if(depth > 0){
            min = Math.min(min,depth + Math.abs(n-cur));
        }

        if(depth == 6) return;

        for(int i=0; i<10; i++){
            if(!remote[i]){
                find(depth +1,cur * 10 + i);
            }
        }
    }
}
