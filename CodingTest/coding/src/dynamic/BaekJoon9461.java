package dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon9461 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        long t = Long.parseLong(br.readLine());
        long[] arr = new long[101];
        arr[1] = 1;
        arr[2] = 1;
        arr[3] = 1;

        for(long i = 0; i < t; i++){
            sb = new StringBuilder();
            int n = Integer.parseInt(br.readLine());
            if(n == 1 ||n== 2 || n==3){
                sb.append(1);
            }else {
                for (int j = 4; j <= n; j++) {
                    arr[j] = arr[j - 2] + arr[j - 3];
                }
                sb.append(arr[n]);
            }
            System.out.println(sb);
        }

    }
}

