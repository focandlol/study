package basic.bi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2110 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        Arrays.sort(arr);
        int left = 1;
        int right = max - min + 1;

        while(left < right){
            int mid = (left + right) / 2;
            if(check(mid)< c){
                right = mid;
            }else{
                left = mid + 1;
            }
        }
        System.out.println(left - 1);
    }

    private static int check(int mid) {
        int count = 1;
        int start = arr[0];
        for(int i = 1; i<arr.length; i++) {
            if(arr[i] - start >= mid){
                count++;
                start = arr[i];
            }
        }
        return count;
    }
}
