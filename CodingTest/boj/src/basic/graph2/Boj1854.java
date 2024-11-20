package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1854 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] graph = new ArrayList[n+1];
        ArrayList<PriorityQueue<Integer>> dap = new ArrayList<>();

        for(int i=0; i<=n; i++){
            graph[i] = new ArrayList<>();
            dap.add(new PriorityQueue<>((a,b) -> b-a));
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[from].add(new Node(to, weight));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        pq.add(new Node(1, 0));
        dap.get(1).add(0);
        while(!pq.isEmpty()){
            Node cur = pq.poll();

            for(Node node : graph[cur.to]){
                if(dap.get(node.to).size() < k){
                    dap.get(node.to).add(cur.weight + node.weight);
                    pq.add(new Node(node.to, cur.weight + node.weight));
                }else if(dap.get(node.to).peek() > cur.weight + node.weight){
                    dap.get(node.to).poll();
                    dap.get(node.to).add(cur.weight + node.weight);
                    pq.add(new Node(node.to, cur.weight + node.weight));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=n; i++){
            if(dap.get(i).size() < k){
                sb.append("-1\n");
            }else{
                sb.append(dap.get(i).peek()).append("\n");
            }
        }
        System.out.println(sb);
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
