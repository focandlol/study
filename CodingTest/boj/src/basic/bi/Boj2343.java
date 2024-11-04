package basic.bi;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        int sum = 0;
        int max = 0;
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            sum += arr[i];
            max = Math.max(max, arr[i]);
        }

        int left = max;
        int right = sum + 1;

        while(left < right){
            int mid = (left + right) / 2;
            int count = 1;
            int gap = 0;
            for(int i = 0; i < n; i++) {
                if(gap + arr[i] > mid){
                    gap = 0;
                    count++;
                }
                gap += arr[i];
            }
            if(count > m){
                left = mid + 1;
            }else{
                right = mid;
            }
        }
        System.out.println(left);

    }
}
