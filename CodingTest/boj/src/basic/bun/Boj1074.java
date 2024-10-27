package basic.bun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj1074 {
    static int n;
    static int r;
    static int c;
    static int sum = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        find(r,c,(int)Math.pow(2,n),0);

    }

    private static void find(int r, int c, int n,int dap) {
        if(n == 1){
            System.out.println(dap);
            return;
        }
        int newSize = n/2;
        if(r < newSize && c < newSize) {
            find(r,c,newSize,dap);
        }else if(r < newSize && c>= newSize){
            find(r,c-newSize,newSize,dap+=newSize * newSize);
        }else if(r >= newSize && c < newSize){
            find(r-newSize,c,newSize,dap+=newSize * newSize * 2);
        }else if(r >= newSize && c >= newSize){
            find(r-newSize,c-newSize,newSize,dap+=newSize * newSize * 3);
        }
    }
}
