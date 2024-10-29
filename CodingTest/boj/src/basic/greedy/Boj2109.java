package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj2109 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        PriorityQueue<int[]> pq = new PriorityQueue<>((x,y) -> {
            if(x[0] == y[0]){
                return y[1] - x[1];
            }
            return y[0] - x[0];
        });

        for(int i = 0; i <n; i++) {
            st = new StringTokenizer(br.readLine());
            pq.add(new int[]{Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }

        boolean[] visited = new boolean[10001];

        int dap = 0;
        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            for(int i = cur[1]; i>=1; i--){
                if(!visited[i]){
                    visited[i] = true;
                    dap += cur[0];
                    break;
                }
            }
        }
        System.out.println(dap);
    }
}
