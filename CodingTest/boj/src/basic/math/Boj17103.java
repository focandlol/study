package basic.math;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Boj17103 {
    static boolean[] arr = new boolean[1000001];
    static int count = 0;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        arr[1] = true;
        for(int i=2; i<=Math.sqrt(1000000); i++){
            if(!arr[i]){
                for(int j=i*2; j<=1000000; j+=i){
                    arr[j] = true;
                }
            }
        }

        for(int i=0; i<t; i++){
            int a = Integer.parseInt(br.readLine());

            find(a);
        }
        System.out.println(sb);
    }
    private static void find(int a) {
        for(int i=2; i<=a/2; i++){
            if(!arr[i]){
                if(!arr[a-i]){
                    count++;
                }
            }
        }
        sb.append(count).append("\n");
        count=0;
    }
}
