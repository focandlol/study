package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj9019 {
    static StringBuilder stringBuilder = new StringBuilder();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        boolean[] visited;
        int t = Integer.parseInt(st.nextToken());

        for(int i = 0; i < t; i++) {
            visited = new boolean[10000];
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            visited[a] = true;

            Queue<String[]> queue = new LinkedList<>();
            queue.add(new String[]{String.valueOf(a),"a"});
            while(!queue.isEmpty()) {
                String[] cur = queue.poll();
                if(Integer.parseInt(cur[0]) == b){
                    StringBuilder builder = new StringBuilder(cur[1]);
                    builder.deleteCharAt(0);
                    stringBuilder.append(builder.toString()).append("\n");
                    break;
                }
                for(int j=0; j<4; j++){
                    if(j == 0){
                        int z = (2 * Integer.parseInt(cur[0])) % 10000;
                        if(!visited[z]) {
                            visited[z] = true;
                            queue.add(new String[]{String.valueOf(z), cur[1] + "D"});
                        }
                    }else if(j == 1){
                       int z = Integer.parseInt(cur[0]) - 1;
                       if(z == b){
                           queue.add(new String[]{String.valueOf(z), cur[1] + "S"});
                           break;
                       }
                       if(Integer.parseInt(cur[0]) == 0){
                           z = 9999;
                       }
                        if(!visited[z]) {
                            visited[z] = true;
                            queue.add(new String[]{String.valueOf(z), cur[1] + "S"});
                        }
                    }else if(j == 2){
                       int z = (Integer.parseInt(cur[0]) % 1000) * 10 + Integer.parseInt(cur[0]) / 1000;
                        if(!visited[z]) {
                            visited[z] = true;
                            queue.add(new String[]{String.valueOf(z), cur[1] + "L"});
                        }
                    }else{
                        int z = (Integer.parseInt(cur[0]) % 10) * 1000 + Integer.parseInt(cur[0]) / 10;
                        if(!visited[z]) {
                            visited[z] = true;
                            queue.add(new String[]{String.valueOf(z), cur[1] + "R"});
                        }
                    }
                }
            }
        }
        System.out.println(stringBuilder);
    }
}
