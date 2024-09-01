package basicMath.practice1;

public class Practice10_2 {
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
        hanoi(1,2,3,i);
        System.out.println(sb);
    }

    private static void hanoi(int start, int sub, int end, int n) {

        if(n == 1){
            move(start,end);
            return;
        }

        hanoi(start,end,sub,n-1);
        move(start,end);
        hanoi(sub,start,end,n-1);

    }

    private static void move(int start, int end) {
        sb.append(start).append(" ").append(end).append("\n");
    }
}
