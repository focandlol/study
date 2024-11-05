package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Boj16954 {
    static int[][] di = {{-1, -1, 0, 1, 1, 1, 0, -1, 0},{0, 1, 1, 1, 0, -1, -1, -1, 0}};
    static char[][] arr = new char[8][8];
    static boolean[][] visited = new boolean[8][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<8; i++){
            arr[i] = br.readLine().toCharArray();
        }

        Queue<int[]> q = new LinkedList<int[]>();
        q.add(new int[]{7, 0});
        while(!q.isEmpty()){
            visited = new boolean[8][8];
            int size = q.size();
            for(int k=0; k<size; k++) {
                int[] cur = q.poll();
                if(arr[cur[0]][cur[1]] == '#'){
                    continue;
                }
                if(cur[0] == 0 && cur[1] == 7){
                    System.out.println(1);
                    return;
                }
                for (int i = 0; i < 9; i++) {
                    int x = cur[0] + di[0][i];
                    int y = cur[1] + di[1][i];
                    if (x >= 0 && x < 8 && y >= 0 && y < 8 && arr[x][y] != '#' && !visited[x][y]) {
                        //System.out.println("X = " + x + ", Y = " + y);
                        visited[x][y] = true;
                        q.add(new int[]{x, y});
                    }
                }
            }
            move();
        }
        System.out.println(0);
    }

    private static void move() {
        for(int i=7; i>=0; i--){
            for(int j=0; j<8; j++){
                if(arr[i][j] == '#'){
                    //System.out.println("i = " + i + ", j = " + j);
                    arr[i][j] = '.';
                    if(i != 7){
                        arr[i+1][j] = '#';
                    }
                }

            }
        }
    }
}
