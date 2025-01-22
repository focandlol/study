package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Boj15685 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    boolean[][] visited = new boolean[101][101];

    int[][] di = {{1,0,-1,0},{0,-1,0,1}};

    for(int i= 0; i<n; i++){
      st = new StringTokenizer(br.readLine());
      int x = Integer.parseInt(st.nextToken());
      int y = Integer.parseInt(st.nextToken());
      int d = Integer.parseInt(st.nextToken());
      int g = Integer.parseInt(st.nextToken());

      visited[x][y] = true;
      visited[x + di[0][d]][y + di[1][d]] = true;

      x = x + di[0][d];
      y = y + di[1][d];

      List<Integer> se = new ArrayList<Integer>();
      se.add(d);
      while(g > 0){
        for(int j=se.size()-1; j>=0; j--){
          int dir = (se.get(j)+1) % 4;
          x = x + di[0][dir];
          y = y + di[1][dir];
          visited[x][y] = true;
          se.add(dir);
        }
        g--;
      }

    }



    int count = 0;
    for(int i=0; i<=99; i++){
      for(int j=0; j<=99; j++){
        if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1]){
          count++;
        }
      }
    }
    System.out.println(count);
  }

}
