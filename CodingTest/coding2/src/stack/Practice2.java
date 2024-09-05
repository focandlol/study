package stack;

import java.util.Stack;

public class Practice2 {
    public static void main(String[] args) {
        checkParenthesis("(");
        checkParenthesis(")");
        checkParenthesis("()");
        checkParenthesis("()()()");
        checkParenthesis("(())()");
        checkParenthesis("(()()())())");
    }

    private static void checkParenthesis(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c == '('){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    stack.push(c);
                    break;
                }else{
                    stack.pop();
                }
            }
        }
        if(stack.isEmpty()){
            System.out.println("pass");
        }else{
            System.out.println("fail");
        }
    }
}
