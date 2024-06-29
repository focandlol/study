import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2042V1 {
    static long[] arr;
    static long pow;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        //int sqrt = (int) Math.ceil(Math.sqrt(n));
        int depth = 0;
        while(Math.pow(2, depth) < n) {
            depth ++;
        }
        //depth++;
        //int arraySize = (int)Math.pow(2, depth);
        pow = (int) Math.pow(2,depth);
        //arr = new long[(int) (pow*2)];
        arr = new long[(int) (pow*2)];

        for(int i = 0 ; i < n ; i ++) {
            st = new StringTokenizer(br.readLine());
            long in = Long.parseLong(st.nextToken());
            arr[(int) (pow + i)] = in;
        }

        //System.out.println(Arrays.toString(arr));

        for(long i = pow*2-1; i>1; i--){
            int num = (int) (i/2);
            arr[num] += arr[(int) i];
        }
//        for(long i = pow - 1 ; i >= 1 ; i --) {
//            arr[(int) i] = arr[(int) (i * 2)] + arr[(int) (i * 2 + 1)];
//        }
        //System.out.println(Arrays.toString(arr));

        for(int i=0; i<m+k; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            long b = Long.parseLong(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if(a == 1){
                change(b,c);
            }else{
                sum(b,c);
            }
        }
    }

    private static void sum(long b, long c) {
        long count = 0;
        long start = b + pow -1;
        long end = c + pow -1;

        while(start<=end){
            if(start % 2 == 1){
                count += arr[(int) start];
                start++;
            }
            if(end % 2 == 0){
                count += arr[(int) end];
                end--;
            }
            start/=2;
            end/=2;
        }
        System.out.println(count);
    }

    private static void change(long b, long c) {
        long num = (int) (b + pow -1);
        long diff = c - arr[(int) num];
        arr[(int) num] = c;
        while(num > 1){
            num = num/2;
            arr[(int) num] += diff;
        }
    }
}
