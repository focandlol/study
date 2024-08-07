package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon2110 {
    static long n;
    static long c;
    static long[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        arr = new long[(int) n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(arr);

        long left = 1;
        long right = arr[(int) (n-1)] - arr[0] + 1;

        while (left < right) {
            long mid = (right + left) / 2;

            if(check(mid) < c){
                right = mid;
            }else{
                left = mid + 1;
            }
        }

        System.out.println(left-1);



    }

    private static long check(long mid) {
        long count = 1;
        long start = arr[0];

        for(int i = 1; i < n; i++) {
            long lo = arr[i];

            if(lo - start >= mid){
                count++;
                start = lo;
            }
        }
        return count;
    }
}
