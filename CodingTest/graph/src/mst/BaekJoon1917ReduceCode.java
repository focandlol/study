package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BaekJoon1917ReduceCode {
    static Edge[] edge;
    static int[] union;
    static int count = 0;
    static int weightSum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int v = Integer.valueOf(st.nextToken());
        int e = Integer.valueOf(st.nextToken());

        edge = new Edge[e];
        union = new int[v+1];
        for(int i=1;i<=v;i++){
            union[i] = i;
        }

        for(int i=0; i<e; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.valueOf(st.nextToken());
            int end = Integer.valueOf(st.nextToken());
            long weight = Long.valueOf(st.nextToken());

            edge[i] = new Edge(start, end, weight);
        }

        Arrays.sort(edge, Comparator.comparingLong(Edge::getWeight));

        for(int i=0; i<e; i++) {
            int start = find(edge[i].getStart());
            int end = find(edge[i].getEnd());
            if(start != end){
                union(start,end);
                weightSum += edge[i].getWeight();
            }
            if(count == v-1){
                System.out.println(weightSum);
                break;
            }
        }
    }
    private static void union(int a, int b){
        union[b] = a;
        count++;
    }

    private static int find(int a) {
        if(a == union[a]){
            return a;
        }else {
            int i = find(union[a]);
            union[a] = i;
            return i;
        }
    }

    static class Edge{
        int start;
        int end;
        long weight;

        public Edge(int start, int end, long weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }

        public long getWeight() {
            return weight;
        }
    }

}