package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Boj2143 {

  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int t = Integer.parseInt(st.nextToken());

    st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());

    int[] arr1 = new int[n+1];
    int[] sum1 = new int[n+1];

    Map<Integer, Integer> map1 = new HashMap<>();

    st = new StringTokenizer(br.readLine());
    for(int i=1; i<=n; i++){
      arr1[i] = Integer.parseInt(st.nextToken());
      sum1[i] = sum1[i-1] + arr1[i];
    }

    st = new StringTokenizer(br.readLine());

    int m = Integer.parseInt(st.nextToken());

    int[] arr2 = new int[m+1];
    int[] sum2 = new int[m+1];

    st = new StringTokenizer(br.readLine());
    for(int i=1; i<=m; i++){
      arr2[i] = Integer.parseInt(st.nextToken());
      sum2[i] = sum2[i-1] + arr2[i];
    }

    for(int i=1; i<=n; i++){
      for(int j=0; j<i; j++){
        int sum = sum1[i] - sum1[j];
        map1.put(sum, map1.getOrDefault(sum, 0) + 1);
      }
    }

    long count = 0;
    for(int i=1; i<=m; i++){
      for(int j=0; j<i; j++){
        int sum = sum2[i] - sum2[j];
        Integer get = map1.get(t - sum);

        if(get != null){
          count += get;
        }
      }
    }
    System.out.println(count);
  }

}
