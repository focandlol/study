package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj1238 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());

        int[] arr = new int[n+1];

        ArrayList<Node>[] graph = new ArrayList[n+1];
        for(int i=1; i<=n; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            graph[start].add(new Node(end,weight));
        }

        int[] ga = new int[n + 1];

        for(int i=1; i<=n; i++){
            PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.weight - b.weight);
            Arrays.fill(ga,Integer.MAX_VALUE);
            ga[i] = 0;
            pq.add(new Node(i,0));

            while(!pq.isEmpty()){
                Node node = pq.poll();

                if(i != x && node.to == x){
                    break;
                }
//                else if(i == x){
//                    arr[node.to] += node.weight;
//                }

                for(Node no : graph[node.to]){
                    if(ga[no.to] > node.weight + no.weight){
                        ga[no.to] = node.weight + no.weight;
                        pq.add(new Node(no.to,ga[no.to]));
                    }
                }
            }

            if(i != x){
                arr[i] += ga[x];
            }else{
                for(int k=1; k<=n; k++){
                    arr[k] += ga[k];
                }
            }
        }
        Arrays.sort(arr);
        System.out.println(arr[n]);
    }

    static class Node{
        private int to;
        private int weight;

        public Node(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
