```
import java.util.*;
import java.io.*;

class Node implements Comparable<Node> {
    int next;
    int weight;

    public Node(int next, int weight) {
        this.next = next;
        this.weight = weight;
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }
}

class Main {
    static List<List<Node>> list;
    static int[] dist;
    static int n;
    final static int INF =  50000 * 1000 * 2;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while(T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            list = new ArrayList<>();
            for(int i = 0; i <= n; i++)
                list.add(new ArrayList<>());

            for(int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken()) * 2;
                if((a == g && b == h) || (a == h && b == g))
                    d--;

                list.get(a).add(new Node(b, d));
                list.get(b).add(new Node(a, d));
            }
            int[] destination = new int[t];
            dist = new int[n+1];
            
            for(int i = 0; i < t; i++) {
                destination[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(destination);
            bfs(s);
            for(int i : destination) {
                if(dist[i] % 2 == 1)
                    sb.append(i).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void bfs(int start) {
        Queue<Node> queue = new PriorityQueue<>();
        boolean[] check = new boolean[n+1];
        for(int i = 1; i <= n; i++)
            dist[i] = INF;
        dist[start] = 0;
        queue.offer(new Node(start, 0));
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            if(check[node.next])
                continue;
            check[node.next] = true;

            for(Node next : list.get(node.next)) {
                if(!check[next.next] && dist[next.next] > dist[node.next] + next.weight) {
                    dist[next.next] = dist[node.next] + next.weight;
                    queue.offer(new Node(next.next, dist[next.next]));
                }
                    
            }
        }
    }
}
```
