package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class BaekJoon11729__1117 {
    static StringBuilder sb = new StringBuilder();
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        find(1,3,2,n);
        System.out.println(count);
        System.out.println(sb);
    }

    private static void find(int start, int end, int sub, int n) {

        if(n == 1){
            move(start,end,n);
            return;
        }

        find(start,sub,end,n-1);
        move(start,end,n);
        find(sub,end,start,n-1);
    }

    private static void move(int start, int end, int n) {
        count++;
        sb.append(start).append(" ").append(end).append("\n");
    }
}
