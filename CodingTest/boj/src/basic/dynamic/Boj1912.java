package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1912 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int max = Integer.MIN_VALUE;
        int count = 0;
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            count += Integer.parseInt(st.nextToken());
            max = Math.max(max, count);
            if(count < 0){
                count = 0;
            }
        }
        System.out.println(max);
    }
}
