package dis;

public class Bellman {
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
        int[][] data = {{1,2,8},{1,3,6},{1,5,5},{2,3,-5},{2,4,1},{2,6,4},{3,4,4},{4,7,3},{5,6,5},{6,2,0},{6,7,-7}};
        bell(7,11,data,1);

        data = new int[][]{{1,2,8},{1,3,6},{1,5,5},{2,3,-5},{2,4,1},{2,6,4},{3,4,4},{4,7,3},{5,6,5},{6,2,-5},{6,7,-7}};
        bell(7,11,data,1);
    }

    private static void bell(int v, int e, int[][] data, int start) {
        Edge[] edges = new Edge[e];
        for (int i = 0; i < e; i++) {
            edges[i] = new Edge(data[i][0], data[i][1], data[i][2]);
        }

        int[] dist = new int[v+1];

        for(int i=1; i<=v; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[start] = 0;
        boolean isMinusCycle = false;

        for(int i=0; i<v+1; i++) {
            for(int j=0; j<e; j++) {
                Edge cur = edges[j];
                if(dist[cur.from] == Integer.MAX_VALUE) {
                    continue;
                }

                if(dist[cur.to] > dist[cur.from] + cur.weight) {
                    dist[cur.to] = dist[cur.from] + cur.weight;
                    if(i == v){
                        isMinusCycle = true;
                        //break;
                    }
                }
            }
        }

        System.out.println("음수 사이클 발생 : " + isMinusCycle);
        for(int i=1; i<=v; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.print("INF ");
            }else{
                System.out.print(dist[i] + " ");
            }
        }
        System.out.println();

    }
}
