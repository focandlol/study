package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj1865 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            Edge[] graph = new Edge[2 * m + w];

            int k = 0;
            int totalEdges = m + w;
            for (int j = 0; j < totalEdges; j++) {
                st = new StringTokenizer(br.readLine());
                int s = Integer.parseInt(st.nextToken());
                int e = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                if (j < m) {
                    // 도로는 양방향
                    graph[k++] = new Edge(s, e, y);
                    graph[k++] = new Edge(e, s, y);
                } else {
                    // 웜홀은 단방향이며 음수 가중치
                    graph[k++] = new Edge(s, e, -y);
                }
            }

            boolean hasNegativeCycle = false;
            for (int start = 1; start <= n; start++) {
                int[] gap = new int[n + 1];
                Arrays.fill(gap, Integer.MAX_VALUE);
                gap[start] = 0;

                // 벨만-포드: n-1번 반복
                boolean check;
                for (int j = 0; j < n - 1; j++) {
                    check = false;
                    for (Edge edge : graph) {
                        if (gap[edge.from] != Integer.MAX_VALUE &&
                                gap[edge.to] > gap[edge.from] + edge.weight) {
                            gap[edge.to] = gap[edge.from] + edge.weight;
                            check = true; // 거리 갱신 발생
                        }
                    }
                    if (!check) break; // 갱신이 없으면 조기 종료
                }

                // 음수 사이클 체크
                for (Edge edge : graph) {
                    if (gap[edge.from] != Integer.MAX_VALUE &&
                            gap[edge.to] > gap[edge.from] + edge.weight) {
                        hasNegativeCycle = true;
                        break;
                    }
                }

                if (hasNegativeCycle) break; // 하나라도 음수 사이클이 있으면 종료
            }

            if (hasNegativeCycle) {
                sb.append("YES\n");
            } else {
                sb.append("NO\n");
            }
        }
        System.out.println(sb);
    }

    static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}