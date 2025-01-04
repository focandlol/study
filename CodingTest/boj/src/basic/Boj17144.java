package basic;

import java.io.*;
import java.util.*;

public class Boj17144 {
    static int[][] map;
    static int[][] newMap;
    static int R, C, T;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, 1, -1};
    static List<Integer> cleaner = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        newMap = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int item = Integer.parseInt(st.nextToken());
                map[i][j] = item;
                newMap[i][j] = item;
                if (item == -1) cleaner.add(i);
            }
        }

        for (int i = 0; i < T; i++) {
            spread();
            clean_top();
            clean_bottom();
            coppyArr(newMap, map);
        }

        int sum = 0;
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sum += map[i][j];
            }
        }

        System.out.println(sum + 2);
    }

    public static void spread() {
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0) {
                    int calc = map[r][c] / 5;
                    for (int i = 0; i < 4; i++) {
                        int newR = r + dr[i];
                        int newC = c + dc[i];
                        if (0 <= newR && newR < R && 0 <= newC && newC < C && map[newR][newC] != -1) {
                            newMap[newR][newC] += calc;
                            newMap[r][c] -= calc;
                        }
                    }
                }
            }
        }
        coppyArr(map, newMap);
    }

    public static void coppyArr(int[][] target, int[][] org) {
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                target[i][j] = org[i][j];
            }
        }
    }

    public static void clean_top() {
        int top = cleaner.get(0);
        for (int r = top - 1; r >= 1; r--) map[r][0] = map[r - 1][0];
        for (int c = 0; c <= C - 2; c++) map[0][c] = map[0][c + 1];
        for (int r = 0; r <= top - 1; r++) map[r][C - 1] = map[r + 1][C - 1];
        for (int c = C - 1; c >= 2; c--) map[top][c] = map[top][c - 1];
        map[top][1] = 0;
    }

    public static void clean_bottom() {
        int bottom = cleaner.get(1);
        for (int r = bottom + 1; r <= R - 2; r++) map[r][0] = map[r + 1][0];
        for (int c = 0; c <= C - 2; c++) map[R - 1][c] = map[R - 1][c + 1];
        for (int r = R - 1; r >= bottom + 1; r--) map[r][C - 1] = map[r - 1][C - 1];
        for (int c = C - 1; c >= 2; c--) map[bottom][c] = map[bottom][c - 1];
        map[bottom][1] = 0;
    }
}




