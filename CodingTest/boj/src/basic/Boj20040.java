package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj20040 {
  static int[] dap;
  static boolean isCycle = false;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    dap = new int[n];
    for(int i=0; i<n; i++) {
      dap[i] = i;
    }

    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());

      union(a, b);

      if(isCycle){
        System.out.println(i + 1);
        return;
      }
    }
    System.out.println(0);
  }

  static int find(int a){
    if(a == dap[a]) return a;
    return dap[a] = find(dap[a]);
  }

  static void union(int a, int b){
    int findA = find(a);
    int findB = find(b);

    if(findA == findB) {
      isCycle = true;
      return;
    }

    if(findA < findB){
      dap[findB] = findA;
    }else{
      dap[findA] = findB;
    }
  }

}
