package mst;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class BaekJoon1197 {
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

        //가중치가 작은 것부터 정렬
        Arrays.sort(edge, Comparator.comparingLong(Edge::getWeight));

        //kruskal
        for(int i=0; i<e; i++) {
            //대표노드 찾기
            int start = find(edge[i].getStart());
            int end = find(edge[i].getEnd());
            //대표노드가 다를 경우 union (사이클 만들어지는지 확인)
            if(start != end){
                union(edge[i].getStart(),edge[i].getEnd());
                weightSum += edge[i].getWeight();
            }
            //에지가 전체 노드 수 -1 만큼 만들어졌을 시 종료
            if(count == v-1){
                System.out.println(weightSum);
                break;
            }
        }
    }
    //union 함수
    private static void union(int a, int b){
        int s = find(a);
        int e = find(b);
        union[e] = s;
        count++;
    }

    //find 함수
    //대표노드를 찾고 대표노드 이전 거쳐간 노드들의 union배열의 값들을 대표노드로 변경
    private static int find(int a) {
        if(a == union[a]){
            return a;
        }else {
            int i = find(union[a]);
            union[a] = i;
            return i;
        }
    }

    //에지 리스트를 만들기 위한 edge 클래스
    static class Edge{
        //출발 노드
        int start;
        //종료 노드
        int end;
        //가중치
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