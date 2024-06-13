package binary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BaekJoon1920 {
    static int[] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] arr = new int[M];
        for(int i=0; i<M; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(A);

        for(int i=0; i<M; i++) {
            int start = 0;
            int end = A.length-1;
            boolean found = false;
            //binary(arr[i],0,A.length-1);
            while(start <= end){
                int mid = (start + end)/2;
                if(arr[i] == A[mid]){
                    found = true;
                    break;
                }
                else if(arr[i] < A[mid]){
                    end = mid - 1;
                }else if(arr[i] > A[mid]){
                    start = mid + 1;
                }
            }
            if(found){
                System.out.println("1");
            }else{
                System.out.println("0");
            }
        }
    }

//    private static void binary(int f,int left, int right) {
//        if(f > A[A.length-1] || f < A[0]) {
//            System.out.println("0");
//            return;
//        }
//       // int mid = (left + right)/2;
//        if(f == A[(left + right)/2]){
//            System.out.println("1");
//            return;
//        }
//        else if(f < A[(left + right)/2]){
//            binary(f,0,(left + right)/2-1);
//        }else if(f > A[(left + right)/2]){
//            binary(f,(left + right)/2+1,right);
//        }
//        if(left == right){
//            System.out.println("0");
//        }
//    }
}
