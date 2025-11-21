```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int e;
    static ArrayList<int[]>[] list;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());


        list = new ArrayList[n + 1];
        for(int i=1; i<=n; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i=0; i<e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list[from].add(new int[] {to, weight});
            list[to].add(new int[] {from, weight});
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        
        long first = find(1, v1) + find(v1,v2) + find(v2,n);
        long second = find(1,v2) + find(v2,v1) + find(v1,n);
        
        long dap =  (first >= Integer.MAX_VALUE && second >= Integer.MAX_VALUE) ? -1 : Math.min(first,second);
        
        System.out.println(dap);
    }

    private static long find(int i, int v1) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a,b) -> a[1] - b[1]);
        pq.add(new int[] {i, 0});
        visited = new boolean[n + 1];

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            if(cur[0] == v1) return cur[1];
            if(visited[cur[0]]){
                continue;
            }
            visited[cur[0]] = true;
            for(int[] a : list[cur[0]]) {
                if(!visited[a[0]]) {
                    pq.add(new int[] {a[0], cur[1] + a[1]});
                }
            }
        }
        return Integer.MAX_VALUE;
    }
}

```
