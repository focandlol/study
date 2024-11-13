package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj11779 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] go = new ArrayList[n + 1];
        ArrayList<Node>[] back = new ArrayList[n + 1];
        int[] ga = new int[n + 1];
        for(int i=1; i<=n; i++) {
            go[i] = new ArrayList<>();
            back[i] = new ArrayList<>();
            ga[i] = Integer.MAX_VALUE;
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            go[from].add(new Node(to, weight));
            back[to].add(new Node(from, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        boolean[] visited = new boolean[n + 1];
        ga[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        pq.add(new Node(start, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.to == end){
                System.out.println(cur.weight);
                break;
            }
            if(visited[cur.to]) continue;
            visited[cur.to] = true;
            for(Node node : go[cur.to]) {
                if(!visited[node.to] && ga[node.to] > cur.weight + node.weight) {
                    ga[node.to] = cur.weight + node.weight;
                    pq.add(new Node(node.to, ga[node.to]));
                }
            }
        }
//        for(int i=1; i<=n; i++) {
//            System.out.println(ga[i] + " ");
//        }
//        for(Node node : back[3]){
//            System.out.println(node.to);
//        }
        pq.clear();
        visited = new boolean[n + 1];
        int count = 1;
        pq.add(new Node(end, 0));
        ArrayList<Integer> list = new ArrayList<>();
        list.add(end);
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.to == start){
                break;
            }
            visited[cur.to] = true;
            for(Node node : back[cur.to]) {
                if(!visited[node.to] && ga[node.to] + node.weight == ga[cur.to]){
                    visited[node.to] = true;
                    count++;
                    list.add(node.to);
                    pq.add(new Node(node.to, ga[node.to]));
                    break;
                }
            }
        }
        System.out.println(count);
        //System.out.println(sb);
        StringBuilder sb = new StringBuilder();
        for(int i=list.size()-1; i>=0; i--) {
            sb.append(list.get(i)).append(" ");
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
