package graph;

import java.util.ArrayList;

public class Practice1 {
    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{4,2}};
        System.out.println(solution(edges));

        edges = new int[][]{{1,2},{5,1},{1,3},{1,4}};
        System.out.println(solution(edges));
    }

    private static int solution(int[][] edges) {
        ArrayList<Integer>[] graph = new ArrayList[edges.length+2];
        for (int i = 1; i < graph.length; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<edges.length; i++) {
            graph[edges[i][0]].add(edges[i][1]);
            graph[edges[i][1]].add(edges[i][0]);
        }

        for(int i=1; i<graph.length; i++) {
            if(graph[i].size() == edges.length) {
                return i;
            }
        }
        return -1;
    }
}
