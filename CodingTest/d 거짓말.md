```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int m;
    static int know;
    static List<Integer> knowList = new ArrayList<Integer>();
    static ArrayList<Integer>[] graph;
    static ArrayList<Integer>[] gap;
    static boolean[] visited;
    static HashSet<Integer> set = new HashSet<Integer>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());

        gap = new ArrayList[n+1];
        graph = new ArrayList[n+1];
        visited = new boolean[n+1];

        for(int i=0; i<=n; i++) {
            gap[i] = new ArrayList<>();
        }
        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        know = Integer.parseInt(st.nextToken());

        for(int i=0; i<know; i++){
            knowList.add(Integer.parseInt(st.nextToken()));
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            gap[a].add(i);
            for(int j=1; j<num; j++){
                int b = Integer.parseInt(st.nextToken());
                graph[a].add(b);
                graph[b].add(a);
                gap[b].add(i);
            }
        }

        for(int i=0; i<know; i++){
            if(!visited[knowList.get(i)]){
                //System.out.println("know = " + knowList.get(i));
                dfs(knowList.get(i));
            }
        }

        //System.out.println(m);
        //System.out.println(set.size());
        System.out.println(m- set.size());


    }

    private static void dfs(int a) {
        visited[a] = true;
        if(set.size() == m){
            return;
        }
        for(int b : gap[a]){
            //System.out.println("plus set = " + b);
            set.add(b);
        }

        for(int k : graph[a]){
            if(!visited[k]){
                dfs(k);
            }
        }
    }
}
```
