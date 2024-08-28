package basicMath.practice1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Practice2 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int i = 0;
        arr = new int[st.countTokens()];
        while(st.hasMoreTokens()){
            arr[i] = Integer.parseInt(st.nextToken());
            i++;
        }


        for(int j = arr.length-1; j>1; j--){
            for(int k = j-1; k>0; k--){
                if(arr[j] < arr[k]){
                    swap(j,k);
                    System.out.println(Arrays.toString(arr));
                    return;
                }
            }
        }
        System.out.println(Arrays.toString(arr));
    }


    private static void swap(int j, int k) {
        int temp = 0;
        temp = arr[j];
        arr[j] = arr[k];
        arr[k] = temp;
    }
}
