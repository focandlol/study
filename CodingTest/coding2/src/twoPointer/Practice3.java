package twoPointer;

import java.util.Arrays;

public class Practice3 {
    public static void main(String[] args) {
        System.out.println(solution("the sky is blue"));
        System.out.println(solution("  hello   java   "));
    }

    private static String solution(String s) {
        String[] split = s.split(" ");
        System.out.println(Arrays.toString(split));
        boolean a = false;
        boolean b = false;
        String dap = "";
        for(int i = split.length-1; i >=0; i--) {
            if(split[i].equals("")){
                i--;
            }else{
                if(a == false){
                    a = true;
                }else{
                    b = true;
                    if(a == true && b == true){
                        dap += " ";
                        b = false;
                    }
                }


                dap += split[i];

            }
        }
        return dap;
    }
}
