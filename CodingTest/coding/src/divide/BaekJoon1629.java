package divide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1629 {
    static long a;
    static long b;
    static long c;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());
        c = Long.parseLong(st.nextToken());

        System.out.println(find(a, b));
    }

    private static long find(long a, long b) {

        if(b == 1){
            return a%c;
        }
        long na = b/2;
        long dap = find(a, na);
        if(b % 2 == 1){
            dap = (((dap*dap)%c)*a)%c;
        }else{
            dap = (dap*dap)%c;
        }
        return dap;
    }
}
