package graph;

import java.util.ArrayList;

public class Practice3 {
    static int[] visited;
    static boolean isEven = true;
    public static void main(String[] args) {
        int[][] graph = new int[][] {{1,3},{0,2},{1,3},{0,2}};
        visited = new int[graph.length];
        solution(graph);

        graph = new int[][] {{1,2,3},{0,2},{0,1,3},{0,2}};
        visited = new int[graph.length];
        solution(graph);
    }

    private static void solution(int[][] graph) {
        ArrayList<Integer>[] list = new ArrayList[graph.length];
        for (int i = 0; i < graph.length; i++) {
            list[i] = new ArrayList<>();
        }
        for (int i = 0; i < graph.length; i++) {
            for (int j = 0; j < graph[i].length; j++) {
                list[i].add(graph[i][j]);
                list[graph[i][j]].add(i);
            }
        }

        visited[0] = 1;
        dfs(0,list);

        System.out.println(isEven ? true : false);
    }

    private static void dfs(int i, ArrayList<Integer>[] list) {

        for(int a : list[i]){
            if(visited[a] == 0){
                visited[a] = visited[i] * -1;
                dfs(a,list);
            }else{
                if(visited[a] == visited[i]){
                    isEven = false;
                }
            }
        }
    }
}
