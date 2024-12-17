package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj15663 {
    static HashSet<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        ArrayList<Integer> list = new ArrayList<>();
        for(int i=0; i<n; i++){
            int a = Integer.parseInt(st.nextToken());
            list.add(a);
        }

        Collections.sort(list);

        find(list,0,m,new int[m], new boolean[n]);
        System.out.println(sb);
    }

    private static void find(ArrayList<Integer> list, int depth,int m, int[] dap,boolean[] visited) {
        if(depth == m){
            StringBuilder k = new StringBuilder();
            for(int i : dap){
                k.append(i).append(" ");
            }
            if(!set.add(k.toString())){
                return;
            }
            sb.append(k).append("\n");
            return;
        }

        for(int i=0; i<list.size(); i++){
            if(!visited[i]){
                visited[i] = true;
                dap[depth] = list.get(i);
                find(list,depth+1,m,dap,visited);
                visited[i] = false;
            }
        }
    }
}
