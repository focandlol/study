package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        for(int i=0; i<n; i++){
            int a = Integer.parseInt(br.readLine());
            arr[i] = a;
        }

        int count = 0;
        for(int i = n-1; i>=0; i--){
            if(k == 0){
                break;
            }
            if(k / arr[i] > 0){
                int b = k / arr[i];
                count += b;
                k = k % arr[i];
            }
        }

        System.out.println(count);
    }
}
