package basic.graph2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj1948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        int[] count = new int[n+1];
        int[] distance = new int[n+1];

        ArrayList<int[]>[] go = new ArrayList[n+1];
        ArrayList<int[]>[] back = new ArrayList[n+1];

        for(int i=1; i<=n; i++){
            go[i] = new ArrayList<>();
            back[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            go[from].add(new int[] {to, weight});
            back[to].add(new int[] {from, weight});
            count[to]++;
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int[] a : go[cur]){
                distance[a[0]] = Math.max(distance[a[0]], distance[cur] + a[1]);
                count[a[0]]--;
                if(count[a[0]] == 0) q.add(a[0]);
            }
        }

        System.out.println(distance[end]);

        int cnt = 0;
        boolean[] visited = new boolean[n+1];
        q.add(end);
        while(!q.isEmpty()){
            int cur = q.poll();
            for(int[] a : back[cur]){
                if(distance[cur] - a[1] == distance[a[0]]){
                    cnt++;
                    if(!visited[a[0]]){
                        visited[a[0]] = true;
                        q.add(a[0]);
                    }
                }
            }
        }
        System.out.println(cnt);

    }
}
