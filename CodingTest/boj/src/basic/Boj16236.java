package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16236 {
    static int[][] di = {{0, 1, 0, -1}, {1, 0, -1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][n];

        int[] start = new int[2];
        int[] prev = new int[2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 9) {
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        boolean[][] visited = new boolean[n][n];
        visited[start[0]][start[1]] = true;
        int min = Integer.MAX_VALUE;
        int size = 2;
        int eat = 0;
        int count = 0;
        while (true) {
            int cnt = 0;
            Queue<Fish> queue = new LinkedList<>();
            queue.add(new Fish(start[0], start[1], size, eat, count));
            prev[0] = start[0];
            prev[1] = start[1];
            visited[start[0]][start[1]] = true;
            while (!queue.isEmpty()) {
                Fish fish = queue.poll();

                if(fish.count > min) {
                    continue;
                }

                if(arr[fish.x][fish.y] < fish.size && arr[fish.x][fish.y] != 0) {
                    cnt++;
                    if(min > fish.count) {
                        min = fish.count;
                        start[0] = fish.x;
                        start[1] = fish.y;
                        count = fish.count;
                    }else if(min == fish.count) {
                        if(start[0] > fish.x){
                            start[0] = fish.x;
                            start[1] = fish.y;
                            count = fish.count;
                        }else if(start[0] == fish.x){
                            if(start[1] > fish.y){
                                start[0] = fish.x;
                                start[1] = fish.y;
                                count = fish.count;
                            }
                        }
                    }
                    continue;
                }

                for (int i = 0; i < 4; i++) {
                    int dx = fish.x + di[0][i];
                    int dy = fish.y + di[1][i];

                    if(dx >=0 && dx < n && dy >= 0 && dy < n && !visited[dx][dy]) {
                        if(arr[dx][dy] <= fish.size) {
                            visited[dx][dy] = true;
                            queue.add(new Fish(dx, dy, fish.size, fish.eat, fish.count + 1));
                        }
                    }
                }
            }
            if(cnt == 0){
                System.out.println(count);
                return;
            }
            min = Integer.MAX_VALUE;
            eat++;
            if(eat == size){
                eat = 0;
                size++;
            }
            arr[prev[0]][prev[1]] = 0;
            arr[start[0]][start[1]] = 0;
            visited = new boolean[n][n];

        }


    }

    static class Fish {
        int x;
        int y;
        int size;
        int eat;
        int count;

        public Fish(int x, int j, int size, int eat, int count) {
            this.x = x;
            this.y = j;
            this.size = size;
            this.eat = eat;
            this.count = count;
        }
    }
}
