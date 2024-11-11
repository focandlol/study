package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1197 {
    static int v;
    static int e;
    static Edge[] arr;
    static int[] union;
    static int count = 0;
    static long dap = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());

        arr = new Edge[e];
        union = new int[v+1];
        for(int i=1; i<=v; i++){
            union[i] = i;
        }
        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            arr[i] = new Edge(from, to, weight);
        }

        Arrays.sort(arr,(a,b) -> a.weight - b.weight);

        for(int i=0; i<e; i++){
            Edge edge = arr[i];
            int a = find(edge.from);
            int b = find(edge.to);
            if(a != b){
                union(a,b);
                dap += edge.weight;
            }
            if(count == v-1) {
                System.out.println(dap);
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

    static class Edge{
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
