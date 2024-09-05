package stack;

import java.util.Stack;

public class Practice1 {
    public static void main(String[] args) {
        String result = reverseString("Hello");
        System.out.println("result = " + result);

        result = reverseString("1 3 5 7 9");
        System.out.println("result = " + result);
    }

    private static String reverseString(String str) {
        Stack<Character> stack = new Stack<Character>();
        for(int i=0; i<str.length(); i++) {
            stack.push(str.charAt(i));
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }
}
