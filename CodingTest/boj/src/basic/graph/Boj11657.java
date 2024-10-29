package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj11657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[] dap = new long[n+1];
        Arrays.fill(dap,Long.MAX_VALUE);
        Graph[] graphs = new Graph[m];
        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graphs[i] = new Graph(a, b, c);
        }

        dap[1] = 0;
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(dap[graphs[j].from] != Long.MAX_VALUE){
                    if(dap[graphs[j].to] > dap[graphs[j].from] + graphs[j].weight){
                        if(i == n-1){
                            System.out.println(-1);
                            return;
                        }
                        dap[graphs[j].to] = dap[graphs[j].from] + graphs[j].weight;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=n; i++){
            if(dap[i] != Long.MAX_VALUE){
                sb.append(dap[i]).append("\n");
            }else{
                sb.append(-1).append("\n");
            }
        }
        System.out.println(sb);

    }

    static class Graph{
        int from;
        int to;
        int weight;

        public Graph(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
