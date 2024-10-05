package Math;

import java.util.HashSet;

public class Practice4 {
    public static void main(String[] args) {
        System.out.println(solution(19));
        System.out.println(solution(2));
        System.out.println(solution(61));
    }

    private static boolean solution(int n) {

        HashSet<Integer> set = new HashSet<Integer>();

        while(set.add(n)){
            int dap = 0;
            while(n > 0){
                int a = n % 10;
                dap += a*a;
                n /= 10;
            }
            if(dap % 10 == 1){
                return true;
            }else{
                n = dap;
            }
        }
        return false;
    }
}
