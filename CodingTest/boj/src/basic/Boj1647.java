package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Boj1647 {
  static int[] ga;
  static int count = 0;
  static int max = 0;
  static int sum = 0;
  public static void main(String[] args) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    int n = Integer.parseInt(st.nextToken());
    int m = Integer.parseInt(st.nextToken());

    Node[] arr = new Node[m];

    for(int i=0; i<m; i++) {
      st = new StringTokenizer(br.readLine());
      int a = Integer.parseInt(st.nextToken());
      int b = Integer.parseInt(st.nextToken());
      int c = Integer.parseInt(st.nextToken());

      arr[i] = new Node(a,b,c);


    }

    Arrays.sort(arr, Comparator.comparingInt(a -> a.weight));


    ga = new int[n+1];
    for(int i=1; i<=n; i++){
      ga[i] = i;
    }

    for(int i=0; i<arr.length; i++) {
      union(arr[i]);
      //if(count == n - 1) break;
    }
    System.out.println(sum - max);

  }

  static void union(Node node) {
    int a = find(node.from);
    int b = find(node.to);

    if(a != b){
      ga[b] = a;
      //count++;
      max = Math.max(max, node.weight);
      sum += node.weight;
    }
  }

  static int find(int a){
    if(ga[a] == a) return a;
    return ga[a] = find(ga[a]);
  }

  static class Node{
    int from;
    int to;
    int weight;

    public Node(int from, int to, int weight) {
      this.from = from;
      this.to = to;
      this.weight = weight;
    }
  }

}
