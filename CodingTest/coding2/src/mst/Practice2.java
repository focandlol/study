package mst;

import java.util.Arrays;

public class Practice2 {
    static int[] parent;
    public static void main(String[] args) {
        int v = 7;
        int e = 12;
        int[][] data = {{1,2,3},{1,3,2},{1,6,2},{2,5,2},{3,2,1},{3,4,4},{4,5,3},{5,1,5},{6,4,1},
                {6,5,3},{6,7,4},{7,3,6}};
        solution(v,e,data);
    }

    private static void solution(int v, int e, int[][] data) {
        parent = new int[v+1];
        for(int i = 1; i<=v; i++){
            parent[i] = i;
        }
        int max = 0;
        int dap = 0;
        Arrays.sort(data,(x,y) -> x[2] - y[2]);
        for(int i=0; i<data.length; i++) {
            int start = find(data[i][0]);
            int end = find(data[i][1]);
            if(start != end) {
                union(start,end);
                dap += data[i][2];
                max = Math.max(max,data[i][2]);
            }
        }
        System.out.println(dap);
        System.out.println(max);
        System.out.println(dap - max);
    }

    private static int find(int a) {
        if(a == parent[a]) return a;
        return parent[a] = find(parent[a]);
    }

    private static void union(int a, int b) {
        parent[a] = b;
    }
}
