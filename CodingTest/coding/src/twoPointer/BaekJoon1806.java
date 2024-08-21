package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1806 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 0;
        int hap = arr[0];
        int min = Integer.MAX_VALUE;
        while(left <= right){
            if(hap < s){
                right++;
                if(right >= n){
                    break;
                }
                hap += arr[right];
            }else{
                min = Math.min(min, right - left + 1);
                hap -= arr[left];
                left++;
            }
        }

        if(min == Integer.MAX_VALUE){
            System.out.println(0);
        }else {
            System.out.println(min);
        }

    }
}
