package dis;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DijkPrior {
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
        for (int i = 0; i < v + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < data.length; i++) {
            graph.get(data[i][0]).add(new Node(data[i][1], data[i][2]));
        }

        int[] dist = new int[v + 1];
        for (int i = 0; i < v + 1; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((x,y) -> x.weight - y.weight);
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            if(dist[cur.to] < cur.weight){
                continue;
            }

            for(int i=0; i<graph.get(cur.to).size(); i++){
                Node adjNode = graph.get(cur.to).get(i);
                if(dist[adjNode.to] > dist[cur.to] + adjNode.weight){
                    dist[adjNode.to] = dist[cur.to] + adjNode.weight;
                    pq.add(new Node(adjNode.to, dist[adjNode.to]));
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
