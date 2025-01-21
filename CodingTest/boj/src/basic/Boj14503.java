package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj14503 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    int[][] arr = new int[n][m];

    st = new StringTokenizer(br.readLine());

    int x = Integer.parseInt(st.nextToken());
    int y = Integer.parseInt(st.nextToken());
    int d = Integer.parseInt(st.nextToken());

    for (int i = 0; i < n; i++) {
      st = new StringTokenizer(br.readLine());
      for (int j = 0; j < m; j++) {
        arr[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    int count = 0;
    int[][] di = {{0, 1, 0, -1}, {-1, 0, 1, 0}};
    while (true) {
      if (arr[x][y] == 0) {
        count++;
        arr[x][y] = -1;
      }
      int start = (4 - d) % 4;
      boolean clean = true;

      for (int i = 0; i < 4; i++) {
        int dx = x + di[0][(start + i) % 4];
        int dy = y + di[1][(start + i) % 4];
        d = (d + 3) % 4;
        if (dx >= 0 && dx < n && dy >= 0 && dy < m && arr[dx][dy] == 0) {
          x = dx;
          y = dy;
          clean = false;
          break;
        }
      }

      if (clean) {
        x = x + di[0][(((4 - d) % 4) + 1) % 4];
        y = y + di[1][(((4 - d) % 4) + 1) % 4];
        if (x < 0 || x >= n || y < 0 || y >= m || arr[x][y] == 1) {
          break;
        }
      }

    }

    System.out.println(count);


  }

}
