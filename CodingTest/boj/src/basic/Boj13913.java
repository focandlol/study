package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Boj13913 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        boolean[] visited = new boolean[100001];
        int[] prev = new int[100001];

        visited[n] = true;
        StringBuilder sb = new StringBuilder();

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n,0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();

            if(cur[0] == m){
                sb.append(cur[1]).append("\n");
                break;
            }
            int next;
            for(int i=0; i<3; i++){
                if(i == 0){
                    next = cur[0] -1;
                }else if(i == 1){
                    next = cur[0] + 1;
                }else{
                    next = cur[0] * 2;
                }

                if(next >= 0 && next <= 100000 && !visited[next]){
                    visited[next] = true;
                    prev[next] = cur[0];
                    queue.add(new int[]{next,cur[1] + 1});
                }
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        int a = m;

        while (a != n) {
            list.add(a);
            a = prev[a];
        }
        list.add(n);

        for(int i=list.size()-1; i>=0; i--){
            sb.append(list.get(i)).append(" ");
        }

        System.out.println(sb);
    }
}
