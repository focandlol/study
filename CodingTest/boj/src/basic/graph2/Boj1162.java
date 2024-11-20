package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1162 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] dp = new long[n+1][k+1];

        for(int i=0; i<=n; i++) {
            Arrays.fill(dp[i],Long.MAX_VALUE);
        }
        dp[1][0] = 0;

        ArrayList<Node>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            long weight = Long.parseLong(st.nextToken());

            graph[from].add(new Node(to, 0, weight));
            graph[to].add(new Node(from, 0, weight));
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> Long.compare(a.weight,b.weight));
        pq.add(new Node(1,0,0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(dp[cur.to][cur.count] < cur.weight) {
                continue;
            }
            for(Node node : graph[cur.to]) {
                if(cur.count < k && dp[node.to][cur.count + 1] > dp[cur.to][cur.count]) {
                    dp[node.to][cur.count + 1] = dp[cur.to][cur.count];
                    pq.add(new Node(node.to,cur.count + 1,dp[node.to][cur.count + 1]));
                }
                if(dp[node.to][cur.count] > dp[cur.to][cur.count] + node.weight) {
                    dp[node.to][cur.count] = dp[cur.to][cur.count] + node.weight;
                    pq.add(new Node(node.to,cur.count,dp[node.to][cur.count]));
                }
            }
        }

        long dap = Long.MAX_VALUE;
        for(int i=0; i<=k; i++){
            dap = Math.min(dap,dp[n][i]);
        }

        System.out.println(dap);

    }

    static class Node{
        int to;
        int count;
        long weight;

        public Node(int to, int count, long weight) {
            this.to = to;
            this.count = count;
            this.weight = weight;
        }
    }
}
