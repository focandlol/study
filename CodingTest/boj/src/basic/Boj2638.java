package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2638 {
    static int n, m;
    static int[][] cheese;
    static boolean[][] visited;
    static int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        cheese = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                cheese[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int time = 0;

        while (true) {
            // 1. 외부 공기를 표시
            markExternalAir();

            // 2. 치즈를 녹이고, 남은 치즈가 있는지 확인
            boolean hasCheese = meltCheese();

            if (!hasCheese) {
                // 모든 치즈가 녹았다면 종료
                System.out.println(time);
                return;
            }

            time++;
        }
    }

    // 외부 공기 표시
    private static void markExternalAir() {
        visited = new boolean[n][m];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0}); // 모눈종이 가장자리는 항상 공기

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int[] direction : directions) {
                int dx = x + direction[0];
                int dy = y + direction[1];

                if (dx >= 0 && dy >= 0 && dx < n && dy < m && !visited[dx][dy] && cheese[dx][dy] == 0) {
                    visited[dx][dy] = true;
                    queue.add(new int[]{dx, dy});
                }
            }
        }
    }

    // 치즈 녹이기
    private static boolean meltCheese() {
        boolean hasCheese = false;
        Queue<int[]> toMelt = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (cheese[i][j] == 1) {
                    hasCheese = true;
                    int airCount = 0;

                    for (int[] direction : directions) {
                        int dx = i + direction[0];
                        int dy = j + direction[1];

                        if (dx >= 0 && dy >= 0 && dx < n && dy < m && visited[dx][dy]) {
                            airCount++;
                        }
                    }

                    if (airCount >= 2) {
                        toMelt.add(new int[]{i, j});
                    }
                }
            }
        }

        // 녹은 치즈 제거
        while (!toMelt.isEmpty()) {
            int[] current = toMelt.poll();
            cheese[current[0]][current[1]] = 0;
        }

        return hasCheese;
    }
}