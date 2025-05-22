package basic.graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj2146 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[][] arr = new int[n][n];

    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0; j<n; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int[][] di = {{0,1,0,-1}, {1,0,-1,0}};

    int idx = -2;
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        if(arr[i][j]==1) {
          Queue<int[]> queue = new LinkedList<int[]>();
          arr[i][j] = -1;
          queue.add(new int[] {i, j});

          while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            for(int k = 0; k < 4; k++) {
              int dx = cur[0] + di[0][k];
              int dy = cur[1] + di[1][k];

              if(dx >= 0 && dx < n && dy >= 0 && dy < n) {
                if(arr[dx][dy]==1) {
                  queue.add(new int[]{dx, dy});
                  arr[dx][dy] = -1;
                }else if(arr[dx][dy]==0) {
                  arr[cur[0]][cur[1]] = idx;
                }
              }
            }
          }
          idx--;
        }
      }
    }

    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        System.out.print(arr[i][j] + " ");
      }
      System.out.println();
    }

    int min = 1000000;
    for(int i=0; i<n; i++) {
      for(int j=0; j<n; j++) {
        if(arr[i][j] <= -2) {

          int num = arr[i][j];
          Queue<int[]> queue = new LinkedList<>();
          queue.add(new int[] {i, j, 1});
          boolean[][] visited = new boolean[n][n];
          visited[i][j] = true;

          while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            if(arr[cur[0]][cur[1]] != 0 && arr[cur[0]][cur[1]] != num) {
              min = Math.min(min, cur[2]);
              break;
            }

            for(int k = 0; k < 4; k++) {
              int dx = cur[0] + di[0][k];
              int dy = cur[1] + di[1][k];

              if(dx >= 0 && dx < n && dy >= 0 && dy < n && arr[dx][dy] != -1 && !visited[dx][dy]) {
                if(arr[dx][dy] != num) {
                  queue.add(new int[]{dx, dy, cur[2] + 1});
                  visited[dx][dy] = true;
                }
              }
            }
          }
        }
      }
    }

    System.out.println(min - 2);
  }

}
