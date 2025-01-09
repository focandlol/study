package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1182 {
    static int n;
    static int s;
    static int[] arr;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=1; i<=n; i++){
            find(0,0,0,i);
        }

        System.out.println(count);
    }

    private static void find(int start, int depth,int sum,int last) {
        if(depth == last){
            if(sum == s){
                count++;
            }
            return;
        }

        for(int i=start; i<n; i++){
            find(i+1, depth+1, sum+arr[i],last);
        }
    }
}
