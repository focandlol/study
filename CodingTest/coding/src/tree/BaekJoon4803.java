package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BaekJoon4803 {
    static int[] u;
    static int count = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while(true) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            if(n == 0 && m == 0) break;

            u = new int[n + 1];
            for (int i = 1; i < n + 1; i++) {
                u[i] = i;
            }

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());

                union(a, b);
            }
            Set<Integer> set = new HashSet<Integer>();

            for (int i = 1; i < n + 1; i++) {
                int i1 = find(i);
                if (i1 > 0) {
                    set.add(i1);
                }
            }

            sb.append("Case " + count + ": ");
            if (set.size() == 0) {
                sb.append("No trees.").append("\n");
            } else if (set.size() == 1) {
                sb.append("There is one tree.").append("\n");
            } else {
                sb.append("A forest of " + set.size() + " trees.").append("\n");
            }
            count++;
        }
        System.out.println(sb);
    }

    private static void union(int a, int b) {
        int i = find(a);
        int j = find(b);

        if(u[i] == u[j]){
            u[i] = 0;
            u[j] = 0;
        }
        else if(i < j){
            u[j] = i;
        }else{
            u[i] = j;
        }
    }

    private static int find(int a) {
        if(u[a] == a) return a;
        else{
            return u[a] = find(u[a]);
        }
    }
}
