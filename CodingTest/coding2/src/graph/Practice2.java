package graph;

import java.util.ArrayList;

public class Practice2 {
    static int visited[];
    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2},{2,0}};
        int n=3;
        int source = 0;
        int dest = 2;
        visited = new int[n];
        solution(n,edges,source,dest);
    }

    private static void solution(int n, int[][] edges, int source, int dest) {
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        dfs(source,graph);

        System.out.println(visited[dest] == 1 ? true : false);

    }

    private static void dfs(int source, ArrayList<Integer>[] graph) {
        if(visited[source] == 0){
            visited[source] = 1;
        }

        for (int i : graph[source]) {
            if(visited[i] == 0){
                dfs(i,graph);
            }
        }
    }
}
