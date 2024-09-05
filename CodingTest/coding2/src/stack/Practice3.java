package stack;

import java.util.Stack;

public class Practice3 {
    public static void main(String[] args) {
        System.out.println(calculate("2 2 +"));
        System.out.println(calculate("2 2 -"));
        System.out.println(calculate("2 2 *"));
        System.out.println(calculate("2 2 /"));

        System.out.println(calculate("2 1 * 2 * 3 * 2 / 5 -"));
        System.out.println(calculate("5 2 * 3 - 8 * 4 /"));
    }

    private static double calculate(String s) {
        Stack<Double> stack = new Stack<Double>();
        String[] split = s.split(" ");
        for (int i = 0; i < split.length; i++) {
            String word = split[i];
            if(word.equals("+")) {
                stack.push(stack.pop()+stack.pop());
            }else if(word.equals("-")) {
                stack.push(-stack.pop()+stack.pop());
            }else if(word.equals("*")) {
                stack.push(stack.pop()*stack.pop());
            }else if(word.equals("/")) {
                stack.push(1/stack.pop()*stack.pop());
            }else{
                stack.push(Double.parseDouble(word));
            }
        }

        return stack.pop();
    }
}
