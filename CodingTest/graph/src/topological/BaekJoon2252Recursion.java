package topological;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon2252Recursion {
    static int[] cha;
    static int[] sort;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        cha = new int[n+1];
        sort = new int[n+1];
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            cha[b]++;
        }

        for(int i=1; i<=n; i++){
            if(cha[i] == 0 && sort[i] == 0){
                we(i);
            }
        }

    }

    private static void we(int i) {
        if(sort[i] == 0){
            System.out.print(i + " ");
            sort[i] = 1;
            for(int j=0; j<graph[i].size(); j++){
                cha[graph[i].get(j)]--;
                if(cha[graph[i].get(j)] == 0){
                    we(graph[i].get(j));
                }
            }
        }
    }
}
