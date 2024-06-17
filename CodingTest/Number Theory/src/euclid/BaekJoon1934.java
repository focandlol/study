package euclid;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1934 {
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        arr = new int[T];
        for(int i=0; i<T; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            int euclid = euclid(Math.max(A, B), Math.min(A, B));

            //arr[i] = A*B/euclid;
            System.out.println(A*B/euclid);

        }

//        for(int i=0; i<T; i++) {
//            System.out.println(arr[i]);
//        }
    }

    private static int euclid(int a, int b) {
        if(a % b == 0){
            return b;
        }else {
            return euclid(b, a%b);
        }
    }


}
