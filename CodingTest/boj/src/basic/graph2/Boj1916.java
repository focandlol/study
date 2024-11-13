package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1916 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            graph[from].add(new Node(to,weight));
            //graph[to].add(new Node(from,weight));
        }

        int[] ga = new int[n+1];
        boolean[] visited = new boolean[n+1];
        Arrays.fill(ga,Integer.MAX_VALUE);

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        ga[start] = 0;
        visited[start] = true;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        pq.add(new Node(start,0));
        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.to == end){
                System.out.println(cur.weight);
                return;
            }
            visited[cur.to] = true;
            for(Node node : graph[cur.to]){
                if(!visited[node.to] && ga[node.to] > cur.weight + node.weight){
                    ga[node.to] = cur.weight + node.weight;
                    pq.add(new Node(node.to,cur.weight + node.weight));
                }
            }
        }
        //System.out.println(ga[end]);

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
