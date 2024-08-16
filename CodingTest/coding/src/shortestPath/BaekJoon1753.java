package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon1753 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());

        ArrayList<Node>[] arr = new ArrayList[v+1];
        int[] ga = new int[v+1];
        boolean[] visited = new boolean[v+1];
        for(int i = 1; i <= v; i++){
            arr[i] = new ArrayList<>();
            ga[i] = Integer.MAX_VALUE;
        }
        ga[k] = 0;

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            arr[a].add(new Node(b,c));
        }

        PriorityQueue<Node> dq = new PriorityQueue<>((a,b) -> a.ga - b.ga);
        dq.add(new Node(k,0));

        while(!dq.isEmpty()){
            Node node = dq.poll();
            int node1 = node.node;
            if(visited[node1]){
                continue;
            }
            visited[node1] = true;

            for(Node nod : arr[node1]){
                if(ga[nod.node] > ga[node1] + nod.ga){
                    ga[nod.node] = ga[node1] + nod.ga;
                    dq.add(new Node(nod.node,ga[nod.node]));
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=1; i<=v; i++){
            if(ga[i] == Integer.MAX_VALUE){
                sb.append("INF").append("\n");
            }else {
                sb.append(ga[i]).append("\n");
            }
        }

        System.out.println(sb);
    }


    static class Node{
        int node;
        int ga;

        public Node(int node, int ga) {
            this.node = node;
            this.ga = ga;
        }

        public int getNode() {
            return node;
        }

        public int getGa() {
            return ga;
        }

    }
}
