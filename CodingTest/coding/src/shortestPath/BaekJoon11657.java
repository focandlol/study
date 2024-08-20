package shortestPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon11657 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        Graph[] arr = new Graph[m];
        long[] dap = new long[n+1];

        Arrays.fill(dap,Integer.MAX_VALUE);
        dap[1] = 0;

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            arr[i] = new Graph(start,end,weight);
        }

        for(int i=0; i<n-1; i++){
            for(int j=0; j<m; j++){
                if(dap[arr[j].start] != Integer.MAX_VALUE){
                    if(dap[arr[j].end] > dap[arr[j].start] + arr[j].weight){
                        dap[arr[j].end] = dap[arr[j].start] + arr[j].weight;
                    }
                }
            }
        }

        for(int j=0; j<m; j++){
            if(dap[arr[j].start] != Integer.MAX_VALUE){
                if(dap[arr[j].end] > dap[arr[j].start] + arr[j].weight){
                    System.out.println(-1);
                    return;
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i=2; i<=n; i++){
            if(dap[i] != Integer.MAX_VALUE) {
                sb.append(dap[i]).append("\n");
            }else{
                sb.append(-1).append("\n");
            }
        }

        System.out.println(sb);


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
    }
}
