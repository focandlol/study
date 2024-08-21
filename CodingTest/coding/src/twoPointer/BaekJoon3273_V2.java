package twoPointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon3273_V2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] da = new int[2000001];
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < n; i++){
            int a = Integer.parseInt(st.nextToken());
            da[a] = 1;
            arr[i] = a;
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);
        int count = 0;
        if(x - arr[n-1] >= 1000000){
            System.out.println(count);
            return;
        }
        for(int i=0; i<n; i++){
            if(arr[i] > x/2){
                break;
            }
            if(arr[i] + arr[i] != x){
                if(da[x-arr[i]] == 1){
                    count++;
                }
            }
        }
        System.out.println(count);
    }
}
