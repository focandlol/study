package union;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1717 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        for(int i=0; i<=n; i++){
            arr[i] = i;
        }
        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int zip = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            if(zip == 0){
                union(Math.min(a,b),Math.max(a,b));
            }else{
                int c = find(a);
                int d = find(b);
                if(c == d){
                    System.out.println("YES");
                }else{
                    System.out.println("NO");
                }
            }
        }
    }

    private static int find(int a) {
        if(arr[a] == a){
            return a;
        }else{
            int i = find(arr[a]);
            arr[a] = i;
            return i;
        }

    }

    private static void union(int min, int max) {
        int i = find(min);
        int j = find(max);
        arr[j] = i;
    }
}
