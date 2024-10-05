package Math.p2;

public class Practice1 {
    public static void main(String[] args) {
        System.out.println(solution(0));
    }

    private static int solution(int n) {

        int result = 0;
        if(n <= 1){
            return 1;
        }

        for(int i=0; i<n; i++){
            result += solution(i) * solution(n -i -1);
        }
        return result;
    }
}
