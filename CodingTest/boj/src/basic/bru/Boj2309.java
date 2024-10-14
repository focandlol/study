package basic.bru;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Boj2309 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        int hap = 0;
        for(int i=0; i<9; i++){
            int a = Integer.parseInt(br.readLine());
            hap += a;
            arr[i] = a;
        }
        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<9; i++){
            for(int j = i+1; j<9; j++){
                if(hap - arr[i] - arr[j] == 100){
                    for(int k=0; k<9; k++){
                        if(k != i && k != j){
                            sb.append(arr[k]).append("\n");
                        }
                    }
                    System.out.println(sb);
                    return;
                }
            }
        }

    }
}
