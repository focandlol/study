package basic.greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj12970 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int aSum = n;
        int bSum = 0;

        for(int i=0; i<=n; i++) {
            aSum = n-i;
            bSum = i;
            if(aSum * bSum >= k) {
                break;
            }
        }
        if(bSum == n && aSum == 0){
            System.out.println(-1);
            return;
        }

        char[] arr = new char[n];
        Arrays.fill(arr,'B');
        for(int i=0; i<aSum; i++) {
            arr[i] = 'A';
        }

        int sum = aSum * bSum;
        int idx = aSum - 1;
        while(k < sum){
            if(sum - k > bSum){
                arr[idx] = 'B';
                arr[idx + bSum] = 'A';
                sum -= bSum;
                idx--;
            }else{
                arr[idx] = 'B';
                arr[idx + (sum - k)] = 'A';
                break;
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<arr.length; i++) {
            sb.append(arr[i]);
        }
        System.out.println(sb);

    }
}
