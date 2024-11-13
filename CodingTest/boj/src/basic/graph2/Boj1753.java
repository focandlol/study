package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[v+1];
        int[] ga = new int[v+1];

        for(int i=1; i<=v; i++) {
            graph[i] = new ArrayList<>();
            ga[i] = Integer.MAX_VALUE;
        }
        ga[start] = 0;

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to,weight));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        pq.add(new Node(start,0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(ga[cur.to] < cur.weight) continue;
            for(Node node : graph[cur.to]){
                if(ga[node.to] > node.weight + ga[cur.to]){
                    ga[node.to] = node.weight + ga[cur.to];
                    pq.add(new Node(node.to,ga[node.to]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=v; i++) {
            if(ga[i] == Integer.MAX_VALUE) sb.append("INF").append("\n");
            else sb.append(ga[i]).append("\n");
        }
        System.out.println(sb);
    }

    static class Node {
        int to;
        int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
