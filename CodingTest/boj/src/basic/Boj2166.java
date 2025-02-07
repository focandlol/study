package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2166 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    long[][] arr = new long[n][2];

    for(int i=0; i<n; i++) {
      st = new StringTokenizer(br.readLine());
      arr[i][0] = Long.parseLong(st.nextToken());
      arr[i][1] = Long.parseLong(st.nextToken());
    }

    double sum = 0;
    for(int i=0; i<n; i++) {
      sum += arr[i][0] * arr[(i+1)%n][1];
      sum -= arr[i][1] * arr[(i+1)%n][0];
    }

    System.out.printf("%.1f\n", Math.abs(sum / 2));
  }

}
