package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon3273 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        int x = Integer.parseInt(br.readLine());

        int left = 0;
        int right = arr.length - 1;
        int count = 0;
        while(left < right){
            if(arr[left] + arr[right] > x){
                right = right - 1;
            }else if(arr[left] + arr[right] < x){
                left = left + 1;
            }else{
                count++;
                right--;
                left++;
            }
        }

        System.out.println(count);
    }
}
