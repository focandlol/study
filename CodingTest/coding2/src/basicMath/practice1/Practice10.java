package basicMath.practice1;

public class Practice10 {
    static StringBuilder sb;
    public static void main(String[] args) {
        sol(2);
        System.out.println();
        sol(3);
        System.out.println();
        sol(4);
    }

    private static void sol(int i) {
        sb = new StringBuilder();
        hanoi(1,3,2,i);
        System.out.println(sb);
    }

    private static void hanoi(int start, int end, int sub, int n) {

        if(n == 1){
            move(start,end);
            return;
        }

        hanoi(start,sub,end,n-1);
        move(start,end);
        hanoi(sub,end,start,n-1);

    }

    private static void move(int start, int end) {
        sb.append(start).append(" ").append(end).append("\n");
    }
}
