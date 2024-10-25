package basic.tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Boj1167 {
    static ArrayList<Node>[] arr;
    static boolean[] visited;
    static int max=0;
    static int maxIndex=0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        visited = new boolean[v+1];
        arr = new ArrayList[v+1];
        for(int i = 1; i <= v; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=1; i<=v; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b;
            while((b = Integer.parseInt(st.nextToken())) != -1){
                int weight = Integer.parseInt(st.nextToken());
                arr[a].add(new Node(b,weight));
                arr[b].add(new Node(a,weight));
            }
        }

        dfs(1,0);
        max = 0;
        visited = new boolean[v+1];
        dfs(maxIndex,0);
        System.out.println(max);
    }

    private static void dfs(int a, int dap) {
        visited[a] = true;
        for(Node n : arr[a]){
            if(!visited[n.to]){
                dfs(n.to,dap + n.weight);
            }
        }
        if(dap > max){
            max = dap;
            maxIndex = a;
        }
    }

    static class Node{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
