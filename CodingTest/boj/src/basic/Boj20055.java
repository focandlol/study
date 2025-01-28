package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20055 {
  static int n = 0;
  static int k = 0;
  static int[] arr;
  static int[] robot;
  static int count = 0;

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    n = Integer.parseInt(st.nextToken());
    k = Integer.parseInt(st.nextToken());

    arr = new int[2 * n];
    robot = new int[n];

    st = new StringTokenizer(br.readLine());
    for(int i=0; i<2*n; i++){
      arr[i] = Integer.parseInt(st.nextToken());
    }

    int dap = 0;
    while(count < k) {
      rotate();
      move();
      plus();
      dap++;
    }

    System.out.println(dap);
  }

  private static void plus() {
    if(arr[0] > 0){
      robot[0]++;
      arr[0]--;
      if(arr[0] == 0) count++;
    }
  }

  private static void move() {
    for(int i=n-2; i>=0; i--){
      if(robot[i] >= 1){
        if(arr[i+1] > 0 && robot[i+1] == 0){
          robot[i+1]++;
          robot[i]--;
          arr[i+1]--;
          if(arr[i+1] == 0) count++;
        }
      }
    }
    robot[n-1] = 0;
  }

  private static void rotate() {
    int temp = arr[2 * n - 1];
    for(int i=2 * n - 2; i>=0; i--){
      arr[i+1] = arr[i];
    }
    arr[0] = temp;

    robot[n-1] = 0;
    for(int i=n-3; i>=0; i--){
      robot[i+1] = robot[i];
    }
    robot[0] = 0;
  }

}
