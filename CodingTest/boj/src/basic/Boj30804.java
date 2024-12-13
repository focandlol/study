package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj30804 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] count = new int[10];

        st = new StringTokenizer(br.readLine());
        int minVal = 10;
        int maxVal = 0;
        for(int i=0;i<n;i++){
            int a = Integer.parseInt(st.nextToken());
            count[a]++;
            arr[i] = a;
            minVal = Math.min(minVal,a);
            maxVal = Math.max(maxVal,a);
        }

        int max = 0;
        for(int i=1; i<=9; i++){
            for(int j=i+1; j<=9; j++){
                int left = 0;
                int right = 0;
                while(right<arr.length){
                    if(arr[right] == i || arr[right] == j){
                        max = Math.max(max,right - left + 1);
                        right++;
                    }else{
                        right++;
                        left = right;
                    }
                }
            }
        }
        System.out.println(max);


    }
}
