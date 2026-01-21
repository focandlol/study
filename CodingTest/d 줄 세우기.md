```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int[] cha;
    static ArrayList<Integer>[] graph;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        graph = new ArrayList[n + 1];
        cha = new int[n+1];
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i = 1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }
        for(int i = 1; i<=m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            graph[a].add(b);
            cha[b]++;
        }

        for(int i=1; i<=n; i++){
            if(cha[i] == 0){
                queue.offer(i);
            }
        }

        while(!queue.isEmpty()){
            int a = queue.poll();
            System.out.print(a + " ");
            for(int i=0; i<graph[a].size(); i++){
                cha[graph[a].get(i)]--;
                if(cha[graph[a].get(i)] == 0){
                    queue.offer(graph[a].get(i));
                }
            }
        }
    }
}

```
