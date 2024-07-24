package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon9663_1117 {
    static int[] arr;
    static int n;
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        find(0);
        System.out.println(count);
    }

    private static void find(int depth) {

        if(depth == n){
            count++;
            return;
        }

        for(int i=0; i<n; i++){
            if(check(depth,i)){
                arr[depth] = i;
                find(depth + 1);
            }
        }

    }

    private static boolean check(int depth, int i) {

        for(int j=0; j<depth; j++){
            if(arr[j] == i){
                return false;
            }else if(Math.abs(j-depth) == Math.abs(arr[j] - i)){
                return false;
            }
        }
        return true;
    }
}
