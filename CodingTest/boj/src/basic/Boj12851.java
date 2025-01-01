package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj12851 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] visitedCount = new int[200001];
        Arrays.fill(visitedCount,Integer.MAX_VALUE);

        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{n,0});
        visitedCount[n] = 0;

        int min = Integer.MAX_VALUE;
        int count = 0;
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if(cur[0] == k){
                if(cur[1] <= min){
                    min = cur[1];
                    count++;
                }
                continue;
            }

            for(int i=0; i<3; i++){
                if(i == 0){
                    int dx = cur[0] -1;
                    if(dx >= 0 && dx <= 100000){
                        if(visitedCount[dx] == Integer.MAX_VALUE){
                            visitedCount[dx] = cur[1] + 1;
                            queue.add(new int[]{dx,cur[1] + 1});
                        }else{
                            if(visitedCount[dx] >= cur[1] + 1){
                                visitedCount[dx] = cur[1] + 1;
                                queue.add(new int[]{dx,cur[1] + 1});
                            }
                        }
                    }
                }else if(i == 1){
                    int dx = cur[0] + 1;
                    if(dx >= 0 && dx <= 100000){
                        if(visitedCount[dx] == Integer.MAX_VALUE){
                            visitedCount[dx] = cur[1] + 1;
                            queue.add(new int[]{dx,cur[1] + 1});
                        }else{
                            if(visitedCount[dx] >= cur[1] + 1){
                                visitedCount[dx] = cur[1] + 1;
                                queue.add(new int[]{dx,cur[1] + 1});
                            }
                        }
                    }
                }else{
                    int dx = cur[0] * 2;
                    if(dx >= 0 && dx <= 100000){
                        if(visitedCount[dx] == Integer.MAX_VALUE){
                            visitedCount[dx] = cur[1] + 1;
                            queue.add(new int[]{dx,cur[1] + 1});
                        }else{
                            if(visitedCount[dx] >= cur[1] + 1){
                                visitedCount[dx] = cur[1] + 1;
                                queue.add(new int[]{dx,cur[1] + 1});
                            }
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min).append("\n");
        sb.append(count);
        System.out.println(sb);
    }
}
