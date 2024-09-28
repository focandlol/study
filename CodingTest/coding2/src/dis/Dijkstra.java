package dis;

import java.util.ArrayList;

public class Dijkstra {
    static class Node{
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        int[][] data = {{1,2,2},{1,3,3},{2,3,4},{2,4,5},{3,4,6},{5,1,1}};
        di(5,data,1);
    }

    private static void di(int v, int[][] data, int start) {
        ArrayList<ArrayList<Node>> graph = new ArrayList<>();
        for (int i = 0; i < v+1; i++) {
            graph.add(new ArrayList<>());
        }

        for(int i=0; i<data.length; i++) {
            graph.get(data[i][0]).add(new Node(data[i][1], data[i][2]));
        }

        int[] dist = new int[v+1];
        for(int i=0; i<v+1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;
        boolean[] visited = new boolean[v+1];

        for(int i=0; i<v; i++){
            int minDist = Integer.MAX_VALUE;
            int curIdx = 0;
            for(int j=1; j<v+1; j++){
                if(!visited[j] && dist[j] < minDist){
                    minDist = dist[j];
                    curIdx = j;
                }
            }

            visited[curIdx] = true;

            for(int j=0; j< graph.get(curIdx).size(); j++){
                Node adjNode = graph.get(curIdx).get(j);
                if(dist[adjNode.to] > dist[curIdx] + adjNode.weight){
                    dist[adjNode.to] = dist[curIdx] + adjNode.weight;
                }
            }
        }
        for(int i=1; i<v+1; i++) {
            if(dist[i] == Integer.MAX_VALUE){
                System.out.print("InF ");
            }else{
                System.out.print(dist[i]+ " ");
            }
        }

    }
}
