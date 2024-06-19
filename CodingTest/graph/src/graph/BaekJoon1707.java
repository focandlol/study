package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BaekJoon1707 {
    static ArrayList<Integer>[] arr;
    static boolean[] visited;
    static int[] check;
    static boolean IsEven;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int k = Integer.parseInt(st.nextToken());
        for(int i = 0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int v = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            arr = new ArrayList[v+1];
            visited = new boolean[v+1];
            check = new int[v+1];
            IsEven = true;

            for(int p=1; p<=v; p++){
                arr[p] = new ArrayList<>();
            }

            for(int j=0; j<e; j++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[a].add(b);
                arr[b].add(a);
            }

            for(int m = 1; m<= v; m++) {
                if(!IsEven){
                    break;
                }else if(!visited[m] && IsEven){
                    dfs(m);
                }
            }
            if(IsEven){
                System.out.println("YES");
            }else{
                System.out.println("NO");
            }
        }
    }

    private static void dfs(int i) {
        visited[i] = true;

        for(int a :  arr[i]){
            if(!visited[a]){
                check[a] = (check[i] + 1)%2;
                dfs(a);
            }else{
                if(check[a] == check[i]){
                    IsEven = false;
                    return;
                }
            }
        }
    }
}
