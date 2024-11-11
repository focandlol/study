package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1922 {
    static int n;
    static int m;
    static int[] union;
    static int count = 0;
    static int gap = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());

        union = new int[n+1];

        for(int i=1; i<=n; i++){
            union[i] = i;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((a,b) -> a.weight - b.weight);
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            pq.add(new Edge(a,b,c));
        }

        for(int i=0; i<m; i++){
            Edge cur = pq.poll();
            int a = find(cur.from);
            int b = find(cur.to);
            if(a != b){
                union(a,b);
                gap += cur.weight;
            }
            if(count == n-1){
                System.out.println(gap);
                return;
            }
        }
    }

    static void union(int a, int b){
        union[a] = b;
        count++;
    }

    static int find(int a){
        if(a == union[a]) return a;
        return union[a] = find(union[a]);
    }

    private static class Edge {
        private int from;
        private int to;
        private int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
