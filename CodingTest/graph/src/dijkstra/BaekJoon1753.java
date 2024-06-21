package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class BaekJoon1753 {
    static ArrayList<Node>[] arr;
    static int[] da;
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(bf.readLine());
        int k = Integer.parseInt(st.nextToken());
        arr = new ArrayList[v+1];
        da = new int[v+1];
        for(int i=1; i<=v; i++){
            arr[i] = new ArrayList<>();
            da[i] = Integer.MAX_VALUE;
        }
        da[k] = 0;
        for(int i=1; i<=e; i++) {
            st = new StringTokenizer(bf.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            arr[a].add(new Node(b,w));
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.comparingInt(p -> da[p]));
        pq.add(k);
        while(!pq.isEmpty()){
            int cur = pq.poll();

            for(Node node : arr[cur]) {
                if(da[cur] + node.getGa() < da[node.getNo()]){
                    da[node.getNo()] = da[cur] + node.getGa();
                    pq.add(node.getNo());
                }

            }
        }
        for(int i=1; i<=v; i++){
            if(da[i] != Integer.MAX_VALUE){
                System.out.println(da[i]);
            }else{
                System.out.println("INF");
            }
        }
    }

    static class Node{
        int no;
        int ga;
        Node(int no, int ga){
            this.no = no;
            this.ga = ga;
        }

        public int getNo() {
            return no;
        }

        public int getGa() {
            return ga;
        }
    }
}