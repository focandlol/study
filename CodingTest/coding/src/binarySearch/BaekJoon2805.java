package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2805 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long m = Long.parseLong(st.nextToken());

        long[] arr = new long[(int) n];
        long min = 0;
        long max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
            if(max < arr[i]) {
                max = arr[i];
            }
        }

        long mid = 0;
        while(min < max) {

            mid = (max + min) / 2;
            long sum = 0;
            for(int i=0; i<n; i++) {
                if(arr[i] > mid){
                    sum += arr[i] - mid;
                }
            }

            if(sum < m){
                max = mid;
            }else{
                min = mid + 1;
            }
        }

        System.out.println(min - 1);

    }
}
