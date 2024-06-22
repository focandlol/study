package bellman;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11657 {
    static Graph[] graph;
    static long[] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new Graph[m];
        d = new long[n+1];
        for(int i=1; i<=n; i++){
            d[i] = Long.MAX_VALUE;
        }
        d[1] = 0;

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[i] = new Graph(start, end, weight);
        }

        int count = 0;
        for(int i=1; i<=n; i++){
            for(int j=0; j<graph.length; j++){
                if(d[graph[j].getStart()] != Long.MAX_VALUE){
                    if(d[graph[j].getStart()] + graph[j].getWeight() < d[graph[j].getEnd()]){
                        if(i == n){
                            count++;
                            break;
                        }
                        d[graph[j].getEnd()] = d[graph[j].getStart()] + graph[j].getWeight();
                    }
                }
                //System.out.println("i = " + i + " " + Arrays.toString(d));
            }
        }

        for(int i=2; i<=n; i++){
            if(count > 0){
                System.out.println("-1");
                break;
            }
            if(d[i] == Long.MAX_VALUE){
                System.out.println("-1");
            }else {
                System.out.println(d[i]);
            }
        }
    }

    static class Graph{
        int start;
        int end;
        int weight;

        public Graph(int start, int end, int weight) {
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

        public int getWeight() {
            return weight;
        }
    }
}

