package basic.bi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1939 {
    static int n;
    static int m;
    static ArrayList<int[]>[] list;
    static int start;
    static int end;
    static boolean[] visited;
    static int dap = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        visited = new boolean[n];
        list = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            list[a].add(new int[]{b,c});
            list[b].add(new int[]{a,c});
        }

        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{start,Integer.MAX_VALUE});
        visited[start] = true;

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(cur[0] == end){
                dap = Math.max(dap,cur[1]);
            }
            for (int[] a : list[cur[0]]) {
                if(!visited[a[0]]){
                    if(a[0] != end) {
                        visited[a[0]] = true;
                    }
                    queue.add(new int[]{a[0],Math.min(a[1],cur[1])});
                }
            }
        }
        
    }
}
