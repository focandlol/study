package dis;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Practice1 {
    static ArrayList<Node>[] arr;
    static class Node{
        int data;
        int weight;

        public Node(int data, int weight) {
            this.data = data;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        int[][] data = {{1,2,3},{1,3,5},{1,4,4},{2,3,3},{2,4,5},{3,4,1}};
        int v = 4;
        int via1 = 2;
        int via2 = 3;
        int start = 1;
        int n = 4;
        System.out.println(solution(data,v,via1,via2,start,n));
    }

    private static int solution(int[][] data, int v, int via1, int via2, int start, int n) {
        int case1 = 0;
        int case2 = 0;
        arr = new ArrayList[v+1];

        for(int i=1; i<=v; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<data.length; i++){
            arr[data[i][0]].add(new Node(data[i][1],data[i][2]));
            arr[data[i][1]].add(new Node(data[i][0],data[i][2]));
        }

        case1 += djikstra(v,start,via1);
        case1 += djikstra(v,via1,via2);
        case1 += djikstra(v,via2,n);

        case2 += djikstra(v,start,via2);
        case2 += djikstra(v,via2,via1);
        case2 += djikstra(v,via1,n);

        if(case1 == Integer.MAX_VALUE && case2 == Integer.MAX_VALUE) {
            return -1;
        }else {
            return Math.min(case1, case2);
        }
    }

    private static int djikstra(int v, int start, int via1) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>((a,b) -> a.weight - b.weight);
        int visited[] = new int[v+1];
        pq.add(new Node(start,0));

        int[] dist = new int[v+1];
        for(int i=1; i<=v; i++){
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(visited[cur.data] == 1){
                continue;
            }
            visited[cur.data] = 1;
            for(Node adjNode : arr[cur.data]){
                if(visited[adjNode.data] == 0 && dist[adjNode.data] > dist[cur.data] + adjNode.weight){
                    dist[adjNode.data] = dist[cur.data] + adjNode.weight;
                    pq.add(new Node(adjNode.data,dist[adjNode.data]));
                }
            }
        }
        return dist[via1];
    }
}
