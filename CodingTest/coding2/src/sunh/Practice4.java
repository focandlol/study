package sunh;

import java.util.Stack;

public class Practice4 {
    public static void main(String[] args) {
        solution("[yyyy]-[mm]-[dd]");
        solution("System.out.println(arr[0][1))");
        solution("([[{}])");
    }

    private static void solution(String str) {
        Stack<String> stack = new Stack<String>();
        boolean check = true;
        String[] split = str.split("");
        for (String s : split) {
            if(s.equals("}") || s.equals("]") || s.equals(")")) {
                if(stack.isEmpty()) {
                    check = false;
                    break;
                }else{
                    if(s.equals("]") && stack.peek().equals("[")){
                        stack.pop();
                    } else if (s.equals("}") && stack.peek().equals("{")) {
                        stack.pop();
                    } else if (s.equals(")") && stack.peek().equals(")")) {
                        stack.pop();
                    } else{
                        check = false;
                        break;
                    }
                }
            }else if(s.equals("{") || s.equals("(") || s.equals("[")) {
                stack.push(s);
            }
        }

        if(!stack.isEmpty()){
            check = false;
        }

        if(check){
            System.out.println("pass");
        }else{
            System.out.println("fail");
        }

    }
}
