package bit;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon2830_kkkkkkkk {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[20];

        for(int i=0; i<n; i++){
            int k = Integer.parseInt(br.readLine());
            for(int j=0; j<20; j++){
                if((k & (1 << j)) != 0){
                    a[j]++;
                }
            }
        }

        long dap = 0;
        for(int i=0; i<20; i++){
            long one = a[i];
            long zero = n-a[i];
            dap += one * zero * (1<<i);
        }

//        for(int i=19; i>=0; i--){
//            int one = a[i];
//            int zero = n-a[i];
//            dap += one * zero;
//            dap <<= 1;
//        }
//        dap >>= 1;
//
//        System.out.println(dap);
        System.out.println(dap);
    }
}
