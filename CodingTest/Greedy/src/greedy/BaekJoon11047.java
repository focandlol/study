package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11047 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int sum = 0;
        int count  = 0;
        for(int i=N-1; i>=0; i--){

            if(arr[i] > K){
                continue;
            }
            while(K >= 0){
                if(K == 0){
                    break;
                }
                count++;
                K -= arr[i];
            }
            if(K == 0){
                System.out.println(count);
                break;
            }else{
                count--;
                K += arr[i];
            }
        }
    }
}
