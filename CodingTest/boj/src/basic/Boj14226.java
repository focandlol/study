package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj14226 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int s = Integer.parseInt(br.readLine());

        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[s + 1][s + 1];
        visited[1][0] = true;
        queue.add(new int[]{1,0,0});

        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            //System.out.println("cur[0] : " + cur[0] + " cur[1] : " + cur[1] + " cur[2] : " + cur[2]);

            if(cur[0] == s){
                System.out.println(cur[2]);
                return;
            }

            for(int i=0; i<3; i++){
                if(i == 0){
                    int copy = cur[0];
                    if(copy <= s && !visited[cur[0]][copy]){
                        visited[cur[0]][copy] = true;
                        queue.add(new int[]{cur[0],copy,cur[2] + 1});
                    }
                }else if(i == 1){
                    int screen = cur[0] + cur[1];
                    if(screen <= s && cur[1] != 0 && !visited[screen][cur[1]]){
                        visited[screen][cur[1]] = true;
                        queue.add(new int[]{screen,cur[1],cur[2] + 1});
                    }
                }else{
                    int screen = cur[0] - 1;
                    if(screen >= 0 && !visited[screen][cur[1]]){
                        visited[screen][cur[1]] = true;
                        queue.add(new int[]{screen,cur[1],cur[2] + 1});
                    }
                }
            }
        }


    }
}
