package tree;

import org.w3c.dom.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon1167 {
    static ArrayList<Node>[] arr;
    static int[] visited;
    static int max = 0;
    static int start = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        arr = new ArrayList[v+1];
        visited = new int[v+1];
        for(int i = 1; i <= v; i++){
            arr[i] = new ArrayList<>();
        }

        for(int i=0; i<v; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            while(true){
                int b = Integer.parseInt(st.nextToken());
                if(b == -1){
                    break;
                }
                int weight = Integer.parseInt(st.nextToken());

                arr[a].add(new Node(b, weight));
                arr[b].add(new Node(a, weight));
            }
        }

        dfs(new Node(1,0));
        visited = new int[v+1];
        dfs(new Node(start,0));
        System.out.println(max);
    }

    private static void dfs(Node node) {
        visited[node.next] = 1;
        if(node.weight > max) {
            max = node.weight;
            start = node.next;
        }
        for(Node nod : arr[node.next]){
            if(visited[nod.next] == 0){
                dfs(new Node(nod.next,node.weight+nod.weight));
            }
        }
    }


    static class Node{
        int next;
        int weight;

        public Node(int next, int weight) {
            this.next = next;
            this.weight = weight;
        }
    }
}
