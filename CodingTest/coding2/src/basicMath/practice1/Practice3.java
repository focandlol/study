package basicMath.practice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Practice3 {
    static boolean[] visited;
    static char[] dap;
    static String s1;
    static String s2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        s1 = br.readLine();
        s2 = br.readLine();

        visited = new boolean[s1.length()];
        dap = new char[s1.length()];

        find(0);

        System.out.println(false);
    }

    private static void find(int depth) {
        if(depth == s1.length()) {
            String s = String.valueOf(dap);
            if(s2.contains(s)) {
                System.out.println(true);
                System.exit(0);
            }
            return;
        }
        for(int i=0; i<s1.length(); i++) {
            if(visited[i]) continue;
            visited[i] = true;
            dap[depth] = s1.charAt(i);
            find(depth+1);
            visited[i] = false;
        }
    }
}
