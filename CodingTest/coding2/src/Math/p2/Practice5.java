package Math.p2;

import java.sql.SQLOutput;

public class Practice5 {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) {
        solution(2);
        System.out.println();

        solution(3);
        System.out.println();

        solution(4);
        System.out.println();

    }

    private static void solution(int n) {

        hanoi(n,1,2,3);
        System.out.println(sb);
        sb = new StringBuilder();
    }

    private static void hanoi(int n, int start, int sub, int end) {

        if(n == 1){
            sb.append(start + " " + end).append("\n");
            return;
        }
        hanoi(n -1,start,end,sub);
        sb.append(start + " " + end).append("\n");
        hanoi(n-1,sub,start,end);
    }
}
