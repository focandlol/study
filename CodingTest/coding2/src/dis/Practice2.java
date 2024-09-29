package dis;

public class Practice2 {
    static class Edge{
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
    public static void main(String[] args) {
        int n = 3;
        int m = 3;
        int w = 1;
        int[][] portal = {{1,2,2},{1,3,4},{2,3,1}};
        int[][] wormhole = {{3,1,3}};

        System.out.println(solution(n,m,w,portal,wormhole));
    }

    private static boolean solution(int n, int m, int w, int[][] portal, int[][] wormhole) {
        Edge[] edges = new Edge[m+w];
        int[] dist = new int[n+1];
        for (int i = 1; i <= n; i++) {
            dist[i] = 1000000;
        }
        for(int i=0; i<m+w; i++) {
            if(i < m){
                edges[i] = new Edge(portal[i][0],portal[i][1],portal[i][2]);
            }else{
                edges[i] = new Edge(wormhole[i-m][0],wormhole[i-m][1],-wormhole[i-m][2]);
            }
        }
        dist[1] = 0;

        for(int i=0; i<=n; i++){
            for(int j=0; j<m+w; j++){
                if(dist[edges[j].from] != 1000000){
                    if(dist[edges[j].to] > dist[edges[j].from] + edges[j].weight){
                        if(i == n){
                            return true;
                        }
                        dist[edges[j].to] = dist[edges[j].from] + edges[j].weight;
                    }
                }
            }
        }
        return false;
    }
}
