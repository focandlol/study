package mst;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class Prim {
    static class Node{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        int v = 7;
        int e = 10;
        int[][] graph = {{1,3,1},{1,2,9},{1,6,8},{2,4,13},{2,5,2},{2,6,7},{3,4,12},{4,7,17},{5,6,5},{5,7,20}};

        System.out.println(prim(graph,v,e));
    }

    private static int prim(int[][] graph, int v, int e) {
        int weightSum = 0;
        boolean[] visited = new boolean[v+1];
        ArrayList<Node>[] arr = new ArrayList[v+1];

        for(int i=1; i<=v; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i<graph.length; i++) {
            arr[graph[i][0]].add(new Node(graph[i][1], graph[i][2]));
            arr[graph[i][1]].add(new Node(graph[i][0], graph[i][2]));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);

        pq.add(new Node(1, 0));

        int cnt = 0;
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            cnt+=1;
            if(visited[cur.to]) {
                continue;
            }
            visited[cur.to] = true;
            weightSum += cur.weight;

            if(cnt == v){
                return weightSum;
            }

            for(Node i : arr[cur.to]){
                if(!visited[i.to]) {
                    pq.add(i);
                }
            }
        }
        return weightSum;
    }
}
